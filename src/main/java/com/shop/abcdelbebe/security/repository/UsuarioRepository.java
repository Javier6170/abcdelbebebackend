package com.shop.abcdelbebe.security.repository;

import com.shop.abcdelbebe.security.model.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByCorreo(String correo);
    Optional<Usuario> findByTokenPassword(String tokenPassword);
    boolean existsByCorreo(String correo);
}
