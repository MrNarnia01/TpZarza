package com.tp.biblioteca.Service;

import com.tp.biblioteca.Entity.Genero;
import com.tp.biblioteca.Entity.Libro;
import com.tp.biblioteca.Entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tp.biblioteca.Repository.repositoryUsuario;

import java.util.ArrayList;
import java.util.List;

@Service
public class serviceUsuario {
    @Autowired
    private repositoryUsuario repoU;

    public void borrar(String nom) {
        repoU.deleteById(nom);
    }

    public Usuario regis(Usuario usu) {
        Usuario us = repoU.findById(usu.getnU()).orElse(null);
        if(us==null){
            if(usu.getnU().length()<8) {
                us = new Usuario();
                us.setnU("Error");
                us.setCon("1007");
                return us;
            }
            if(usu.getCon().length()<8) {
                us = new Usuario();
                us.setnU("Error");
                us.setCon("1008");
                return us;
            }
            return repoU.save(usu);
        }
        else{
            us=new Usuario();
            us.setnU("Error");
            us.setCon("1006");
            return us;
        }
    }

    public List<Usuario> lis(){
        if(repoU.findAll().isEmpty()){
            Usuario error= new Usuario();
            error.setCon("1002");
            error.setnU("Error");
            List<Usuario> er=new ArrayList<>();
            er.add(error);
            return er;
        }
        return repoU.findAll();
    }

    public Usuario inicioSesion(Usuario usu){
        Usuario us=repoU.findById(usu.getnU()).orElse(null);
        if(us==null){
            us=new Usuario();
            us.setnU("Error");
            us.setCon("1004");
            return us;
        }
        else if(us.getCon().equals(usu.getCon())){
            return us;
        }else{
            us=new Usuario();
            us.setnU("Error");
            us.setCon("1005");
            return us;
        }

    }

}
