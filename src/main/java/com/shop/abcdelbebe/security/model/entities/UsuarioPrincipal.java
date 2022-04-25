package com.shop.abcdelbebe.security.model.entities;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UsuarioPrincipal implements UserDetails {
    private String nombre;
    private String telefono;
    private String correo;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return correo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UsuarioPrincipal(String nombre, String telefono, String correo, String password, Collection<? extends GrantedAuthority> authorities) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.password = password;
        this.authorities = authorities;
    }


    public static UsuarioPrincipal build(Usuario usuario) {
        List<GrantedAuthority> authorities =
                usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
                        .getNombre().name())).collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getNombre(), usuario.getTelefono(), usuario.getCorreo(), usuario.getPassword(), authorities);
    }

}
