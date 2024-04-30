<template>
    <div>
        <h1>Listado de libros</h1>
        <button> <RouterLink to="/Main"> Volver al inicio </RouterLink> </button>
        <button> <RouterLink to="/ListaPrestamo"> Listado de prestamos </RouterLink> </button>
        <button> <RouterLink to="/RegisLibro"> Registrar nuevo libro </RouterLink> </button>
        <select v-model="opS">
            <option value="0">Sin filtros</option>
            <option value="1">Titulo</option>
            <option value="2">Autor</option>
            <option value="3">Cantidad de paginas</option>
            <option value="4">Fecha de publicacion</option>
            <option value="5">Publicados entre...</option>
        </select>
    <div>
        <input type="text" v-if="opS >= 1 && opS <= 2" v-model="texto">
        <input type="number" v-if="opS == 3" v-model="num"  :min="1">
        <input type="number" v-if="opS == 4" v-model="an" >
        <input type="date" v-if="opS == 5" v-model="f1" :max="fHoy">
        <input type="date" v-if="opS == 5" v-model="f2"  :max="fHoy" :min="f1">
    </div>
        

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
            <tr v-for="libro in libros" :key="libro.lId">
                <Libro :libro="libro" @eliminar="elm(libro.lId)" @prestar="prt(libro.lId)" @modificar="mod(libro.lId)"/>
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
                texto: "",
                num: 1,
                an: 2023,
                f1: new Date(),
                f2: new Date(),
                fHoy:'',
                fechas: [],
            };
        },
        mounted(){
            this.flibros(0);

            //Obtener la fecha actual en formato YYYY-MM-DD
                const today = new Date();
                const year = today.getFullYear();
                let month = today.getMonth() + 1;
                month = month < 10 ? '0' + month : month;
                let day = today.getDate();
                day =day;
                day = day < 10 ? '0' + day : day;

                // Asignar la fecha actual a la propiedad fechaActual
                this.fHoy = `${year}-${month}-${day}`;
                console.log(this.fHoy);

        },
        methods: {
            async flibros(tip){
                try {
                    if(tip==0){
                        const response = await axios.get('http://localhost:8080/Libro');
                        this.libros=response.data;
                        console.log(this.libros);
                    }else if(tip==1){
                        const response = await axios.get('http://localhost:8080/Libro/tit/'+this.texto);
                        this.libros=response.data;
                        console.log(this.libros);
                    }else if(tip==2){
                        const response = await axios.get('http://localhost:8080/Libro/aut/'+this.texto);
                        this.libros=response.data;
                        console.log(this.libros);
                    }else if(tip==3){
                        const response = await axios.get('http://localhost:8080/Libro/cant/'+this.num);
                        this.libros=response.data;
                        console.log(this.libros);
                    }else if(tip==4){
                        console.log(typeof this.an)
                        const response = await axios.get('http://localhost:8080/Libro/fep/'+this.an);
                        this.libros=response.data;
                        console.log(this.libros);
                    }
                } catch (error) {
                    this.libros='';
                    
                }
                if(tip==5){
                    console.log(this.f1)
                    console.log(this.dia(this.f1,false))
                    this.fechas=[];
                    this.fechas.push(this.dia(this.f1,false),this.dia(this.f2,true));
                    axios.post( 'http://localhost:8080/Libro/fec',this.fechas).then(response => {
                        this.libros=response.data;
                        console.log(this.libros);
                    })
                    .catch(error => {
                        console.log(error.response.data);
                        this.libros='';
                    });
                }
            },
            elm(id){
                axios.delete( 'http://localhost:8080/Libro/del/'+id ).then(response => {
                    this.flibros(0);
                })
                    .catch(error => {
                        const er=error.response.data;
                      console.log('Error: ', mensajes.obtenerMensajePorId(er));
                      window.alert('Error: '+ mensajes.obtenerMensajePorId(er));
                });
            },
            prt(idL){
                this.$router.push({
                    name: 'RegisPrestamo',
                    query: { id:idL }
                });
            },
            dia(today,t){
                today=new Date(today);
                console.log(today);
                const year = today.getFullYear();
                let month = today.getMonth() + 1;
                month = month < 10 ? '0' + month : month;
                let day = today.getDate();
                day = day < 10 ? '0' + day : day;
                if(t==true)day+=2;
                
                // Asignar la fecha actual a la propiedad fechaActual
                return `${day}/${month}/${year}`;
            },
            mod(idL){
                this.$router.push({
                    name: 'ModLibro',
                    query: { id:idL }
                });
            },
        }
    }
</script>