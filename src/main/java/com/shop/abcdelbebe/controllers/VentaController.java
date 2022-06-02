package com.shop.abcdelbebe.controllers;

import com.shop.abcdelbebe.dto.Mensaje;
import com.shop.abcdelbebe.model.entities.Imagen;
import com.shop.abcdelbebe.model.entities.Producto;
import com.shop.abcdelbebe.model.entities.Venta;
import com.shop.abcdelbebe.sendMail.dto.VentaUserDTO;
import com.shop.abcdelbebe.sendMail.dto.WelcomeUserDTO;
import com.shop.abcdelbebe.sendMail.service.EmailService;
import com.shop.abcdelbebe.service.ProductoService;
import com.shop.abcdelbebe.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

    @Autowired
    EmailService emailService;

    @Autowired
    private ProductoService prodcutoService;

    @Value("${spring.mail.username}")
    private String mailFrom;

    @PostMapping("/nuevo")
    public ResponseEntity<?> crearVenta(@Valid @RequestBody Venta venta, VentaUserDTO dto, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()){
            return new ResponseEntity(new Mensaje("campos mal puestos o email invalido"), HttpStatus.BAD_REQUEST);
        }
        Producto producto = prodcutoService.getOne(venta.getId_producto()).get();
        int cantidadAnterior = producto.getCantidad();
        if (cantidadAnterior <= 0) {
            return new ResponseEntity(new Mensaje("Producto agotado"), HttpStatus.BAD_REQUEST);
        }
        int cantidadNueva = cantidadAnterior - 1;
        ventaService.saveVenta(venta);
        dto.setMailFrom(mailFrom);
        dto.setMailTo(venta.getCorreoUsuario());
        producto.setCantidad(cantidadNueva);
        prodcutoService.save(producto);
        String estado = venta.getEstado();
        String NombreProducto = producto.getNombre();
        String direccion = venta.getDireccion();
        dto.setEstadoVenta(estado);
        dto.setNombreProducto(NombreProducto);
        dto.setDireccion(direccion);
        emailService.sendEmailventa(dto);

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
