<template>
    <form @submit.prevent="crear">
        <table>
            <tr>
                <td colspan="3"><h1>Registro de prestamo</h1></td>
            </tr>

            <tr>
                <td class="boton"><RouterLink to="/Main" class="router-link"> Volver al inicio </RouterLink></td>
                <td class="boton"> <RouterLink to="/ListaLibros" class="router-link"> Listado libros </RouterLink> </td>
                <td class="boton"> <RouterLink to="/ListaPrestamo" class="router-link"> Listado prestamos </RouterLink> </td>
            </tr>
            
            <tr>
                <td>
                    <label for="lib">Libro: </label>
                    <input type="text" name="lib" id="lib" :placeholder="libro.titulo" readonly> 
                </td>
                <td>
                    <label for="fIni">Fecha prestamo: </label>
                    <input type="date" name="fIni" id="fIni" v-model="NewPrestamo.fInicio" required :max="fHoy">
                </td>
                <td><button type="submit">Registrar prestamo</button></td>
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
                NewPrestamo:{
                    fInicio:new Date(),
                    bFin:false,
                    fFin:new Date(),
                },
                fHoy:'',
                libro: Object,
                id:0,
                
            };
        },
        mounted() {
            // Obtener la fecha actual en formato YYYY-MM-DD
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
                this.id= this.$route.query.id;
                this.libPres();
                console.log(typeof(this.id))
        },
        methods: {
            crear(){
                axios.post( 'http://localhost:8080/Prestamo/'+this.id,this.NewPrestamo).then(response => {
                    console.log(this.NewPrestamo);
                    console.log('Prestamo caragado correctamente');
                    this.$router.push('/ListaPrestamo')

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
                } catch (error) {
                    console.error('Error al obtener datos:', error);
                }
            }

        }
    }
</script>