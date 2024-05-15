package com.tp.biblioteca.Service;

import com.tp.biblioteca.Entity.Genero;
import com.tp.biblioteca.Entity.Libro;
import com.tp.biblioteca.Entity.Prestamo;
import com.tp.biblioteca.Repository.repositoryLibro;
import com.tp.biblioteca.Repository.repositoryPrestamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
            Calendar cal = Calendar.getInstance();

            // Establecer la fecha que deseas
            cal.set(1001, Calendar.MARCH, 7); // Año, mes (enero=0, febrero=1, ...), día

            // Obtener un objeto Date a partir del Calendar
            Date fechaInicio = cal.getTime();

            pres.setfInicio(fechaInicio);
            return pres;
        }
        return presC;
    }

    public List<Prestamo> lis(){
        if(repoP.findAll().isEmpty()){
            Prestamo pres =new Prestamo();
            pres.setpId(Long.parseLong("-1"));
            Calendar cal = Calendar.getInstance();

            // Establecer la fecha que deseas
            cal.set(1001, Calendar.MARCH, 7); // Año, mes (enero=0, febrero=1, ...), día

            // Obtener un objeto Date a partir del Calendar
            Date fechaInicio = cal.getTime();

            pres.setfInicio(fechaInicio);

            List<Prestamo> er= new ArrayList<>();
            er.add(pres);
            return er;
        }
        return repoP.findAll();
    }

    public Prestamo regis(Prestamo pres,Long id){
        if(repoP.searchByEnd(id).isEmpty()){
            Optional<Libro> lib=repoL.findById(id);
            Prestamo pre = repoP.save(pres);
            lib.get().setPrestamo(pre);
            repoL.save(lib.get());
            return pre;
        }
        Prestamo pre =new Prestamo();
        pre.setpId(Long.parseLong("-1"));

        Calendar cal = Calendar.getInstance();

        // Establecer la fecha que deseas
        cal.set(1010, Calendar.MARCH, 7); // Año, mes (enero=0, febrero=1, ...), día

        // Obtener un objeto Date a partir del Calendar
        Date fechaInicio = cal.getTime();

        pre.setfInicio(fechaInicio);
        return pre;
    }

    public Prestamo mod(Prestamo pres){
        Prestamo pre=repoP.findById(pres.getpId()).orElse(null);
        if(pre!=null){
            if(!pre.isbFin()){
                return repoP.save(pres);
            }
            pre.setpId(Long.parseLong("-1"));
            Calendar cal = Calendar.getInstance();

            // Establecer la fecha que deseas
            cal.set(1009, Calendar.MARCH, 7); // Año, mes (enero=0, febrero=1, ...), día

            // Obtener un objeto Date a partir del Calendar
            Date fechaInicio = cal.getTime();

            pre.setfInicio(fechaInicio);
            return pre;
        }
        pre.setpId(Long.parseLong("-1"));
        Calendar cal = Calendar.getInstance();

        // Establecer la fecha que deseas
        cal.set(1001, Calendar.MARCH, 7); // Año, mes (enero=0, febrero=1, ...), día

        // Obtener un objeto Date a partir del Calendar
        Date fechaInicio = cal.getTime();

        pre.setfInicio(fechaInicio);
        return pre;
    }

    public void borrar(Long id){
        repoP.borrarPres(id);
        repoP.deleteById(id);
    }

    public Prestamo finalizarPres(Prestamo pres){
        Prestamo pre=repoP.findById(pres.getpId()).orElse(null);
        if(pre!=null){
            if(!pre.isbFin()){
               return repoP.save(pres);
            }
        }
        pre.setpId(Long.parseLong("-1"));
        Calendar cal = Calendar.getInstance();

        // Establecer la fecha que deseas
        cal.set(1009, Calendar.MARCH, 7); // Año, mes (enero=0, febrero=1, ...), día

        // Obtener un objeto Date a partir del Calendar
        Date fechaInicio = cal.getTime();

        pre.setfInicio(fechaInicio);
        return pre;
    }

    public Long libPrestado(Long id){
        return repoP.searchByLib(id);
    }


    public List<Prestamo> presFec(List<String> fechas) throws ParseException {
        List<Prestamo> fec=repoP.findAll();
        SimpleDateFormat form=new SimpleDateFormat("dd/MM/yyyy");
        Date f1=form.parse(fechas.get(0));
        Date f2=form.parse(fechas.get(1));
        for (int i = 0; i < fec.size(); i++) {
            if(f1.toInstant().isBefore(fec.get(i).getfInicio().toInstant())
                    && f2.toInstant().isAfter(fec.get(i).getfInicio().toInstant()) ){

                System.out.println("Indice correcto: "+i);

            }else{
                System.out.println(f1);
                System.out.println(f1.toInstant());
                System.out.println(f2);
                System.out.println(f2.toInstant());
                System.out.println(f1.toInstant().isBefore(fec.get(i).getfInicio().toInstant()));
                System.out.println(f2.toInstant().isAfter(fec.get(i).getfInicio().toInstant()));
                System.out.println("Indice removido: "+i);
                fec.remove(i);
                i--;
            }
        }

        if(fec.isEmpty()){
            Prestamo pre=new Prestamo();

            pre.setpId(Long.parseLong("-1"));
            Calendar cal = Calendar.getInstance();

            // Establecer la fecha que deseas
            cal.set(1002, Calendar.MARCH, 7); // Año, mes (enero=0, febrero=1, ...), día

            // Obtener un objeto Date a partir del Calendar
            Date fechaInicio = cal.getTime();

            pre.setfInicio(fechaInicio);
            List<Prestamo> er=new ArrayList<Prestamo>();
            er.add(pre);
            return er;
        }
        return fec;
    }
}
