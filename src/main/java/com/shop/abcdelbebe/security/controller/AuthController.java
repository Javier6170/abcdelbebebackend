package com.shop.abcdelbebe.security.controller;

import com.shop.abcdelbebe.dto.Mensaje;
import com.shop.abcdelbebe.security.dto.JwtDto;
import com.shop.abcdelbebe.security.dto.LoginUsuario;
import com.shop.abcdelbebe.security.dto.NuevoUsuario;
import com.shop.abcdelbebe.security.enums.RolNombre;
import com.shop.abcdelbebe.security.jwt.JwtProvider;
import com.shop.abcdelbebe.security.model.entities.Rol;
import com.shop.abcdelbebe.security.model.entities.Usuario;
import com.shop.abcdelbebe.security.service.RolService;
import com.shop.abcdelbebe.security.service.UsuarioService;
import com.shop.abcdelbebe.sendMail.dto.WelcomeUserDTO;
import com.shop.abcdelbebe.sendMail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin()
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    EmailService emailService;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario,WelcomeUserDTO dto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);
        }
        if (usuarioService.exitsByCorreo(nuevoUsuario.getCorreo())) {
            return new ResponseEntity(new Mensaje("ya existe este usuario"), HttpStatus.BAD_REQUEST);
        }
        dto.setMailFrom(mailFrom);
        dto.setMailTo(nuevoUsuario.getCorreo());
        emailService.sendEmailWelcome(dto);
        Usuario usuario = new Usuario(nuevoUsuario.getNombre(),nuevoUsuario.getTelefono(), nuevoUsuario.getCorreo()
                , passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByNombre(RolNombre.ROLE_USER).get());
        if (nuevoUsuario.getRoles()!= null) {
            if (nuevoUsuario.getRoles().contains("admin"))
                roles.add(rolService.getByNombre(RolNombre.ROLE_ADMIN).get());
        }
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("Usuario Guardado"), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Los campos del correo y contraseña son invalidos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getCorreo(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }

    @GetMapping("/recoverPassword/{correo}")
    public Optional<Usuario> recoverPasswordUser(@Valid @PathVariable(name = "correo") String correo){
        if (usuarioService.exitsByCorreo(correo)){
            return usuarioService.getByCorreo(correo);
        }
        return null;
    }

}
