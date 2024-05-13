<template>
    <table>
        <tr>
            <td colspan="6">
                <h1>Listado de prestamos</h1>
            </td>
        </tr>
        <tr>
            <td class="boton" colspan="2"> <RouterLink to="/Main" class="router-link"> Volver al inicio </RouterLink> </td>
            <td class="boton" colspan="2"> <RouterLink to="/ListaLibros" class="router-link"> Listado de libros </RouterLink> </td>
            <td colspan="2">
                <select v-model="opS" @change="fprestamo(opS)">
                    <option value="0">Sin filtros</option>
                    <option value="1">Prestados entre...</option>
                    <option value="2">Por libro</option>
                </select>
            </td>
            
        </tr>
        
        <tr v-if="opS == 0">
                <td colspan="6"></td>
        </tr>
        <tr v-else>
            <td colspan="5">
                <input type="date" v-if="opS == 1" v-model="f1" :max="fHoy">
                <input type="date" v-if="opS == 1" v-model="f2"  :max="fHoy" :min="f1">
                <select v-model="lib" id="libros" v-if="opS == 2">
                    <option v-for="libro in listaLibros" :value="libro.lId" :key="libro.lId">{{ libro.titulo }}</option>
                </select>
            </td>
            <td class="boton" @click="fprestamo(opS)">Buscar</td>
        </tr>
        

    
        <tr>
            <th>Libro</th>
            <th>Fecha prestado</th>
            <th>Fecha devolucion</th>
            <th colspan="3"></th>
        </tr>
        <tr v-if="this.prestamos==''">
            <td colspan="6" >No hay prestamos registrados</td>
        </tr>
        <tr v-for="prestamo in prestamos" :key="prestamo.pId">
            <Prestamo :prestamo="prestamo" @eliminar="elm(prestamo.pId)" @devo="fDev(prestamo)" @modificar="mod(prestamo)"/>
        </tr>
    </table>
    <div v-if="this.modi == 1">
        <h3>Modificacion de prestamo</h3>
        <input type="date" v-model="f3" :max="fHoy">
        <h4>Fecha anterior: {{ fechaFormateada(prestamo.fInicio) }}</h4>
        <button @click="modi=0">Cancelar</button>
        <button @click="modif">Modificar</button>
    </div>
</template>

<script>
    import { RouterLink } from 'vue-router';
    import axios from 'axios';
    import Prestamo from './Prestamo.vue';
    import mensajes from './mensajes';
    export default{
        components: {
            Prestamo
        },
        data(){
            return{
                prestamos: [],
                prestamo:Object,
                modi:0,
                f1: new Date(),
                fHoy:'',
                opS:0,
                f2: new Date(),
                fechas: [],
                f3: new Date(),
                listaLibros: [],
                lib: null,
            };
        },
        mounted(){
            this.fprestamo(0);
            const today = new Date();
                const year = today.getFullYear();
                let month = today.getMonth() + 1;
                month = month < 10 ? '0' + month : month;
                let day = today.getDate();
                day =day;
                day = day < 10 ? '0' + day : day;

                // Asignar la fecha actual a la propiedad fechaActual
                this.fHoy = `${year}-${month}-${day}`;
                console.log(this.fHoy);
                this.libr();
                
        },
        methods: {
            async libr(){
                try {
                    const response = await axios.get('http://localhost:8080/Libro');
                    this.listaLibros=response.data;
                    console.log(this.listaLibros);
                }catch (error) {
                    this.listaLibros='';
                }
            },
            async fprestamo(tip){
                console.log(tip);
                try {
                    if(tip==0){
                        const response = await axios.get('http://localhost:8080/Prestamo');
                        this.prestamos=response.data;
                    }
                } catch (error) {
                    const er=error.response.data;
                      console.log('Error: ', mensajes.obtenerMensajePorId(er));
                    this.prestamos='';
                }
                if(tip==1){
                    console.log(this.f1)
                    console.log(this.dia(this.f1,false))
                    this.fechas=[];
                    this.fechas.push(this.dia(this.f1,false),this.dia(this.f2,true));
                    axios.post( 'http://localhost:8080/Prestamo/fecha',this.fechas).then(response => {
                        this.prestamos=response.data;
                        console.log(this.prestamos);
                    })
                    .catch(error => {
                        console.log(error.response.data);
                        this.prestamos='';
                    });
                }
                if(tip==2){
                    for(let i=0 ; i < this.listaLibros.length;i++){
                        if(this.listaLibros[i].lId==this.lib){
                            this.prestamos = this.listaLibros[i].prestamos;
                        }
                     }
                }
            },
            elm(id){
                axios.delete( 'http://localhost:8080/Prestamo/'+id ).then(response => {
                    this.fprestamo(0);
                })
                    .catch(error => {
                        const er=error.response.data;
                      console.log('Error: ', mensajes.obtenerMensajePorId(er));
                      window.alert('Error: '+ mensajes.obtenerMensajePorId(er));
                });
            },
            mod(prestamo){
                this.prestamo=prestamo;
                this.modi=1;
                console.log(this.modi);
            },
            fDev(prestamo){
                this.prestamo=prestamo;
                this.prestamo.fFin=new Date();
                this.prestamo.bFin=true;
                axios.post( 'http://localhost:8080/Prestamo/fec',this.prestamo ).then(response => {
                    console.log(response.data);
                    this.fprestamo(0);
                })
                    .catch(error => {
                        const er=error.response.data;
                      console.log('Error: ', mensajes.obtenerMensajePorId(er));
                      window.alert('Error: '+ mensajes.obtenerMensajePorId(er));
                });
            },
            fechaFormateada(f3) {
                const fecha= new Date(f3.substring(0,10)+"T00:00:00");
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
            modif(){
                this.prestamo.fInicio=this.f3;
                axios.post( 'http://localhost:8080/Prestamo/mod',this.prestamo ).then(response => {
                    console.log(response.data);
                    this.fprestamo(0);
                    this.modi=0;
                })
                    .catch(error => {
                        const er=error.response.data;
                      console.log('Error: ', mensajes.obtenerMensajePorId(er));
                      window.alert('Error: '+ mensajes.obtenerMensajePorId(er));
                });
            },
            dia(today,t){
                today=new Date(today);
                console.log(today);
                const year = today.getFullYear();
                let month = today.getMonth() + 1;
                month = month < 10 ? '0' + month : month;
                let day = today.getDate();
                day = day < 10 ? '0' + day : day;
                if(t==true)day+=2;
                
                // Asignar la fecha actual a la propiedad fechaActual
                return `${day}/${month}/${year}`;
            },
        }
    }
</script>