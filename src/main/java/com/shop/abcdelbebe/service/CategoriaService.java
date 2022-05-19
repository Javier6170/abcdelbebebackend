package com.shop.abcdelbebe.service;

import com.shop.abcdelbebe.model.entities.Categoria;
import com.shop.abcdelbebe.model.entities.Producto;
import com.shop.abcdelbebe.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;

    public Optional<Categoria> getOne(long id){
        return categoriaRepository.findById(id);
    }

    public void save(Categoria categoria){categoriaRepository.save(categoria);}

    public void delete(Categoria categoria){categoriaRepository.delete(categoria);}

    public Optional<Categoria> getByNombre(String nombre){
        return categoriaRepository.findByNombreCategoria(nombre);
    }

    public List<Categoria> listaCategorias(){return categoriaRepository.findAll();}


    public Optional<Categoria> findByNombre(String nombreCategoria) {
        return categoriaRepository.findByNombreCategoria(nombreCategoria);
    }

    public void delete(long id){
        categoriaRepository.deleteById(id);
    }
    public boolean existsByIdCategory(long id){
        return categoriaRepository.existsById(id);
    }

    public boolean existsByNombreCategoria(String nombreCategoria){
        return categoriaRepository.existsByNombreCategoria(nombreCategoria);
    }
}
