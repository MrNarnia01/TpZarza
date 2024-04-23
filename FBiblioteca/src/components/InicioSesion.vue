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
        inicio(){
          axios.post( 'http://localhost:8080/Usuario/nom',this.Usuario ).then(response => {
                  this.$emit('usua', this.Usuario)
                  console.log(response.data);
                  
                  this.$router.push('/Main');
                })
                    .catch(error => {
                      const er=error.response.data;
                      console.log('Error: ', mensajes.obtenerMensajePorId(er));
                      window.alert('Error: '+ mensajes.obtenerMensajePorId(er));
                }); 
          /*
          try {
            const response = await axios.get('http://localhost:8080/Usuario/nom/'+this.Usuario.nU);
            console.log(response.data);
            if(response.data.con==this.Usuario.con)this.$router.push('/Main');
            else  window.alert('Contraseña incorrecta');
          } catch (error) {
            console.error('error',error);
            window.alert('Usuario incorrecto');
          }
          */
        },
        crear(){
          console.log(this.$router.push('/CrearCuenta'));
        },
    }
  }

</script>

<template>
  <h1>Inicio de sesion</h1>
  <form @submit.prevent="inicio()">
    <label for="nU">Nombre de usuario </label>
    <input type="text" name="nU" id="nU" v-model="Usuario.nU" required>
    <label for="con">Contraseña </label>
    <input type="password" name="con" id="con" v-model="Usuario.con" required>

    <button type="submit">Iniciar Sesion</button>
  </form>
  <button @click="crear()">Crear Cuenta</button>
</template>

<style scoped>
</style>
