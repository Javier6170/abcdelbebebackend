package com.shop.abcdelbebe.controllers;

import com.shop.abcdelbebe.dto.Mensaje;
import com.shop.abcdelbebe.model.entities.Categoria;
import com.shop.abcdelbebe.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/categoria")
@CrossOrigin
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;


    @PostMapping("/nuevo")
    public ResponseEntity<?> crearProducto(@Valid  @RequestBody  Categoria categoria) {
        categoriaService.save(categoria);
        return new ResponseEntity(new Mensaje("Se ha añadido con exito tu categoria"), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> eliminarCategoria(@Valid @RequestBody Categoria categoria) {
        categoriaService.delete(categoria);
        return new ResponseEntity(new Mensaje("Se ha eliminado con exito tu categoria"), HttpStatus.ACCEPTED);
    }

    @PostMapping("/categoriaByNombre")
    public Optional<Categoria> categoriaByNombre(String nombre_categoria){
        return categoriaService.findByNombre(nombre_categoria);
    }

    @GetMapping("/listaCategoria")
    public List<Categoria> listaCategorias(){
        return categoriaService.listaCategorias();
    }
}
