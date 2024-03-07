<template>
    <div>
        <h1>Listado de libros</h1>
        <button> <RouterLink to="/Main"> Volver al inicio </RouterLink> </button>
        <button> <RouterLink to="/ListaPrestamo"> Listado de prestamos </RouterLink> </button>
        <button> <RouterLink to="/RegisLibro"> Registrar nuevo libro </RouterLink> </button>
        <select v-model="opS" @change="flibros(opS)">
            <option value="0">Sin filtros</option>
            <option value="1">Titulo</option>
            <option value="2">Autor</option>
            <option value="3">Genero</option>
            <option value="4">Fecha de publicacion</option>
            <option value="5">Publicados entre...</option>
        </select>
        <input type="text" v-model="list" @change="flibros(opS)">
        <button @click="flibros(opS)">Buscar</button>
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
                opS: 0,
                list: "",
                
            };
        },
        mounted(){
            this.flibros(0);
        },
        methods: {
            async flibros(tip){
                try {
                    if(tip==0){
                        const response = await axios.get('http://localhost:8080/Libro');
                        this.libros=response.data;
                        console.log(this.libros);
                    }else if(tip==1){
                        const response = await axios.get('http://localhost:8080/Libro/tit/'+this.list);
                        this.libros=response.data;
                        console.log(this.libros);
                    }else if(tip==2){
                        const response = await axios.get('http://localhost:8080/Libro/aut/'+this.list);
                        this.libros=response.data;
                        console.log(this.libros);
                    }else if(tip==3){
                        console.log("Pendiente de hacer");
                    }else if(tip==4){
                        console.log("Pendiente de hacer");
                    }else if(tip==5){
                        console.log("Pendiente de hacer");
                    }
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