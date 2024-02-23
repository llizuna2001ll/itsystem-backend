package com.llizuna2001ll.itsystem.repositories;

import com.llizuna2001ll.itsystem.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}