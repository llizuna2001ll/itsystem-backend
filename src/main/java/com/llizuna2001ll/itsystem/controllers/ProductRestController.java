package com.llizuna2001ll.itsystem.controllers;

import com.llizuna2001ll.itsystem.entities.Category;
import com.llizuna2001ll.itsystem.entities.Product;
import com.llizuna2001ll.itsystem.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductRestController {

    private final ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/clients")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/clients/category/{categoryId}")
    public ResponseEntity<Page<Product>> getProductsByCategoryId(@PathVariable Long categoryId, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 8); // PageRequest is 0-indexed
        Page<Product> productsPage = productService.getProductsByCategory(categoryId, pageable);
        return new ResponseEntity<>(productsPage, HttpStatus.OK);
    }

    @GetMapping("/clients/{productName}")
    public ResponseEntity<Product> getProductByName(@PathVariable String productName) {
        Product product = productService.getProductByName(productName);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(String name, String brand, String reference, String description, Long categoryId, @RequestParam(value = "productImage") MultipartFile productImage) {
        Product product = Product.builder()
                .name(name)
                .brand(brand)
                .reference(reference)
                .description(description)
                .category(Category.builder().id(categoryId).build())
                .build();
        Product createdProduct = productService.createProduct(product, productImage);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){
        Product updatedProduct = productService.updateProduct(id, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/clients/recent")
    public ResponseEntity<List<Product>> getRecentProducts() {
        List<Product> products = productService.getRecent();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}

