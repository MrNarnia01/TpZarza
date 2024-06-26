package com.tp.biblioteca.Controller;

import com.tp.biblioteca.Entity.Genero;
import com.tp.biblioteca.Entity.Libro;
import com.tp.biblioteca.Repository.repositoryLibro;
import com.tp.biblioteca.Service.serviceGenero;
import com.tp.biblioteca.Service.serviceLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@CrossOrigin
@RestController
@RequestMapping("/Libro")
public class libroController {
    @Autowired
    private serviceLibro serL;

    @GetMapping
    public ResponseEntity<?> listadoLibro(){
        List<Libro> listl = serL.lis();
        if (listl.get(0).getTitulo().equals("Error")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listl.get(0).getCantPag());
        }
        return ResponseEntity.ok(listl);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> unLibro(@PathVariable Long id){
        Libro libro = serL.busId(id);
        if (libro.getTitulo().equals("Error")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(libro.getCantPag());
        }
        return ResponseEntity.ok(libro);
    }
    @DeleteMapping("/del/{id}")
    public void borrarLib(@PathVariable Long id){
        serL.borrar(id);
    }
    @GetMapping("/tit/{tit}")
    public ResponseEntity<?> libTi(@PathVariable String tit){
        List<Libro> listl = serL.busTit(tit);
        if (listl.get(0).getTitulo().equals("Error")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listl.get(0).getCantPag());
        }
        return ResponseEntity.ok(listl);
    }

    @GetMapping("/aut/{autor}")
    public ResponseEntity<?> libAut(@PathVariable String autor){
        List<Libro> listl = serL.busAut(autor);
        if (listl.get(0).getTitulo().equals("Error")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listl.get(0).getCantPag());
        }
        return ResponseEntity.ok(listl);
    }

    @GetMapping("/cant/{pag}")
    public ResponseEntity<?> libPag(@PathVariable int pag){
        List<Libro> listl = serL.busCantPag(pag);
        if (listl.get(0).getTitulo().equals("Error")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listl.get(0).getCantPag());
        }
        return ResponseEntity.ok(listl);
    }

    @GetMapping("/fep/{fep}")
    public ResponseEntity<?> libFep(@PathVariable int fep){
        List<Libro> listl = serL.busFep(fep);
        if (listl.get(0).getTitulo().equals("Error")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listl.get(0).getCantPag());
        }
        return ResponseEntity.ok(listl);
    }

    @GetMapping("/ult")
    public Long ultLibro(){
        return serL.ultimo();
    }

    @PostMapping("/fec")
    public ResponseEntity<?> fecLib(@RequestBody List<String> f) throws ParseException {
        List<Libro> listl = serL.busFec(f);
        if (listl.get(0).getTitulo().equals("Error")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listl.get(0).getCantPag());
        }
        return ResponseEntity.ok(listl);
    }

    @PostMapping("/reg")
    public Libro regisLib(@RequestBody Libro libro){
        return serL.regis(libro);
    }

    @PostMapping("/mod")
    public Libro regisMod(@RequestBody Libro libro){
        return serL.mod(libro);
    }


}
