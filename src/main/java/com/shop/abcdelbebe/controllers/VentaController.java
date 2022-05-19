package com.shop.abcdelbebe.controllers;

import com.shop.abcdelbebe.dto.Mensaje;
import com.shop.abcdelbebe.model.entities.Imagen;
import com.shop.abcdelbebe.model.entities.Producto;
import com.shop.abcdelbebe.model.entities.Venta;
import com.shop.abcdelbebe.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/ventas")
@CrossOrigin
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @PostMapping("/nuevo")
    public ResponseEntity<?> crearVenta(@Valid @RequestBody Venta venta) throws IOException {
        ventaService.saveVenta(venta);
        return new ResponseEntity(new Mensaje("Se ha generado la venta con exito"), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")long id){
        if(!ventaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        ventaService.delete(id);
        return new ResponseEntity(new Mensaje("venta eliminada"), HttpStatus.OK);
    }

    @GetMapping
    public List<Venta> listaVentas(){
        return ventaService.listaVentas();
    }


}
