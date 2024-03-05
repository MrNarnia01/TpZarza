package com.tp.biblioteca.Repository;

import com.tp.biblioteca.Entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface repositoryLibro extends JpaRepository<Libro, Long> {
    List<Libro> findByTitulo(String titulo);
    List<Libro> findByAutor(String autor);
    @Query(value="SELECT id FROM Libro ORDER BY id DESC LIMIT 1",nativeQuery = true)
    Long searchByIdQueryNative();
    @Query(value="SELECT * FROM Libro WHERE fecPu BETWEEN '?1' AND '?2'",nativeQuery = true)
    List<Libro> searchByFecPuQueryNative(Date f1, Date f2);
}
