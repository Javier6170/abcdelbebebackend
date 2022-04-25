package com.shop.abcdelbebe.service;

import com.shop.abcdelbebe.model.entities.Producto;
import com.shop.abcdelbebe.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductoService {
    @Autowired
    ProductoRepository productoRepository;

    public void save(Producto producto){
         productoRepository.save(producto);
    }

    public List<Producto> listaProductos(){
        return productoRepository.findAll();
    }

    public void delete(Producto producto){
        productoRepository.delete(producto);
    }

    public Producto buscarPorId(Long id){
        return productoRepository.getById(id);
    }
}
