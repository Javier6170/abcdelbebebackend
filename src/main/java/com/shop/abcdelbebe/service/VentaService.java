package com.shop.abcdelbebe.service;

import com.shop.abcdelbebe.model.entities.Producto;
import com.shop.abcdelbebe.model.entities.Venta;
import com.shop.abcdelbebe.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VentaService {
    @Autowired
    VentaRepository ventaRepository;


    public Optional<Venta> getOne(long id){
        return ventaRepository.findById(id);
    }

    public Optional<Venta> getById(Long id){
        return ventaRepository.findById(id);
    }

    public void saveVenta(Venta venta){
        ventaRepository.save(venta);
    }

    public List<Venta> listaVentas(){
        return ventaRepository.findAll();
    }

    public void delete(Long id){
        ventaRepository.deleteById(id);
    }

    public boolean existsById(long id){
        return ventaRepository.existsById(id);
    }
}
