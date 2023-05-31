package com.cts.bookShop.controller;

import com.cts.bookShop.entity.Product;
import com.cts.bookShop.exception.InvalidIdException;
import com.cts.bookShop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping({"/products"})
//    @PreAuthorize(
//            "hasAuthority('ADMIN')"
//    )
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping({"/addProducts"})
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping({"/products/{id}"})
    public ResponseEntity<Product> getProductId(@PathVariable Integer id)  {
        if(productService.getProductId(id)==null){
            throw new InvalidIdException("Product ID not found");
        }
        return productService.getProductId(id);
    }

    @PutMapping({"/updateProducts/{id}"})
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product newProduct) throws NumberFormatException {
        return productService.updateProduct(id, newProduct);
    }

    @DeleteMapping({"/deleteProducts/{id}"})
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer id) {
        return productService.deleteProduct(id);
    }


}
