<script lang="ts">
import type { RouterView,RouterLink } from 'vue-router';

import axios from 'axios';
export default {
  name: 'App',
  mounted() {
    console.log('App.vue se ha montado');
    this.$router.push('/InicioSesion');
  }
}
</script>

<template>
  <RouterView></RouterView>
</template>

<style scoped>

</style>
