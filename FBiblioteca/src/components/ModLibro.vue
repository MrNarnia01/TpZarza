<template>
        
        
        
    
    <form @submit.prevent="modificar">

        <table>
            <tr>
                <td colspan="2"><h1>Modificar de libro</h1></td>
            </tr>

            <tr>
                <td class="boton"><RouterLink to="/Main" class="router-link"> Volver al inicio </RouterLink></td>
                <td class="boton"><RouterLink to="/ListaLibros" class="router-link"> Listado de libros </RouterLink></td>
            </tr>

            <tr>
                <td>
                    <label for="tit">Titulo: </label>
                    <input type="text" name="tit" id="tit" v-model="libro.titulo"  required>
                </td>
                <td>
                    <label for="aut">Autor: </label>
                    <input type="text" name="aut" id="aut" v-model="libro.autor"  required>
                </td>
            </tr>

            <tr>
                <td>
                    <label for="sin">Sinopsis: </label>
                    <input type="text" name="sin" id="sin" v-model="libro.sinopsis"  required>
                </td>
                <td>
                    <label for="cap">Paginas: </label>
                    <input type="number" name="cap" id="cap" v-model="libro.cantPag" required min="1" >
                </td>
            </tr>

            <tr>
                <td><label for="fep">Fecha de publicacion: </label></td>
                <td><input type="date" name="fep" id="fep" v-model="libro.fep" required :max="fHoy"></td>
            </tr>

            <tr>
                <th colspan="2">Fecha anterior: {{ dia()}}</th>
            </tr>

        <tr v-for="(input, index) in inputs" :key="index">
            <th>
                Genero: {{ index+1 }}
                <input v-model="inputs[index]" type="text" required/>
            </th>
            <td class="boton" @click="eliminarInput(index)">Eliminar</td>
        </tr>

        <tr>
            <td colspan="2">
                <button type="submit">Guardar cambios</button>
                <button @click="agregarInput" type="button">Agregar Genero</button>
            </td>
        </tr>
        

        </table>
    </form>


</template>
<script>
import  axios  from 'axios';
import mensajes from './mensajes';
import { RouterLink } from 'vue-router';
export default{
        data(){
            return{
                fHoy:'',
                libro: Object,
                id:0,
                inputs: [],
                f1: '',
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
                this.id= this.$route.query.id;
                this.libPres();
                
        },
        methods: {
            modificar(){
                const gens = this.inputs.map(input => {
                    return {
                        genero: input
                    };
                });
                console.log(gens);
                this.libro.generos=gens;
                axios.post( 'http://localhost:8080/Libro/mod',this.libro).then(response => {
                    console.log(response.data);
                    console.log('Libro cargado correctamente');
                    this.$router.push('/ListaLibros')
                })
                .catch(error => {
                    const er=error.response.data;
                      console.log('Error: ', mensajes.obtenerMensajePorId(er));
                      window.alert('Error: '+ mensajes.obtenerMensajePorId(er));
                });
            },
            async libPres(){
                try {
                    const response = await axios.get('http://localhost:8080/Libro/id/'+this.id);
                    console.log(response.data);
                    this.libro=response.data;
                    for(const genero of this.libro.generos){
                        let g=genero.genero;
                        this.inputs.push(g);
                    }
                    
                    this.f1=this.libro.fep;
                } catch (error) {
                    console.error('Error al obtener datos:', error);
                }
            },
            dia(){
                const today = new Date(this.f1);
                const year = today.getFullYear();
                let month = today.getMonth() + 1;
                month = month < 10 ? '0' + month : month;
                let day = today.getDate();
                day++;
                day = day < 10 ? '0' + day : day;

                return `${day}-${month}-${year}`;
            },
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
            },

        }
    }
</script>