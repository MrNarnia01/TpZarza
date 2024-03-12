package com.tp.biblioteca.Repository;

import com.tp.biblioteca.Entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface repositoryLibro extends JpaRepository<Libro, Long> {
    List<Libro> findByCantPag(int cantPag);
    List<Libro> findByTitulo(String titulo);
    List<Libro> findByAutor(String autor);
    @Query(value="SELECT id FROM Libro ORDER BY id DESC LIMIT 1",nativeQuery = true)
    Long searchByIdQueryNative();
    @Query(value="?1",nativeQuery = true)
    List<Libro> searchByFecPuQueryNative(String datos);
    @Query(value="SELECT * FROM Libro WHERE EXTRACT(YEAR FROM fec_pu) = ?1 ",nativeQuery = true)
    List<Libro> searchByFePQueryNative(int y1);
}
