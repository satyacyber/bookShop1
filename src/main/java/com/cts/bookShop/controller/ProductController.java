package com.cts.bookShop.controller;

import com.cts.bookShop.entity.Product;
import com.cts.bookShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping({"/products"})
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping({"/products"})
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping({"/products/{id}"})
    public ResponseEntity<Product> getProductId(@PathVariable Integer id) {
        return productService.getProductId(id);
    }

    @PutMapping({"/products/{id}"})
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product newProduct) {
        return productService.updateProduct(id, newProduct);
    }

    @DeleteMapping({"/products/{id}"})
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }


}
