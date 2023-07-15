package com.example.stripe_libreriasalvador.service;

import com.example.stripe_libreriasalvador.model.Articulo;
import com.example.stripe_libreriasalvador.repository.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional (readOnly = true)
public class ArticuloService {

    @Autowired
    ArticuloRepository articuloRepository;

    public List<Articulo> lista(){
        List<Articulo> lista = articuloRepository.findAll();
        return lista;
    }

    public Optional<Articulo> getById(long id){
        return  articuloRepository.findById(id);
    }

    public boolean existsId(long id){
        return articuloRepository.existsById(id);
    }
}
