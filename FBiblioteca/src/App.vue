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
      this.$router.push('/InicioSesion');
      this.usuario=null;
    }
  }
}
</script>

<template>
  <header>
    <div v-if="usuario == null" @usua="recibirUsuario" class="opciones"> 
      <nav>
        <ul>
          <li>Sin sesion activa
          </li>
        </ul>
      </nav>
    </div>
    <div v-else>
      <div>
          <nav>
            <ul>
              <li >Usuario {{ this.usuario.nU }} conectado</li>
              <li @click="infoUsuario" class="boton"> Configuracion </li>
              <li @click="nulear" class="boton"> Cerrar Sesion </li>
            </ul>
          </nav>
        </div>
    </div>
  </header>
  
  <RouterView @usua="recibirUsuario" @mod="nulear"></RouterView>
</template>

<style scoped>

</style>
