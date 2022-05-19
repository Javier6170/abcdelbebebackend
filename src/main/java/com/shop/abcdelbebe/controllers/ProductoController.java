package com.shop.abcdelbebe.controllers;

import com.shop.abcdelbebe.dto.Mensaje;
import com.shop.abcdelbebe.dto.ProductoDto;
import com.shop.abcdelbebe.model.entities.Imagen;
import com.shop.abcdelbebe.model.entities.Producto;
import com.shop.abcdelbebe.repository.ImagenRepository;
import com.shop.abcdelbebe.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/productos")
@CrossOrigin
public class ProductoController {
    @Autowired
    private ProductoService prodcutoService;

    @Autowired
    ImagenRepository imagenRepository;

    @PostMapping("/nuevo")
    public ResponseEntity<?> crearProducto(@Valid  @RequestBody Producto producto) throws IOException {
        prodcutoService.save(producto);
        return new ResponseEntity(new Mensaje("Se ha añadido con exito tu producto"), HttpStatus.CREATED);
    }

    @DeleteMapping
    public void eliminarProducto(@Valid @RequestBody Producto producto) {
        prodcutoService.delete(producto);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Producto> getById(@PathVariable("id") int id){
        if(!prodcutoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Producto producto = prodcutoService.getOne(id).get();
        return new ResponseEntity(producto, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")long id, @RequestBody ProductoDto productoDto){
        if(!prodcutoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        if(prodcutoService.existsByNombre(productoDto.getNombre()) && prodcutoService.getByNombre(productoDto.getNombre()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
        if(productoDto.getPrecio()==0 || productoDto.getPrecio()<0 )
            return new ResponseEntity(new Mensaje("el precio debe ser mayor que 0"), HttpStatus.BAD_REQUEST);
        if (productoDto.getCantidad() ==0 || productoDto.getCantidad() < 0)
            return new ResponseEntity(new Mensaje("Alemnos debe haber una cantidad"), HttpStatus.BAD_REQUEST);
        Producto producto = prodcutoService.getOne(id).get();
        producto.setNombre(productoDto.getNombre());
        producto.setPrecio(productoDto.getPrecio());
        producto.setCantidad(productoDto.getCantidad());
        producto.setDescripcion(productoDto.getDescripcion());
        prodcutoService.save(producto);
            return new ResponseEntity(new Mensaje("producto actualizado"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")long id){
        if(!prodcutoService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Producto producto = prodcutoService.getOne(id).get();
        String nameImagen = producto.getNombre_imagen();
        Imagen imagen = imagenRepository.findByName(nameImagen).get();
        imagenRepository.delete(imagen);
        prodcutoService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }

    @GetMapping
    public List<Producto> listaProductos(){
        return prodcutoService.listaProductos();
    }

}
