<script>
import { RouterLink } from 'vue-router';

</script>
<template>
    <h1>Bienvenido a la biblioteca</h1>
    
        <div class="opciones">
            <nav>
                <ul>
                <li><RouterLink to="/ListaLibros">Libros</RouterLink></li>
                <li><RouterLink to="/ListaPrestamo">Prestamo</RouterLink></li>
                </ul>
            </nav>
        </div>
    
</template>

<style>
    
</style>