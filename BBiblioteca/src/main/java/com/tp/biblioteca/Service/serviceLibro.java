package com.tp.biblioteca.Service;

import com.tp.biblioteca.Entity.Genero;
import com.tp.biblioteca.Entity.Libro;
import com.tp.biblioteca.Entity.Prestamo;
import com.tp.biblioteca.Repository.repositoryGenero;
import com.tp.biblioteca.Repository.repositoryLibro;
import com.tp.biblioteca.Repository.repositoryPrestamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class serviceLibro {

    @Autowired
    private repositoryLibro repoL;

    @Autowired
    private repositoryGenero repoG;

    @Autowired
    private repositoryPrestamo repoP;

    public List<Genero> gen(Long id) {
        Optional<Libro> libro = repoL.findById(id);
        return libro.get().getGeneros();
    }

    public Libro busId(Long id){
        Libro error= new Libro();
        error.setCantPag(1001);
        error.setTitulo("Error");
        return repoL.findById(id).orElse(error);
    }

    public List<Libro> lis(){
        if(repoL.findAll().isEmpty()){
            Libro error= new Libro();
            error.setCantPag(1002);
            error.setTitulo("Error");
            List<Libro> er=new ArrayList<>();
            er.add(error);
            return er;
        }
        return repoL.findAll();
    }

    public List<Libro> busAut(String Autor){
        if(repoL.findByAutor(Autor).isEmpty()){
            Libro error= new Libro();
            error.setCantPag(1002);
            error.setTitulo("Error");
            List<Libro> er=new ArrayList<>();
            er.add(error);
            return er;
        }
        return repoL.findByAutor(Autor);
    }

    public List<Libro> busTit(String Titulo){
        if(repoL.findByTitulo(Titulo).isEmpty()){
            Libro error= new Libro();
            error.setCantPag(1002);
            error.setTitulo("Error");
            List<Libro> er=new ArrayList<>();
            er.add(error);
            return er;
        }
        return repoL.findByTitulo(Titulo);
    }

    public List<Libro> busCantPag(int cantPag){
        if(repoL.findByCantPag(cantPag).isEmpty()){
            Libro error= new Libro();
            error.setCantPag(1002);
            error.setTitulo("Error");
            List<Libro> er=new ArrayList<>();
            er.add(error);
            return er;
        }
        return repoL.findByCantPag(cantPag);
    }

    public List<Libro> busFep(int fep){
        if(repoL.searchByFePQueryNative(fep).isEmpty()){
            Libro error= new Libro();
            error.setCantPag(1002);
            error.setTitulo("Error");
            List<Libro> er=new ArrayList<>();
            er.add(error);
            return er;
        }
        return repoL.searchByFePQueryNative(fep);
    }

    public Long ultimo(){
        return repoL.searchByIdQueryNative();
    }

    public List<Libro> busFec(List<String> fechas) throws ParseException {
        List<Libro> fec=repoL.findAll();
        SimpleDateFormat form=new SimpleDateFormat("dd/MM/yyyy");
        Date f1=form.parse(fechas.get(0));
        Date f2=form.parse(fechas.get(1));
        for (int i = 0; i < fec.size(); i++) {
            if(f1.toInstant().isBefore(fec.get(i).getFep().toInstant())
                && f2.toInstant().isAfter(fec.get(i).getFep().toInstant()) ){

                System.out.println("Indice correcto: "+i);

            }else{
                System.out.println(f1);
                System.out.println(f1.toInstant());
                System.out.println(f2);
                System.out.println(f2.toInstant());
                System.out.println(f1.toInstant().isBefore(fec.get(i).getFep().toInstant()));
                System.out.println(f2.toInstant().isAfter(fec.get(i).getFep().toInstant()));
                System.out.println("Indice removido: "+i);
                fec.remove(i);
                i--;
            }
        }

        if(fec.isEmpty()){
            Libro error= new Libro();
            error.setCantPag(1002);
            error.setTitulo("Error");
            List<Libro> er=new ArrayList<Libro>();
            er.add(error);
            return er;
        }
        return fec;
    }

    public void borrar(Long id) {
        repoL.deleteById(id);
    }

    public Libro regis(Libro libro) {
        List<Genero> generos = libro.getGeneros();
        libro.setGeneros(null);
        libro.setPrestamos(null);
        Libro lib = repoL.save(libro);

        List<Genero> gen=repoG.saveAll(generos);

        lib.setGeneros(gen);
        lib.setPrestamos(new ArrayList<Prestamo>());
        return repoL.save(lib);
    }

    public Libro mod(Libro libro) {
        Optional<Libro> lib=repoL.findById(libro.getlId());
        List<Genero> generos = lib.get().getGeneros();
        repoL.borrarGen(libro.getlId());
        repoG.deleteAll(generos);
        generos = libro.getGeneros();

        List<Genero> gen=repoG.saveAll(generos);

        libro.setGeneros(gen);
        return repoL.save(libro);

        /*
        Optional<Libro> lib=repoL.findById(libro.getlId());
        List<Genero> listLib1=libro.getGeneros();
        List<Genero> listLib2=lib.get().getGeneros();
        List<Genero> generosABorrar = new ArrayList<>();
        for (Genero genero : listLib1) {
            boolean encontrado = false;
            for (Genero g : listLib2) {
                if (genero.getGenero().equals(g.getGenero())) {
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                generosABorrar.add(genero);
            }
        }

        for (Genero genero : generosABorrar){
            repoL.borrarGen(genero.getgId());
            repoG.deleteById(genero.getgId());
        }

        List<Genero> gen=repoG.saveAll(listLib1);

        libro.setGeneros(gen);


        return repoL.save(libro);*/
    }

    public List<Prestamo> pres(Long id) {
        Libro libro = repoL.findById(id).orElse(null);
        if (libro != null) {
            if(libro.getPrestamos().isEmpty()){
                return null;
            }
            return libro.getPrestamos();
        }
        return null;
    }

    /*
    public boolean allPresEnd(Long id) {
        Libro libro = repoL.findById(id).orElse(null);
        if (libro != null) {
            for (int i = 0; i < libro.getPrestamos().stream().count(); i++) {
                if (!libro.getPrestamos().get(i).isbFin()) return false;
            }
        }
        return true;
    }
    */
}
