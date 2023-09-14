package com.ecommerce.crazymonkey.controllers;

import com.ecommerce.crazymonkey.domain.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController//This tells Spring that this class is a Component of type RestController and capable of handling HTTP requests
@RequestMapping("/products")//This is a companion to @RestController that indicates which address requests must have to access this Controller
public class ProductRestController {

    @GetMapping("/{id}")//marks a method as a handler method. GET requests that match product/{requestedID} will be handled by this method.
    public ResponseEntity<Product> getPersonaById(@PathVariable Long id){//@PathVariable makes Spring Web aware of the requestedId supplied in the HTTP request. Now it’s available for us to use in our handler method
        return null;
//        return ResponseEntity.ok(product);
//        return ResponseEntity.notFound().build();
    }

//    @GetMapping
//    public List<Persona> listPersonas(Long id){
//        return this.personas;
//    }

    @PostMapping
    public ResponseEntity createPersonas(@RequestBody Product product){

// TODO: 13/09/2023 adicionar a la db

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("{nombre}")
                .buildAndExpand(product.getName())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePersonas(@PathVariable Long id){

        return ResponseEntity.noContent().build();
    }



}
