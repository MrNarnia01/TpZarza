<template>
    <div>
        <h1>Registro de libro</h1>
        <button> <RouterLink to="/Main"> Volver al inicio </RouterLink> </button>
        <button> <RouterLink to="/ListaLibros"> Listado de libros </RouterLink> </button>
    </div>
    <div>
        <form @submit.prevent="crear">
            <label for="tit">Titulo: </label>
            <input type="text" name="tit" id="tit" v-model="NewLibro.titulo" required>

            <label for="aut">Autor: </label>
            <input type="text" name="aut" id="aut" v-model="NewLibro.autor" required>

            <label for="sin">Sinopsis: </label>
            <input type="text" name="sin" id="sin" v-model="NewLibro.sinopsis" required>

            <label for="cap">Paginas: </label>
            <input type="number" name="cap" id="cap" v-model="NewLibro.cantPag" required min="1" value="1">

            <label for="fep">Fecha de publicacion: </label>
            <input type="date" name="fep" id="fep" v-model="NewLibro.fep" required :max="fHoy">

                <div v-for="(input, index) in inputs" :key="index">
                    <h3>Genero: {{ index+1 }}</h3>
                    <input v-model="inputs[index]" type="text" required/>
                    <button @click="eliminarInput(index)">Eliminar</button>
                </div>

            <button type="submit">Crear Libro</button>
        </form>
        <button @click="agregarInput">Agregar Genero</button>
    </div>

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
            crear(){
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
                this.inputs.splice(index, 1); // Elimina el input en la posición 'index'
            }
        }
    }
</script>