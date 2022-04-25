package com.shop.abcdelbebe.repository;

import com.shop.abcdelbebe.model.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
