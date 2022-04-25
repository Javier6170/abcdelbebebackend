package com.shop.abcdelbebe.controllers;

import com.shop.abcdelbebe.dto.Mensaje;
import com.shop.abcdelbebe.model.entities.Imagen;
import com.shop.abcdelbebe.model.entities.Producto;
import com.shop.abcdelbebe.repository.ImagenRepository;
import com.shop.abcdelbebe.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@RequestMapping("api/productos")
@CrossOrigin
public class ProductoController {
    @Autowired
    private ProductoService prodcutoService;

    @PostMapping("/nuevo")
    public ResponseEntity<?> crearProducto(@Valid  @RequestBody Producto producto) throws IOException {
        prodcutoService.save(producto);
        return new ResponseEntity(new Mensaje("Se ha añadido con exito tu producto"), HttpStatus.CREATED);
    }

    @DeleteMapping
    public void eliminarProducto(@Valid @RequestBody Producto producto) {
        prodcutoService.delete(producto);
    }

    @GetMapping("/{id}")
    public Producto buscarProductoPorId(@PathVariable("id") Long id) {
        return prodcutoService.buscarPorId(id);
    }

    @GetMapping
    public List<Producto> listaProductos(){
        return prodcutoService.listaProductos();
    }

}
