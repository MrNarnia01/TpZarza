<script>
import { RouterLink } from 'vue-router';

</script>
<template>
    <table>
        <tr>
            <td colspan="2">
                <h1>Bienvenido a la biblioteca</h1>
            </td>
        </tr>
        <tr>
            <td class="boton"><RouterLink to="/ListaLibros" class="router-link">Libros</RouterLink></td>
            <td class="boton"><RouterLink to="/ListaPrestamo" class="router-link">Prestamo</RouterLink></td>
        </tr>
    </table>
</template>

<style>
    
</style>