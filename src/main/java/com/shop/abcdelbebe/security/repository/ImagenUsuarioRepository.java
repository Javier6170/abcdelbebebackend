package com.shop.abcdelbebe.security.repository;

import com.shop.abcdelbebe.model.entities.Imagen;
import com.shop.abcdelbebe.security.model.entities.ImagenUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ImagenUsuarioRepository extends JpaRepository<ImagenUsuario, Long> {

    Optional<ImagenUsuario> findByName(String name);
}
