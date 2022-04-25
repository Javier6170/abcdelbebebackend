package com.shop.abcdelbebe.security.service;

import com.shop.abcdelbebe.security.model.entities.Usuario;
import com.shop.abcdelbebe.security.model.entities.UsuarioPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.getByCorreo(correo).get();
        return UsuarioPrincipal.build(usuario);
    }
}
