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
                  this.$emit('usua', response.data)
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
  
  <form @submit.prevent="inicio()">
      <table>
        <tr>
          <td colspan="2"><h1>Inicio de sesion</h1></td>
        </tr>
        <tr>
          <td>
            <label for="nU">Nombre de usuario </label>
          </td>
          <td>
            <input type="text" name="nU" id="nU" v-model="Usuario.nU" required>
          </td>
        </tr>
        <tr>
          <td>
            <label for="con">Contraseña </label>
          </td>
          <td>
            <input type="password" name="con" id="con" v-model="Usuario.con" required>
          </td>
        </tr>
        <tr>
          <td colspan="2">
            <button type="submit">Iniciar Sesion</button>
            <button @click="crear()">Crear Cuenta</button>
          </td>
        </tr>
      </table>
  </form>
</template>

<style scoped>
</style>
