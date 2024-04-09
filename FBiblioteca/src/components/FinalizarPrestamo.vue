<template>
    <h2> Devolucion del libro {{ libro.titulo }} </h2>
    <h3>Info</h3>
    <h3>Fecha De prestado {{ fechaFormateada(prestamo.fInicio) }}</h3>
    <table>
        <tr>
            <td><input type="date" v-model="fD" :min="fMin"></td>
            <td><button type="button">Devolver</button></td>
        </tr>
    </table>


</template>
<script>
    
    import axios from 'axios';
    export default{
        data(){
            return{
                prestamo:Object,
                libro:Object,
                id:0,
                fMin: new Date(),
                fD: new Date(),
            }
        },
        mounted(){
            this.id=this.$route.query.id;
            console.log(this.id)
            this.carga();
            this.recLibro();
        },
        methods:{
            async carga(){
                try {
                    const response = await axios.get('http://localhost:8080/Prestamo/pre/'+this.id);
                    console.log(response.data);
                    this.prestamo=response.data;
                    this.fMin=new Date(this.prestamo.fInicio);

                } catch (error) {
                    console.error('Error al obtener datos:', error);
                }
            },
            async recLibro(){
                try {
                    const response = await axios.get('http://localhost:8080/Prestamo/lib/'+this.id);
                    this.id=response.data;
                    this.libPres();
                } catch (error) {
                    const er=error.response.data;
                      console.log('Error: ');
                      
                }
            },
            async libPres(){
                try {
                    const response = await axios.get('http://localhost:8080/Libro/id/'+this.id);
                    this.libro=response.data;
                } catch (error) {
                    console.error('Error al obtener datos:', error);
                }
            },fechaFormateada(f1) {
                const fecha= new Date(f1);
                const dia = fecha.getDate(); // Obtener el día del mes
                const mes = fecha.getMonth() + 1; // Obtener el mes (los meses van de 0 a 11)
                const año = fecha.getFullYear(); // Obtener el año

                // Formatear la fecha en el formato 'dd/mm/yyyy'
                const fechaFormateada = `${this.agregarCero(dia)}/${this.agregarCero(mes)}/${año}`;

                return fechaFormateada;
            },agregarCero(valor) {
                return valor < 10 ? '0' + valor : valor; // Agregar un cero delante si es menor que 10
            },
        }
    }

</script>


