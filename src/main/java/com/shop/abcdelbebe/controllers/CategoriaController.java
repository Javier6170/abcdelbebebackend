package com.shop.abcdelbebe.controllers;

import com.shop.abcdelbebe.dto.Mensaje;
import com.shop.abcdelbebe.model.entities.Categoria;
import com.shop.abcdelbebe.model.entities.Producto;
import com.shop.abcdelbebe.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/detail/{id}")
    public ResponseEntity<Categoria> getById(@PathVariable("id") int id){
        if(!categoriaService.existsByIdCategory(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Categoria categoria = categoriaService.getOne(id).get();
        return new ResponseEntity(categoria, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")long id, @RequestBody Categoria categoriaDTO){
        if(!categoriaService.existsByIdCategory(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(categoriaService.existsByNombreCategoria(categoriaDTO.getNombreCategoria()) && categoriaService.getByNombre(categoriaDTO.getNombreCategoria()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        Categoria categoria = categoriaService.getOne(id).get();
        categoria.setNombreCategoria(categoriaDTO.getNombreCategoria());;
        categoriaService.save(categoria);
        return new ResponseEntity(new Mensaje("categoria actualizada"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")long id){
        if(!categoriaService.existsByIdCategory(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        categoriaService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }
}
