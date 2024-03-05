export default {
    mensajes: [
      { id: 1001, texto: 'Entidad no encontrada' },
      { id: 1002, texto: 'Lista vacia o inexistente' },
      { id: 1003, texto: 'Datos erroneos' },
      { id: 1004, texto: 'Usuario no encontrado' },
      { id: 1005, texto: 'Contraseña incorrecta' },
      { id: 1006, texto: 'Usuario ya en uso' },
      { id: 1007, texto: 'Usuario con caracteres insuficientes' },
      { id: 1008, texto: 'Contraseña con caracteres insuficientes' },
      { id: 1009, texto: 'No se puede borrar o modificar el prestamo debido a que ya aparece como terminado' },
      { id: 1010, texto: 'Aun quedan prestamos activos' },,
      { id: 0, texto: 'Prueba' },
      // Agrega más mensajes según sea necesario
    ],
    obtenerMensajePorId(id) {
      const men = this.mensajes.find(mensaje => mensaje.id === id);
      return men.texto;
    }
  };
