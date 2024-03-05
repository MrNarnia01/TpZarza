package com.tp.biblioteca.Service;

import com.tp.biblioteca.Entity.Genero;
import com.tp.biblioteca.Entity.Libro;
import com.tp.biblioteca.Entity.Prestamo;
import com.tp.biblioteca.Repository.repositoryLibro;
import com.tp.biblioteca.Repository.repositoryPrestamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class servicePrestamo {
    @Autowired
    private repositoryPrestamo repoP;

    @Autowired
    private repositoryLibro repoL;


    public Prestamo busId(Long id){
        Prestamo presC = repoP.findById(id).orElse(null);
        if(presC==null){
            Prestamo pres =new Prestamo();
            pres.setpId(Long.parseLong("-1"));
            pres.setIdL(Long.parseLong("1001"));
            return pres;
        }
        return presC;
    }

    public List<Prestamo> lis(){
        if(repoP.findAll().isEmpty()){
            Prestamo pres =new Prestamo();
            pres.setpId(Long.parseLong("-1"));
            pres.setIdL(Long.parseLong("1001"));

            List<Prestamo> er= new ArrayList<>();
            er.add(pres);
            return er;
        }
        return repoP.findAll();
    }

    public Prestamo regis(Prestamo pres){
        if(repoP.searchByEnd(pres.getIdL()).isEmpty()){
            Prestamo pre = repoP.save(pres);
            repoL.findById(pre.getIdL()).get().setPrestamo(pre);
            return pre;
        }
        Prestamo pre =new Prestamo();
        pre.setpId(Long.parseLong("-1"));
        pre.setIdL(Long.parseLong("1010"));
        return pre;
    }

    public Prestamo mod(Prestamo pres){
        Prestamo pre=repoP.findById(pres.getpId()).orElse(null);
        if(pre!=null){
            if(!pre.isbFin()){
                return repoP.save(pres);
            }
            pre.setpId(Long.parseLong("-1"));
            pre.setIdL(Long.parseLong("1009"));
            return pre;
        }
        pre.setpId(Long.parseLong("-1"));
        pre.setIdL(Long.parseLong("1001"));
        return pre;
    }

    public void borrar(Long id){ repoP.deleteById(id); }

    public Prestamo finalizarPres(Prestamo pres){
        Prestamo pre=repoP.findById(pres.getpId()).orElse(null);
        if(pre!=null){
            if(pre.isbFin()){
               return repoP.save(pre);
            }
        }
        pre.setpId(Long.parseLong("-1"));
        pre.setIdL(Long.parseLong("1009"));
        return pre;
    }


}
