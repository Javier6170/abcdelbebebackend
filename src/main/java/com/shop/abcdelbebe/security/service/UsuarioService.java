package com.shop.abcdelbebe.security.service;

import com.shop.abcdelbebe.security.model.entities.Usuario;
import com.shop.abcdelbebe.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByCorreo(String correo){
        return usuarioRepository.findByCorreo(correo);
    }

    public Optional<Usuario> getByTokens(String tokenPassword){
        return usuarioRepository.findByTokenPassword(tokenPassword);
    }

    public boolean exitsByCorreo(String correo){
        return usuarioRepository.existsByCorreo(correo);
    }

    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }

}
