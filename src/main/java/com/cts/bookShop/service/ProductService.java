package com.cts.bookShop.service;

import com.cts.bookShop.ServiceInterf.ProductInterf;
import com.cts.bookShop.dao.ProductDao;
import com.cts.bookShop.entity.Product;
import com.cts.bookShop.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductInterf {
    @Autowired
    private ProductDao productDao;

    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    public Product addProduct(Product product) {
        return productDao.save(product);
    }

    public ResponseEntity<Product> getProductId(Integer id) {
        Product product = productDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not Found with id" + id));
        return ResponseEntity.ok(product);
    }

    public ResponseEntity<Product> updateProduct(Integer id, Product newProduct) {
        Product product = productDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not Found with id" + id));
        product.setISBN(newProduct.getISBN());
        product.setAuthor(newProduct.getAuthor());
        product.setCategory(newProduct.getCategory());
        product.setName(newProduct.getName());
        product.setImgUrl(newProduct.getImgUrl());
        product.setDescription(newProduct.getDescription());
        product.setQuantity(newProduct.getQuantity());
        product.setPrice(newProduct.getPrice());
        Product updatedProduct = productDao.save(product);
        return ResponseEntity.ok(updatedProduct);
    }

    public ResponseEntity<Product> deleteProduct(Integer id) {
        Product product = productDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not Found with id" + id));
        productDao.delete(product);
        return ResponseEntity.ok(product);
    }

}
