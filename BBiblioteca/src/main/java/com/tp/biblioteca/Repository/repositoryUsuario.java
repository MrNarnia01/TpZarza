package com.tp.biblioteca.Repository;

import com.tp.biblioteca.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface repositoryUsuario extends JpaRepository<Usuario, String> {
}