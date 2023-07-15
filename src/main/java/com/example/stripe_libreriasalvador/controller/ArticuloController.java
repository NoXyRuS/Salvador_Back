package com.example.stripe_libreriasalvador.controller;

import com.example.stripe_libreriasalvador.http.Mensaje;
import com.example.stripe_libreriasalvador.model.Articulo;
import com.example.stripe_libreriasalvador.service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articulo")
@CrossOrigin
public class ArticuloController {

    @Autowired
    ArticuloService articuloService;

    @GetMapping("/lista")
    public ResponseEntity<List<Articulo>>lista(){
        List<Articulo> lista = articuloService.lista();
        return new ResponseEntity<List<Articulo>>(lista, HttpStatus.OK);
    }

    @GetMapping("/detalle/{id}")
    public ResponseEntity<Articulo> detalle(@PathVariable("id") long id){
        if (!articuloService.existsId(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Articulo articulo = articuloService.getById(id).get();
        return new ResponseEntity<Articulo>(articulo, HttpStatus.OK);
    }
}
