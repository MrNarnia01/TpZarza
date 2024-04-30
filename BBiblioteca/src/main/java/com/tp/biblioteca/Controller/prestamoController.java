package com.tp.biblioteca.Controller;

import com.tp.biblioteca.Entity.Prestamo;
import com.tp.biblioteca.Service.servicePrestamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Prestamo")
public class prestamoController {
    @Autowired
    private servicePrestamo serP;

    @GetMapping
    public ResponseEntity<?> listarPres(){
        List<Prestamo> listP = serP.lis();
        if(listP.get(0).getpId()==-1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listP.get(0).getfInicio().getYear()+1900);
        }
        return ResponseEntity.ok(listP);
    }

    @GetMapping("/pre/{id}")
    public ResponseEntity<?> bPres(@PathVariable Long id){
        Prestamo pres = serP.busId(id);
        if (pres.getpId()==-1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pres.getfInicio().getYear()+1900);
        }
        return ResponseEntity.ok(pres);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> regisPres(@PathVariable Long id,@RequestBody Prestamo prestamo){
        System.out.println(id);
        Prestamo pres=serP.regis(prestamo,id);
        if(pres.getpId()==-1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pres.getfInicio().getYear()+1900);
        }
        return ResponseEntity.ok(pres);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>  borrarPres(@PathVariable Long id){
        if( !serP.busId(id).isbFin() ){
            serP.borrar(id);
            return ResponseEntity.ok(0);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(1009);
    }

    @PostMapping("/fec")
    public ResponseEntity<?> finPres(@RequestBody Prestamo prestamo){
        Prestamo pres = serP.finalizarPres(prestamo);
        if(pres.getpId()==-1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pres.getfInicio().getYear()+1900);
        }
        return ResponseEntity.ok(pres);
    }

    @PostMapping("/mod")
    public ResponseEntity<?> modPres(@RequestBody Prestamo prestamo){
        Prestamo pres = serP.mod(prestamo);
        if(pres.getpId()==-1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pres.getfInicio().getYear()+1900);
        }
        return ResponseEntity.ok(pres);
    }


    @GetMapping("/lib/{id}")
    public Long libPres(@PathVariable Long id){
        return serP.libPrestado(id);
    }

    @PostMapping("/fecha")
    public ResponseEntity<?> busFecPres(@RequestBody List<String> f) throws ParseException {
        List<Prestamo> listp = serP.presFec(f);
        if (listp.get(0).getpId()==-1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(listp.get(0).getfInicio().getYear()+1900);
        }
        return ResponseEntity.ok(listp);
    }
}
