<template>
    <h1>Configuracion del Usuario: {{ this.usuario.nU }}</h1>
    <button>Borrar usuario</button>
    <button @click="mod">Modificar usuario</button>
    <div v-if="modi!=0">
        <table>
            <tr>
                <td>Usuario:</td>
                <td><input type="text" name="usu" id="usu" v-model="usuario.nU"></td>
            </tr>
            <tr>
                <td>Contraseña</td>
                <td><input type="password" name="cont" id="cont" v-model="usuario.con"></td>
            </tr>
            <tr>
                <td><button @click="modificar">Modificar</button></td>
            </tr>
        </table>
    </div>
</template>
<script>
    import  axios  from 'axios';
    import mensajes from './mensajes';
    import { RouterLink } from 'vue-router';
export default{
        data(){
            return{
                usuario: {
                    uId:0,
                    nU: '',
                    con: ''
                },
                modi:0,
            };
        },
        mounted() {
            this.usuario.nU= this.$route.query.nU;
            this.usuario.con= this.$route.query.con;
            this.usuario.uId= this.$route.query.uId;
            console.log(this.usuario);    
        },
        methods: {
            mod(){
                this.modi=1;
            },
            modificar(){
                axios.post( 'http://localhost:8080/Usuario/mod',this.usuario ).then(response => {
                  this.$emit('mod');
                  this.$router.push('/InicioSesion');
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