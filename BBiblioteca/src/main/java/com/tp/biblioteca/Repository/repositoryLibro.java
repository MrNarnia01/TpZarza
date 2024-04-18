package com.tp.biblioteca.Repository;

import com.tp.biblioteca.Entity.Libro;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface repositoryLibro extends JpaRepository<Libro, Long> {
    List<Libro> findByCantPag(int cantPag);
    List<Libro> findByTitulo(String titulo);
    List<Libro> findByAutor(String autor);
    @Query(value="SELECT id FROM Libro ORDER BY id DESC LIMIT 1",nativeQuery = true)
    Long searchByIdQueryNative();
    @Query(value="SELECT * FROM Libro WHERE EXTRACT(YEAR FROM fec_pu) = ?1 ",nativeQuery = true)
    List<Libro> searchByFePQueryNative(int y1);

    @Modifying
    @Transactional
    @Query(value="DELETE FROM libro_generos AS lg WHERE lg.libro_l_id=?1",nativeQuery = true)
    int borrarGen(Long id);
}
