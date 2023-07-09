package com.cts.bookShop.controller;

import com.cts.bookShop.entity.Product;
import com.cts.bookShop.exception.InvalidIdException;
import com.cts.bookShop.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class ProductController {
    @Autowired
    private ProductService productService;
    private static final Logger logger = LogManager.getLogger(ProductController.class);
    @GetMapping({"/products"})
    @CrossOrigin(origins="http://localhost:3000")
    public List<Product> getAllProducts() {

        logger.info("Fetching all products");
        return productService.getAllProducts();
    }

    @PostMapping({"/addProducts"})
    @CrossOrigin(origins="http://localhost:3000")
    public Product addProduct(@RequestBody Product product) {
        logger.info("Adding a new product: {}", product);
        return productService.addProduct(product);
    }

    @GetMapping({"/products/{id}"})
    @CrossOrigin(origins="http://localhost:3000")
    public ResponseEntity<Product> getProductId(@PathVariable Integer id)  {
        logger.info("Fetching product by ID: {}", id);
        if(productService.getProductId(id)==null){
            throw new InvalidIdException("Product ID not found");
        }
        return productService.getProductId(id);
    }

    @PutMapping({"/updateProducts/{id}"})
    @CrossOrigin(origins="http://localhost:3000")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product newProduct) throws NumberFormatException {
        logger.info("Fetching product by ID: {}", id);
        return productService.updateProduct(id, newProduct);
    }

    @DeleteMapping({"/deleteProducts/{id}"})
    @CrossOrigin(origins="http://localhost:3000")
    public ResponseEntity<Product> deleteProduct(@PathVariable Integer id) {
        logger.info("Deleting product with ID: {}", id);
        return productService.deleteProduct(id);
    }


}
