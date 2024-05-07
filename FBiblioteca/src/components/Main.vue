<script>
import { RouterLink } from 'vue-router';

</script>
<template>
    <h1>Bienvenido a la biblioteca</h1>
    <header>
        <div class="opciones">
            <nav>
                <ul>
                <li><RouterLink to="/ListaLibros">Libros</RouterLink></li>
                <li><RouterLink to="/ListaPrestamo">Prestamo</RouterLink></li>
                </ul>
            </nav>
        </div>
    </header>
    
</template>

<style>
    
</style>