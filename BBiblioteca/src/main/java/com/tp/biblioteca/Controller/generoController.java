package com.tp.biblioteca.Controller;

import com.tp.biblioteca.Entity.Genero;
import com.tp.biblioteca.Service.serviceGenero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Genero")
public class generoController {
    @Autowired
    private serviceGenero serG;

    @GetMapping
    public ResponseEntity<?> listaGen(){
        List<Genero> listG = serG.lis();
        if(listG.get(0).getGenero().equals("Error")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listG.get(0).getgId());
        }
        return ResponseEntity.ok(listG);
    }

    @GetMapping("/lib/{id}")
    public ResponseEntity<?> generos(@PathVariable Long id){
        Genero gen = serG.busId(id);
        if (gen.getGenero().equals("Error")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(gen.getgId());
        }
        return ResponseEntity.ok(gen);
    }

    @DeleteMapping("/del/{id}")
    public void borrarGen(@PathVariable Long id){
        serG.borrar(id);
    }

    @PostMapping()
    public Genero newGenero(Genero genero){
        return serG.regis(genero);
    }

}
