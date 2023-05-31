package com.cts.bookShop.ServiceInterf;

import com.cts.bookShop.entity.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductInterf {
     List<Product> getAllProducts();
     Product addProduct(Product product);
     ResponseEntity<Product> getProductId(Integer id);

     ResponseEntity<Product> updateProduct(Integer id, Product newProduct);

     ResponseEntity<Product> deleteProduct(Integer id);
}
