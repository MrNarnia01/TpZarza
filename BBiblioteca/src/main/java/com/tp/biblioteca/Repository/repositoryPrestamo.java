package com.tp.biblioteca.Repository;

import com.tp.biblioteca.Entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface repositoryPrestamo extends JpaRepository<Prestamo, Long> {

    @Query(value="SELECT Prestamo.* FROM Prestamo,libro_prestamos AS lp WHERE Prestamo.b_Fin=false AND lp.libro_l_id=?1",nativeQuery = true)
    List<Prestamo> searchByEnd(Long idL);

    @Query(value="SELECT lp.libro_l_id FROM libro_prestamos AS lp WHERE lp.prestamos_p_id=?1",nativeQuery = true)
    Long searchByLib(Long id);

}
