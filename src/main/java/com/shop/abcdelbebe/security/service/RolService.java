package com.shop.abcdelbebe.security.service;

import com.shop.abcdelbebe.security.enums.RolNombre;
import com.shop.abcdelbebe.security.model.entities.Rol;
import com.shop.abcdelbebe.security.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RolService {
    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByNombre(RolNombre nombre){
        return rolRepository.findByNombre(nombre);
    }

    public void save (Rol rol){
        rolRepository.save(rol);
    }
}
