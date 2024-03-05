<template>
    <div>
        <h1>Listado de libros</h1>
        <button> <RouterLink to="/Main"> Volver al inicio </RouterLink> </button>
        <button> <RouterLink to="/ListaPrestamo"> Listado de prestamos </RouterLink> </button>
        <button> <RouterLink to="/RegisLibro"> Registrar nuevo libro </RouterLink> </button>
    </div>
        <table>
            <tr>
                <th>Titulo</th>
                <th>Autor</th>
                <th>Sinopsis</th>
                <th>Paginas</th>
                <th>Genero/s</th>
                <th>Fecha de publicacion</th>
                <th colspan="3"></th>
            </tr>
            <tr v-if="this.libros==''">
                <td colspan="6" align="center">No hay libros registrados</td>
            </tr>
            <tr v-for="libro in libros" :key="libro.id">
                <Libro :libro="libro" @eliminar="elm(libro.id)" @prestar="prt(libro.id)"/>
            </tr>
        </table>


</template>

<script>
    import { RouterLink } from 'vue-router';
    import axios from 'axios';
    import Libro from './Libro.vue';
import { isThisSecond } from 'date-fns';
import mensajes from './mensajes';
    export default{
        components: {
            Libro
        },
        data(){
            return{
                libros: [],
            };
        },
        mounted(){
            this.flibros(0);
        },
        methods: {
            async flibros(tip){
                try {
                    const response = await axios.get('http://localhost:8080/Libro');
                    this.libros=response.data;
                    console.log(this.libros);
                } catch (error) {
                    this.libros='';
                }
            },
            elm(id){
                
            },
            prt(idL){
                const id=parseInt(idL);
                console.log(typeof(id));
                
                this.$router.push({
                    name: 'RegisPrestamo',
                    query: { id:idL }
                });
            }
        }
    }
</script>