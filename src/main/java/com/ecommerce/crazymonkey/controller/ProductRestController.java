package com.ecommerce.crazymonkey.controller;

import com.ecommerce.crazymonkey.domain.Product;
import com.ecommerce.crazymonkey.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController//This tells Spring that this class is a Component of type RestController and capable of handling HTTP requests
@RequestMapping("/products")//This is a companion to @RestController that indicates which address requests must have to access this Controller
public class ProductRestController {

    private ProductRepository productRepository;

    public ProductRestController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/{requestedId}")
    public ResponseEntity<Product> findById(@PathVariable Long requestedId) {
        Optional<Product> productOptional = productRepository.findById(requestedId);
        if (productOptional.isPresent()) {
            return ResponseEntity.ok(productOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll(Pageable pageable) {
        Page<Product> page = productRepository.findAll(
                PageRequest.of(
                        pageable.getPageNumber(),
                        pageable.getPageSize(),
                        pageable.getSortOr(Sort.by(Sort.Direction.ASC, "id"))
                ));
        return ResponseEntity.ok(page.getContent());
    }

    @PostMapping
    private ResponseEntity<Void> createProduct(@RequestBody Product newProductRequest, UriComponentsBuilder ucb) {
        Product savedProduct = productRepository.save(newProductRequest);
        URI locationOfNewCashCard = ucb
                .path("product/{id}")
                .buildAndExpand(savedProduct.getId())
                .toUri();
        return ResponseEntity.created(locationOfNewCashCard).build();
    }

    @PutMapping("/{requestedId}")
    private ResponseEntity<Void> putProduct(@PathVariable Long requestedId, @RequestBody Product productUpdate) {
        Optional<Product> product = productRepository.findById(requestedId);
        if (product.isPresent()) {
            Product updatedProduct = new Product(requestedId, productUpdate.getName(), productUpdate.getLastName());
            productRepository.save(updatedProduct);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
