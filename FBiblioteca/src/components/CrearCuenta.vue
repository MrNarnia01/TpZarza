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
  <h1>Crear Usuario</h1>
  <form @submit.prevent="crear()">
    <label for="nU">Nombre de usuario </label>
    <input type="text" name="nU" id="nU" v-model="Usuario.nU" required>
    <label for="con">Contraseña </label>
    <input type="password" name="con" id="con" v-model="Usuario.con" required>

    <button type="submit">Crear</button>
    <button @click="inicio()">Volver al registro</button>
  </form>
</template>

<style scoped>
</style>
