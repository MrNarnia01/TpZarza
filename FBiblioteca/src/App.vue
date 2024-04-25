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
    },
    infoUsuario(){
      console.log(this.usuario);
      this.$router.push({
        name: 'ModUsuario',
        query: { 
          nU:this.usuario.nU,
          con:this.usuario.con,
          uId:this.usuario.uId
         }
      });
    },
    nulear(){
      this.usuario=null;
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
        <button @click="infoUsuario" >Configuracion</button>
    </h1>
    </div>
  <RouterView @usua="recibirUsuario" @mod="nulear"></RouterView>
</template>

<style scoped>

</style>
