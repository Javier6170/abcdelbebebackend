package com.shop.abcdelbebe.service;

import com.shop.abcdelbebe.model.entities.Producto;
import com.shop.abcdelbebe.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService {
    @Autowired
    ProductoRepository productoRepository;

    public Optional<Producto> getOne(long id){
        return productoRepository.findById(id);
    }

    public Optional<Producto> getByNombre(String nombre){
        return productoRepository.findByNombre(nombre);
    }

    public void save(Producto producto){
         productoRepository.save(producto);
    }

    public List<Producto> listaProductos(){
        return productoRepository.findAll();
    }

    public void delete(Producto producto){
        productoRepository.delete(producto);
    }

    public void delete(long id){
        productoRepository.deleteById(id);
    }

    public Producto buscarPorId(Long id){
        return productoRepository.getById(id);
    }

    public boolean existsById(long id){
        return productoRepository.existsById(id);
    }

    public boolean existsByNombre(String nombre){
        return productoRepository.existsByNombre(nombre);
    }
}
