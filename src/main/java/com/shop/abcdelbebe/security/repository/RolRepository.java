package com.shop.abcdelbebe.security.repository;

import com.shop.abcdelbebe.security.enums.RolNombre;
import com.shop.abcdelbebe.security.model.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombre(RolNombre nombre);

}
