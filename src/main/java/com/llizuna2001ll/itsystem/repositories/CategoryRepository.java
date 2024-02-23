package com.llizuna2001ll.itsystem.repositories;

import com.llizuna2001ll.itsystem.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findCategoryByNameIgnoreCase(String name);
}