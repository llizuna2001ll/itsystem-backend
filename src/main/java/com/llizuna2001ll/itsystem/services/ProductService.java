package com.llizuna2001ll.itsystem.services;

import com.llizuna2001ll.itsystem.entities.Product;
import com.llizuna2001ll.itsystem.exceptions.ProductAlreadyExistsException;
import com.llizuna2001ll.itsystem.exceptions.ProductNotFoundException;
import com.llizuna2001ll.itsystem.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final S3Service s3Service;

    @Autowired
    public ProductService(ProductRepository productRepository, S3Service s3Service) {
        this.productRepository = productRepository;
        this.s3Service = s3Service;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Page<Product> getProductsByCategory(Long categoryId, Pageable pageable) {
        return productRepository.findProductByCategory_Id(categoryId, pageable);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    public Product getProductByName(String name) {
        return productRepository.findByName(name).orElseThrow(ProductNotFoundException::new);
    }

    @Transactional
    public Product createProduct(Product product, MultipartFile productImage) {
        if (productRepository.findByName(product.getName())) {
            throw new ProductAlreadyExistsException("Product already exists");
        }
        product.setCreationDate(LocalDateTime.now());
        Product savedProduct = productRepository.save(product);
        return s3Service.uploadPicture(productImage, savedProduct.getId());
    }

    public Product updateProduct(Long id, Product product) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException();
        }
        product.setId(id);
        product.setBrand(product.getBrand());
        product.setName(product.getBrand());
        product.setDescription(product.getDescription());
        product.setReference(product.getReference());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void deleteProducts(List<Long> ids){
        productRepository.deleteAllById(ids);
    }

    public List<Product> getRecent() {
        return productRepository.findAll()
                .stream()
                .sorted((product1, product2) -> product2.getCreationDate().compareTo(product1.getCreationDate()))
                .limit(5)
                .collect(Collectors.toList());
    }
}

