package com.shop.abcdelbebe.repository;

import com.shop.abcdelbebe.model.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
