package com.tp.biblioteca.Controller;

import com.tp.biblioteca.Service.serviceUsuario;
import com.tp.biblioteca.Entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Usuario")
public class usuarioController {
    @Autowired
    private serviceUsuario serU;

    @GetMapping
    public ResponseEntity<?> listaUsuario() {
        List<Usuario> listU = serU.lis();
        if(listU.get(0).getnU().equals("Error")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listU.get(0).getCon());
        }
        return ResponseEntity.ok(listU);
    }

    @PostMapping("/nom")
    public ResponseEntity<?> inicioSesion(@RequestBody Usuario usu){
        Usuario us = serU.inicioSesion(usu);
        if(us.getnU().equals("Error")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(us.getCon());
        }
        return ResponseEntity.ok("Inicio exitoso");
    }
    @PostMapping
    public ResponseEntity<?> regisUsuario(@RequestBody Usuario usuario) {
        Usuario usu = serU.regis(usuario);
        if(usu.getnU().equals("Error")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usu.getCon());
        }
        return ResponseEntity.ok(usu);
    }

    @DeleteMapping("/del/{nom}")
    public void borrarUsuario(@PathVariable String nom){
        serU.borrar(nom);
    }
}