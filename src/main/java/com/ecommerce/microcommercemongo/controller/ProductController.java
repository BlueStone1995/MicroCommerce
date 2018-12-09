package com.ecommerce.microcommercemongo.controller;

import com.ecommerce.microcommercemongo.repository.ProductRepository;
import com.ecommerce.microcommercemongo.exceptions.ProductNotFindException;
import com.ecommerce.microcommercemongo.model.Product;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@Api(description = "Gestion des produits")
@RestController
@RequestMapping("/products")
public class ProductController {

    // Couche données
    @Autowired
    @Qualifier("productRepository")
    private ProductRepository productRepository;

    // Produits
    @GetMapping(value = "/")
    public List<Product> productList() {
        return productRepository.findAll();
    }

    // Produits/{id}
    @ApiOperation(value = "Récupère un produit selon son ID")
    @GetMapping(value = "/{id}")
    public Product getProduit(@PathVariable("id") ObjectId id) throws ProductNotFindException {

        Product product = productRepository.findBy_id(id);

        if (product == null) throw new ProductNotFindException("Product " + id + " not found... ");

        return product;
    }

    // Post Product
    @PostMapping(value = "/")
    public ResponseEntity<Void> addProduct(@Valid @RequestBody Product product) {
        product.set_id(ObjectId.get());
        Product productSend = productRepository.save(product);

        if (product == null) {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(productSend.get_id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    // Update Product
    @PutMapping(value = "/{id}")
    public void updateProduct(@PathVariable("id") ObjectId id, @Valid @RequestBody Product product) {
        product.set_id(id);
        productRepository.save(product);
    }

    // Delete product
    @DeleteMapping(value = "/{id}")
    public void deleteProduct(@PathVariable("id") ObjectId id) {
        productRepository.delete(productRepository.findBy_id(id));
    }
}
