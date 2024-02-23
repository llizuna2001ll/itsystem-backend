package com.llizuna2001ll.itsystem.repositories;

import com.llizuna2001ll.itsystem.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Page<Product> findProductByCategory_Id(Long id, Pageable pageable);
    Optional<Product> findByName(String name);

    
}
