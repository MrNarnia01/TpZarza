<template>
    
    <form @submit.prevent="crear(1)">

        <table>
            <tr>
                <td colspan="2"><h1>Registro de libro</h1></td>
            </tr>
            <tr>
                <td class="boton"><RouterLink to="/Main" class="router-link"> Volver al inicio </RouterLink></td>
                <td class="boton"><RouterLink to="/ListaLibros" class="router-link"> Listado de libros </RouterLink></td>
            </tr>

            <tr>
                <td>
                    <label for="tit">Titulo: </label>
                    <input type="text" name="tit" id="tit" v-model="NewLibro.titulo" required>
                </td>
                <td>
                    <label for="aut">Autor: </label>
                    <input type="text" name="aut" id="aut" v-model="NewLibro.autor" required>
                </td>
            </tr>
            
            <tr>
                <td>
                    <label for="sin">Sinopsis: </label><input type="text" name="sin" id="sin" v-model="NewLibro.sinopsis" required>
                </td>
                <td>
                    <label for="cap">Paginas: </label><input type="number" name="cap" id="cap" v-model="NewLibro.cantPag" required min="1" value="1">
                </td>
            </tr>

            <tr>
                <td>
                    <label for="fep">Fecha de publicacion: </label>
                </td>
                <td>
                    <input type="date" name="fep" id="fep" v-model="NewLibro.fep" required :max="fHoy">
                </td>
            </tr>
                
            <tr v-for="(input, index) in inputs" :key="index">
                <th>Genero: {{ index+1 }}<input v-model="inputs[index]" type="text" required/></th>
                <td @click="eliminarInput(index)" class="boton">Eliminar</td>
            </tr>

            <tr>
                <td colspan="2">
                    <button type="submit">Crear Libro</button>
                    <button @click="agregarInput" colspan="2" class="boton" type="button">Agregar Genero</button>
                </td>
            </tr>

                
        </table>
    </form>
        
    
    

</template>

<script>
    import { RouterLink } from 'vue-router';
    import axios from 'axios';
    import mensajes from './mensajes';
    export default{
        data(){
            return{
                NewLibro:{
                    titulo:'',
                    autor:'',
                    sinopsis:'',
                    cantPag:1,
                    fep:new Date(),
                    generos:null,
                    prestamos:null,
                },
                inputs: [''],
                lId: 0,
                fHoy:'',
            };
        },
        mounted() {
                // Obtener la fecha actual en formato YYYY-MM-DD
                const today = new Date();
                const year = today.getFullYear();
                let month = today.getMonth() + 1;
                month = month < 10 ? '0' + month : month;
                let day = today.getDate();
                day = day < 10 ? '0' + day : day;

                // Asignar la fecha actual a la propiedad fechaActual
                this.fHoy = `${year}-${month}-${day}`;
                console.log(this.fHoy);
            },
        methods: {
            crear(pru){

                    const gens = this.inputs.map(input => {
                        return {
                            genero: input
                        };
                    });
                    console.log(gens);
                    this.NewLibro.generos=gens;
                    console.log(this.NewLibro.generos)
                    axios.post( 'http://localhost:8080/Libro/reg',this.NewLibro).then(response => {
                        console.log(response.data);
                        console.log('Libro cargado correctamente');
                        this.$router.push('/ListaLibros')
                    })
                    .catch(error => {
                        const er=error.response.data;
                        console.log('Error: ', mensajes.obtenerMensajePorId(er));
                        window.alert('Error: '+ mensajes.obtenerMensajePorId(er));
                    });
            },/*
            async idLib(){
                try {
                    const response = await axios.get('http://localhost:8080/Libro/ult');
                    console.log('id libro',response.data);
                    this.lId=response.data;
                    this.allGenero();
                } catch (error) {
                    console.log('Id de libro conseguido');
                }
            },
            allGenero(){
                const gens = this.inputs.map(input => {
                    return {
                        idL: this.lId, // Aquí se puede definir el valor para todos los inputs
                        gen: input
                    };
                });
                console.log(gens);
                axios.post('http://localhost:8080/Genero', gens)
                    .then(response => {
                        console.log('Generos cargados');
                    })
                    .catch(error => {
                        console.error('Error al crear generos:', error);
                    });
            },*/
            agregarInput() {
                if (this.inputs.every(input => input.trim() !== '')) {
                    this.inputs.push('');
                } else {
                    // Muestra un mensaje de error o realiza alguna acción apropiada
                    console.log('Por favor, completa el input antes de agregarlo.');
                    window.alert('Por favor, completa el input antes de agregarlo.');
                }
            },
            eliminarInput(index) {
                if(this.inputs.length!=1)
                this.inputs.splice(index, 1); // Elimina el input en la posición 'index'
            }
        }
    }
</script>