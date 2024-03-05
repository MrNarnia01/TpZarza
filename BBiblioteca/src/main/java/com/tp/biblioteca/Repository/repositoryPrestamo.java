package com.tp.biblioteca.Repository;

import com.tp.biblioteca.Entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface repositoryPrestamo extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findByIdL(Long idL);

    @Query(value="SELECT * FROM Prestamo WHERE bFin=false AND idL=?1",nativeQuery = true)
    List<Prestamo> searchByEnd(Long idL);

}
