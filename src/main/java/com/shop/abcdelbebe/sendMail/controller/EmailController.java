package com.shop.abcdelbebe.sendMail.controller;

import com.shop.abcdelbebe.dto.Mensaje;
import com.shop.abcdelbebe.security.model.entities.Usuario;
import com.shop.abcdelbebe.security.service.UsuarioService;
import com.shop.abcdelbebe.sendMail.dto.ChangePasswordDTO;
import com.shop.abcdelbebe.sendMail.dto.EmailValuesDTO;
import com.shop.abcdelbebe.sendMail.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController()
@RequestMapping("/email")
@CrossOrigin
public class EmailController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    EmailService emailService;

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/send")
    public ResponseEntity<?> sendEmail(){
        emailService.sendEmail();
        return new ResponseEntity(new Mensaje("Correo enviado con exito"), HttpStatus.OK);
    }

    @Value("${spring.mail.username}")
    private String mailFrom;

    @PostMapping("/send-html")
    public ResponseEntity<?> sendEmailTemplate(@Valid @RequestBody EmailValuesDTO dto){
        Optional<Usuario> usuarioOpt = usuarioService.getByCorreo(dto.getMailTo());
        if (!usuarioOpt.isPresent()) {
            return new ResponseEntity(new Mensaje("No existe ningún usuario con esas credenciales"), HttpStatus.NOT_FOUND);
        }
        Usuario usuario = usuarioOpt.get();
        dto.setMailFrom(mailFrom);
        dto.setSubject("Recuperar contraseña");
        dto.setUsername(usuario.getNombre());
        UUID uuid = UUID.randomUUID();
        String tokenPassword = uuid.toString();
        dto.setTokenPassword(tokenPassword);
        emailService.sendEmailTemplate(dto);
        usuario.setTokenPassword(tokenPassword);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("Correo con plantilla enviado con exito"), HttpStatus.OK);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDTO dto, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("Campos mal puestos"), HttpStatus.BAD_REQUEST);
        if (!dto.getPassword().equals(dto.getConfirmPassword()))
            return new ResponseEntity(new Mensaje("Las contraseñas no coinciden"), HttpStatus.BAD_REQUEST);
        Optional<Usuario> usuarioOpt = usuarioService.getByTokens(dto.getTokenPassword());
        if (!usuarioOpt.isPresent())
            return new ResponseEntity(new Mensaje("No existe ningún usuario con esas credenciales"), HttpStatus.NOT_FOUND);
        Usuario usuario = usuarioOpt.get();
        String newPassword = passwordEncoder.encode(dto.getPassword());
        usuario.setPassword(newPassword);
        usuario.setTokenPassword(null);
        usuarioService.save(usuario);
        return new ResponseEntity(new Mensaje("Se ha cambiado la contraseña correctamente"),HttpStatus.OK);
    }
}
