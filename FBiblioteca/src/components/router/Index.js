import { createRouter, createWebHistory } from 'vue-router';
import Main from '../Main.vue';
import InicioSesion from '../InicioSesion.vue';
import CrearCuenta from '../CrearCuenta.vue';
import ListaLibros from '../ListaLibros.vue';
import RegisLibro from '../RegisLibro.vue';
import ListaPrestamo from '../ListaPrestamo.vue';
import RegisPrestamo from '../RegisPrestamo.vue';
import ModLibro from '../ModLibro.vue';
import ModUsuario from '../ModUsuario.vue';

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/Main',
            name: 'Main',
            component: Main
        },
        {
            path: '/InicioSesion',
            name: 'inicioSesion',
            component: InicioSesion
        },
        {
            path: '/CrearCuenta',
            name: 'CrearCuenta',
            component: CrearCuenta
        },
        {
            path: '/ListaLibros',
            name: 'ListaLibros',
            component: ListaLibros
        },
        {
            path: '/RegisLibro',
            name: 'RegisLibro',
            component: RegisLibro
        },
        {
            path: '/ListaPrestamo',
            name: 'ListaPrestamo',
            component: ListaPrestamo
        },
        {
            path: '/RegisPrestamo',
            name: 'RegisPrestamo',
            component: RegisPrestamo
        },
        {
            path: '/ModLibro',
            name: 'ModLibro',
            component: ModLibro
        },
        {
            path: '/ModUsuario',
            name: 'ModUsuario',
            component: ModUsuario
        }
    ]
})
export default router;