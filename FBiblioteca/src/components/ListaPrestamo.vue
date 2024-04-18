<template>
    <div>
        <h1>Listado de prestamos</h1>
        <button> <RouterLink to="/Main"> Volver al inicio </RouterLink> </button>
        <button> <RouterLink to="/ListaLibros"> Listado de libros </RouterLink> </button>
    </div>
    <table>
        <tr>
            <th>Libro</th>
            <th>Fecha prestado</th>
            <th>Fecha devolucion</th>
            <th colspan="2"></th>
        </tr>
        <tr v-if="this.prestamos==''">
            <td colspan="3" align="center">No hay prestamos registrados</td>
        </tr>
        <tr v-for="prestamo in prestamos" :key="prestamo.pId">
            <Prestamo :prestamo="prestamo" @eliminar="elm(prestamo.pId)" @devo="fDev(prestamo.pId)"/>
        </tr>
    </table><!---
    <div v-if="this.devol">
    <form @submit.prevent="diaDev">

        <label for="devF">Fecha devolucion: </label>
    
            <input type="date" name="devF" id="devF" v-model="NewPrestamo.fFin" required :max="fHoy">
            <button type="submit">Registrar devolucion</button>

    </form>
    </div> -->
</template>

<script>
    import { RouterLink } from 'vue-router';
    import axios from 'axios';
    import Prestamo from './Prestamo.vue';
    import mensajes from './mensajes';
    export default{
        components: {
            Prestamo
        },
        data(){
            return{
                prestamos: [],
                devol: false,
                NewPrestamo:{
                    idL: 0,
                    fIni:new Date(),
                    bFin:false,
                    fFin:new Date(),
                },
                fhoy: '',
            };
        },
        mounted(){
            this.fprestamo();
            const today = new Date();
                const year = today.getFullYear();
                let month = today.getMonth() + 1;
                month = month < 10 ? '0' + month : month;
                let day = today.getDate();
                day =day;
                day = day < 10 ? '0' + day : day;

                // Asignar la fecha actual a la propiedad fechaActual
                this.fHoy = `${year}-${month}-${day}`;
        },
        methods: {
            async fprestamo(){
                try {
                    const response = await axios.get('http://localhost:8080/Prestamo');
                    console.log(response.data);
                    this.prestamos=response.data;
                } catch (error) {
                    const er=error.response.data;
                      console.log('Error: ', mensajes.obtenerMensajePorId(er));
                }
            },
            elm(id){
                
            },
            fDev(id){
                this.$router.push({
                    name: 'FinalizarPrestamo',
                    query: { id:id }
                });
            },
            async diaDev(){
                axios.post( 'http://localhost:8080/Prestamo/fec',this.NewPrestamo ).then(response => {
                    if(response.data){
                        this.fprestamo;
                        this.devol=false;
                    }
                    else window.alert('Fecha invalida');

                })
                    .catch(error => {
                        const er=error.response.data;
                      console.log('Error: ', mensajes.obtenerMensajePorId(er));
                      window.alert('Error: '+ mensajes.obtenerMensajePorId(er));
                });
                /*
                try {
                    const response = await axios.get('http://localhost:8080/Prestamo/fec/'+this.idPres+'/'+this.devF);
                    if(response.data) this.fprestamo;
                    else window.alert('Fecha invalida');
                } catch (error) {
                    
                }*/
            }
        }
    }
</script>