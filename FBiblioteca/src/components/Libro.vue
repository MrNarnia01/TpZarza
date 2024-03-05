<template>
    <td> {{ libro.titulo }} </td>
    <td> {{ libro.autor }} </td>
    <td> {{ libro.sinopsis }} </td>
    <td> {{ libro.cantPag }} </td>

    <td> {{ genUnidos() }} </td>
        <td> {{ fechaFormateada(libro.fep) }} </td>
    <td>
        <button>Modificar</button>
    </td>

    
    <td v-if="this.libro.prestamos==''"><button @click="elm">Eliminar</button></td>
    <td v-else>-</td>
    
    <td v-if="!prestado()"><button @click="prt">Prestar</button></td>
    <td v-else>-</td>
    

</template>
<script>
    import  axios  from 'axios';
    export default {
        props: {
            libro:Object,
        },
        methods: {
            elm() {
                this.$emit('eliminar', this.libro.id)
            },
            genUnidos() {
                const allGen = this.libro.generos.map(genero => genero.genero);
                return allGen.join(', ');
            },
            fechaFormateada(f1) {
                const fecha= new Date(`${f1}`);
                const dia = fecha.getDate(); // Obtener el día del mes
                const mes = fecha.getMonth() + 1; // Obtener el mes (los meses van de 0 a 11)
                const año = fecha.getFullYear(); // Obtener el año

                // Formatear la fecha en el formato 'dd/mm/yyyy'
                const fechaFormateada = `${this.agregarCero(dia)}/${this.agregarCero(mes)}/${año}`;

                return fechaFormateada;
            },
            agregarCero(valor) {
                return valor < 10 ? '0' + valor : valor; // Agregar un cero delante si es menor que 10
            },
            prt(){
                this.$emit('prestar', this.libro.id)
            },
            prestado(){
                const pres=this.libro.prestamos;
                console.log("prestamos");
                console.log(pres);
                for(let i=0; i<pres.lenght;i++){
                    if(pres[i].bFin){
                        return true;
                    }
                }
                return false;
            },
        }
    };



</script>