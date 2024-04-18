<template>
    <td> {{ libro.titulo }} </td>
    <td> {{ fechaFormateada(prestamo.fInicio) }} </td>
    <td v-if="prestamo.bFin==false">-</td>
    <td align="center" v-else> {{ fechaFormateada(prestamo.fFin) }} </td>
    <td>
        <button>Modificar</button>
    </td>
    <td>
        <button @click="elm">Eliminar</button>
    </td>
    <td v-if="prestamo.bFin==false">
        <button @click="fDev">Devolver</button>
    </td>

</template>
<script>
    import  axios  from 'axios';
    import mensajes from './mensajes';
    export default {
        props: {
            prestamo: Object,
        },
        data(){
            return {
                libro: Object,
                id: 0,
            };
        },
        mounted(){
            this.recLibro();
        },
        methods: {
            elm() {
                this.$emit('eliminar', this.prestamo.pId)
            },
            async recLibro(){
                try {
                    const response = await axios.get('http://localhost:8080/Prestamo/lib/'+this.prestamo.pId);
                    this.id=response.data;
                    this.libPres();
                } catch (error) {
                    const er=error.response.data;
                      console.log('Error: ', mensajes.obtenerMensajePorId(er));
                      
                }
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
            fDev() {
                this.$emit('devo', this.prestamo)
            },
            async libPres(){
                try {
                    const response = await axios.get('http://localhost:8080/Libro/id/'+this.id);
                    this.libro=response.data;
                } catch (error) {
                    console.error('Error al obtener datos:', error);
                }
            }
        }
    };



</script>