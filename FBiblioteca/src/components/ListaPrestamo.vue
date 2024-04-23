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
            <Prestamo :prestamo="prestamo" @eliminar="elm(prestamo.pId)" @devo="fDev(prestamo)" @modificar="mod(prestamo)"/>
        </tr>
    </table>
    <div v-if="this.modi == 1">
        <h3>Modificacion de prestamo</h3>
        <input type="date" v-model="f1" :max="fHoy">
        <h4>Fecha anterior: {{ fechaFormateada(prestamo.fInicio) }}</h4>
        <button @click="modi=0">Cancelar</button>
        <button @click="modif">Modificar</button>
    </div>
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
                prestamo:Object,
                modi:0,
                f1: new Date(),
                fHoy:'',
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
                this.fHoy = `${day}/${month}/${year}`;
        },
        methods: {
            async fprestamo(){
                try {
                    const response = await axios.get('http://localhost:8080/Prestamo');
                    this.prestamos=response.data;
                } catch (error) {
                    const er=error.response.data;
                      console.log('Error: ', mensajes.obtenerMensajePorId(er));
                    this.prestamos='';
                }
            },
            elm(id){
                axios.delete( 'http://localhost:8080/Prestamo/'+id ).then(response => {
                    this.fprestamo();
                })
                    .catch(error => {
                        const er=error.response.data;
                      console.log('Error: ', mensajes.obtenerMensajePorId(er));
                      window.alert('Error: '+ mensajes.obtenerMensajePorId(er));
                });
            },
            mod(prestamo){
                this.prestamo=prestamo;
                this.modi=1;
                console.log(this.modi);
            },
            fDev(prestamo){
                this.prestamo=prestamo;
                this.prestamo.fFin=new Date();
                this.prestamo.bFin=true;
                axios.post( 'http://localhost:8080/Prestamo/fec',this.prestamo ).then(response => {
                    console.log(response.data);
                    this.fprestamo();
                })
                    .catch(error => {
                        const er=error.response.data;
                      console.log('Error: ', mensajes.obtenerMensajePorId(er));
                      window.alert('Error: '+ mensajes.obtenerMensajePorId(er));
                });
            },
            fechaFormateada(f1) {
                const fecha= new Date(f1.substring(0,10)+"T00:00:00");
                const dia = fecha.getDate(); // Obtener el día del mes
                const mes = fecha.getMonth() + 1; // Obtener el mes (los meses van de 0 a 11)
                const año = fecha.getFullYear(); // Obtener el año

                // Formatear la fecha en el formato 'dd/mm/yyyy'
                const fechaFormateada = `${this.agregarCero(dia)}/${this.agregarCero(mes)}/${año}`;

                return fechaFormateada;
            },
            agregarCero(valor) {
                return valor < 10 ? '0' + valor : valor; // Agregar un cero delante si es menor que 10
            },
            modif(){
                this.prestamo.fInicio=this.f1;
                axios.post( 'http://localhost:8080/Prestamo/mod',this.prestamo ).then(response => {
                    console.log(response.data);
                    this.fprestamo();
                    this.modi=0;
                })
                    .catch(error => {
                        const er=error.response.data;
                      console.log('Error: ', mensajes.obtenerMensajePorId(er));
                      window.alert('Error: '+ mensajes.obtenerMensajePorId(er));
                });
            },
        }
    }
</script>