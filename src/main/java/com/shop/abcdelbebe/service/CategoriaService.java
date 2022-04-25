package com.shop.abcdelbebe.service;

import com.shop.abcdelbebe.model.entities.Categoria;
import com.shop.abcdelbebe.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;

    public void save(Categoria categoria){categoriaRepository.save(categoria);}

    public void delete(Categoria categoria){categoriaRepository.delete(categoria);}

    public List<Categoria> listaCategorias(){return categoriaRepository.findAll();}


}
