<script lang="ts">
  import axios from 'axios'
  import mensajes from './mensajes';
  export default {
    data() {
      return {
        Usuario: {
          nU: '',
          con: ''
        }
      };
    },
    methods: {
      crear(){
          axios.post( 'http://localhost:8080/Usuario',this.Usuario ).then(response => {
                  console.log(response.data);
                  this.$router.push('/InicioSesion');
                })
                    .catch(error => {
                      const er=error.response.data;
                      console.log('Error: ', mensajes.obtenerMensajePorId(er));
                      window.alert('Error: '+ mensajes.obtenerMensajePorId(er));
                });
        },
        inicio(){
          console.log(this.$router.push('/InicioSesion'));
        },
    }
  }

</script>

<template>
<form @submit.prevent="crear()">
  <table>
    <tr>
      <td colspan="2"><h1>Crear Usuario</h1></td>
    </tr>
    <tr>
      <td><label for="nU">Nombre de usuario </label></td>
      <td><input type="text" name="nU" id="nU" v-model="Usuario.nU" required></td>
    </tr>
    <tr>
      <td><label for="con">Contraseña </label></td>
      <td><input type="password" name="con" id="con" v-model="Usuario.con" required></td>
    </tr>
    <tr>
      <td colspan="2">
        <button type="submit">Crear</button>
        <button @click="inicio()">Volver al registro</button>
      </td>
    </tr>
    
  </table>
</form>
</template>

<style scoped>
</style>
