package com.shop.abcdelbebe.repository;

import com.shop.abcdelbebe.model.entities.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ImagenRepository extends JpaRepository<Imagen, Long> {

    Optional<Imagen> findByName(String name);
}
