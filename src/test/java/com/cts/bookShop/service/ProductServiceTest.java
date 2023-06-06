package com.cts.bookShop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cts.bookShop.dao.ProductDao;
import com.cts.bookShop.entity.Product;
import com.cts.bookShop.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductService.class})
@ExtendWith(SpringExtension.class)
class ProductServiceTest {
    @MockBean
    private ProductDao productDao;

    @Autowired
    private ProductService productService;

    /**
     * Method under test: {@link ProductService#getAllProducts()}
     */
    @Test
    void testGetAllProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        when(productDao.findAll()).thenReturn(productList);
        List<Product> actualAllProducts = productService.getAllProducts();
        assertSame(productList, actualAllProducts);
        assertTrue(actualAllProducts.isEmpty());
        verify(productDao).findAll();
    }

    /**
     * Method under test: {@link ProductService#getAllProducts()}
     */
    @Test
    void testGetAllProducts2() {
        when(productDao.findAll()).thenThrow(new ResourceNotFoundException("Msg"));
        assertThrows(ResourceNotFoundException.class, () -> productService.getAllProducts());
        verify(productDao).findAll();
    }

    /**
     * Method under test: {@link ProductService#addProduct(Product)}
     */
    @Test
    void testAddProduct() {
        Product product = new Product();
        product.setAuthor("JaneDoe");
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setISBN("ISBN");
        product.setImgUrl("https://example.org/example");
        product.setName("Name");
        product.setPId(1);
        product.setPrice(10.0d);
        product.setQuantity(1);
        when(productDao.save(Mockito.<Product>any())).thenReturn(product);

        Product product2 = new Product();
        product2.setAuthor("JaneDoe");
        product2.setCategory("Category");
        product2.setDescription("The characteristics of someone or something");
        product2.setISBN("ISBN");
        product2.setImgUrl("https://example.org/example");
        product2.setName("Name");
        product2.setPId(1);
        product2.setPrice(10.0d);
        product2.setQuantity(1);
        assertSame(product, productService.addProduct(product2));
        verify(productDao).save(Mockito.<Product>any());
    }

    /**
     * Method under test: {@link ProductService#addProduct(Product)}
     */
    @Test
    void testAddProduct2() {
        when(productDao.save(Mockito.<Product>any())).thenThrow(new ResourceNotFoundException("Msg"));

        Product product = new Product();
        product.setAuthor("JaneDoe");
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setISBN("ISBN");
        product.setImgUrl("https://example.org/example");
        product.setName("Name");
        product.setPId(1);
        product.setPrice(10.0d);
        product.setQuantity(1);
        assertThrows(ResourceNotFoundException.class, () -> productService.addProduct(product));
        verify(productDao).save(Mockito.<Product>any());
    }

    /**
     * Method under test: {@link ProductService#getProductId(Integer)}
     */
    @Test
    void testGetProductId() {
        Product product = new Product();
        product.setAuthor("JaneDoe");
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setISBN("ISBN");
        product.setImgUrl("https://example.org/example");
        product.setName("Name");
        product.setPId(1);
        product.setPrice(10.0d);
        product.setQuantity(1);
        Optional<Product> ofResult = Optional.of(product);
        when(productDao.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        ResponseEntity<Product> actualProductId = productService.getProductId(1);
        assertTrue(actualProductId.hasBody());
        assertTrue(actualProductId.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualProductId.getStatusCode());
        verify(productDao).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link ProductService#getProductId(Integer)}
     */
    @Test
    void testGetProductId2() {
        when(productDao.findById(Mockito.<Integer>any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> productService.getProductId(1));
        verify(productDao).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link ProductService#getProductId(Integer)}
     */
    @Test
    void testGetProductId3() {
        when(productDao.findById(Mockito.<Integer>any())).thenThrow(new ResourceNotFoundException("Msg"));
        assertThrows(ResourceNotFoundException.class, () -> productService.getProductId(1));
        verify(productDao).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link ProductService#updateProduct(Integer, Product)}
     */
    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setAuthor("JaneDoe");
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setISBN("ISBN");
        product.setImgUrl("https://example.org/example");
        product.setName("Name");
        product.setPId(1);
        product.setPrice(10.0d);
        product.setQuantity(1);
        Optional<Product> ofResult = Optional.of(product);

        Product product2 = new Product();
        product2.setAuthor("JaneDoe");
        product2.setCategory("Category");
        product2.setDescription("The characteristics of someone or something");
        product2.setISBN("ISBN");
        product2.setImgUrl("https://example.org/example");
        product2.setName("Name");
        product2.setPId(1);
        product2.setPrice(10.0d);
        product2.setQuantity(1);
        when(productDao.save(Mockito.<Product>any())).thenReturn(product2);
        when(productDao.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Product newProduct = new Product();
        newProduct.setAuthor("JaneDoe");
        newProduct.setCategory("Category");
        newProduct.setDescription("The characteristics of someone or something");
        newProduct.setISBN("ISBN");
        newProduct.setImgUrl("https://example.org/example");
        newProduct.setName("Name");
        newProduct.setPId(1);
        newProduct.setPrice(10.0d);
        newProduct.setQuantity(1);
        ResponseEntity<Product> actualUpdateProductResult = productService.updateProduct(1, newProduct);
        assertEquals(newProduct, actualUpdateProductResult.getBody());
        assertTrue(actualUpdateProductResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualUpdateProductResult.getStatusCode());
        verify(productDao).save(Mockito.<Product>any());
        verify(productDao).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link ProductService#updateProduct(Integer, Product)}
     */
    @Test
    void testUpdateProduct2() {
        Product product = new Product();
        product.setAuthor("JaneDoe");
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setISBN("ISBN");
        product.setImgUrl("https://example.org/example");
        product.setName("Name");
        product.setPId(1);
        product.setPrice(10.0d);
        product.setQuantity(1);
        Optional<Product> ofResult = Optional.of(product);
        when(productDao.save(Mockito.<Product>any())).thenThrow(new ResourceNotFoundException("Msg"));
        when(productDao.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        Product newProduct = new Product();
        newProduct.setAuthor("JaneDoe");
        newProduct.setCategory("Category");
        newProduct.setDescription("The characteristics of someone or something");
        newProduct.setISBN("ISBN");
        newProduct.setImgUrl("https://example.org/example");
        newProduct.setName("Name");
        newProduct.setPId(1);
        newProduct.setPrice(10.0d);
        newProduct.setQuantity(1);
        assertThrows(ResourceNotFoundException.class, () -> productService.updateProduct(1, newProduct));
        verify(productDao).save(Mockito.<Product>any());
        verify(productDao).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link ProductService#updateProduct(Integer, Product)}
     */
    @Test
    void testUpdateProduct3() {
        Product product = new Product();
        product.setAuthor("JaneDoe");
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setISBN("ISBN");
        product.setImgUrl("https://example.org/example");
        product.setName("Name");
        product.setPId(1);
        product.setPrice(10.0d);
        product.setQuantity(1);
        when(productDao.save(Mockito.<Product>any())).thenReturn(product);
        when(productDao.findById(Mockito.<Integer>any())).thenReturn(Optional.empty());

        Product newProduct = new Product();
        newProduct.setAuthor("JaneDoe");
        newProduct.setCategory("Category");
        newProduct.setDescription("The characteristics of someone or something");
        newProduct.setISBN("ISBN");
        newProduct.setImgUrl("https://example.org/example");
        newProduct.setName("Name");
        newProduct.setPId(1);
        newProduct.setPrice(10.0d);
        newProduct.setQuantity(1);
        assertThrows(ResourceNotFoundException.class, () -> productService.updateProduct(1, newProduct));
        verify(productDao).findById(Mockito.<Integer>any());
    }

    /**
     * Method under test: {@link ProductService#deleteProduct(Integer)}
     */
    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setAuthor("JaneDoe");
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setISBN("ISBN");
        product.setImgUrl("https://example.org/example");
        product.setName("Name");
        product.setPId(1);
        product.setPrice(10.0d);
        product.setQuantity(1);
        Optional<Product> ofResult = Optional.of(product);
        doNothing().when(productDao).delete(Mockito.<Product>any());
        when(productDao.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        ResponseEntity<Product> actualDeleteProductResult = productService.deleteProduct(1);
        assertTrue(actualDeleteProductResult.hasBody());
        assertTrue(actualDeleteProductResult.getHeaders().isEmpty());
        assertEquals(HttpStatus.OK, actualDeleteProductResult.getStatusCode());
        verify(productDao).findById(Mockito.<Integer>any());
        verify(productDao).delete(Mockito.<Product>any());
    }

    /**
     * Method under test: {@link ProductService#deleteProduct(Integer)}
     */
    @Test
    void testDeleteProduct2() {
        Product product = new Product();
        product.setAuthor("JaneDoe");
        product.setCategory("Category");
        product.setDescription("The characteristics of someone or something");
        product.setISBN("ISBN");
        product.setImgUrl("https://example.org/example");
        product.setName("Name");
        product.setPId(1);
        product.setPrice(10.0d);
        product.setQuantity(1);
        Optional<Product> ofResult = Optional.of(product);
        doThrow(new ResourceNotFoundException("Msg")).when(productDao).delete(Mockito.<Product>any());
        when(productDao.findById(Mockito.<Integer>any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class, () -> productService.deleteProduct(1));
        verify(productDao).findById(Mockito.<Integer>any());
        verify(productDao).delete(Mockito.<Product>any());
    }

    /**
     * Method under test: {@link ProductService#deleteProduct(Integer)}
     */
    @Test
    void testDeleteProduct3() {
        doNothing().when(productDao).delete(Mockito.<Product>any());
        when(productDao.findById(Mockito.<Integer>any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> productService.deleteProduct(1));
        verify(productDao).findById(Mockito.<Integer>any());
    }
}

