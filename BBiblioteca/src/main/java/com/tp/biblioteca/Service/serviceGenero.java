package com.tp.biblioteca.Service;

import com.tp.biblioteca.Entity.Genero;
import com.tp.biblioteca.Entity.Prestamo;
import com.tp.biblioteca.Repository.repositoryGenero;
import com.tp.biblioteca.Repository.repositoryLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class serviceGenero {
    @Autowired
    private repositoryGenero repoG;
    @Autowired
    private repositoryLibro repoL;
    public Genero busId(Long id){
        Genero genC = repoG.findById(id).orElse(null);
        if(genC==null){
            Genero gen =new Genero();
            gen.setGenero("Error");
            gen.setgId(Long.parseLong("1001"));
            return gen;
        }
        return genC;
    }

    public List<Genero> lis(){
        if(repoG.findAll().isEmpty()){
            Genero gen =new Genero();
            gen.setGenero("Error");
            gen.setgId(Long.parseLong("1002"));

            List<Genero> er=new ArrayList<>();
            er.add(gen);
            return er;
        }
        return repoG.findAll();
    }
    public void borrar(Long id){ repoG.deleteById(id); }

    public Genero regis(Genero gen){
        Genero gene=repoG.save(gen);
        return gene;
    }


}
