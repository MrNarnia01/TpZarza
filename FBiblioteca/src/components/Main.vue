<script>
import { RouterLink } from 'vue-router';

</script>
<template>
    <header>
        <nav>
            <li><RouterLink to="/ListaLibros">Libros</RouterLink></li>
            <li><RouterLink to="/ListaPrestamo">Prestamo</RouterLink></li>
        </nav>
    </header>
    <h1>Bienvenido a la biblioteca</h1>
</template>