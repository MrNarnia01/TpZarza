<script lang="ts">
import type { RouterView,RouterLink } from 'vue-router';

import axios from 'axios';
export default {
  name: 'App',
  mounted() {
    console.log('App.vue se ha montado');
    this.$router.push('/InicioSesion');
  },
  data(){
    return{
      usuario:null,
      usu:0
      };
  },
  methods:{
    recibirUsuario(usuario){
      console.log("Prueba");
        this.usuario=usuario;
        this.usu=1;
    }
  }
}
</script>

<template>
  <div v-if="usuario == null" @usua="recibirUsuario"> 
    <h1> Sin sesion activa </h1>
  </div>
  <div v-else>
    <h1>
        Usuario {{ this.usuario.nU }} conectado
    </h1>
    </div>
  <RouterView @usua="recibirUsuario" ></RouterView>
</template>

<style scoped>

</style>
