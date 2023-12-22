package com.example.jsonproductsshop.domain.repositories;

import com.example.jsonproductsshop.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
