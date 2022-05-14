package com.shop.abcdelbebe.repository;

import com.shop.abcdelbebe.model.entities.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Long> {

    Optional<Imagen> findByName(String name);
}
