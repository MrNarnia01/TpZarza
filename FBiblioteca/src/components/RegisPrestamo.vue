<template>
    <div>
        <h1>Registro de prestamo</h1>
        <button> <RouterLink to="/Main"> Volver al inicio </RouterLink> </button>
        <button> <RouterLink to="/ListaLibros"> Listado libros </RouterLink> </button>
        <button> <RouterLink to="/ListaPrestamo"> Listado prestamos </RouterLink> </button>
    </div>
    <div>
        <form @submit.prevent="crear">
            <label for="lib">Libro: </label>
            <input type="text" name="lib" id="lib" :placeholder="libro.titulo" readonly> 

            <label for="fIni">Fecha prestamo: </label>
            <input type="date" name="fIni" id="fIni" v-model="NewPrestamo.fIni" required :max="fHoy">

            <button type="submit">Registrar prestamo</button>
        </form>
    </div>

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
                day =day-1;
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