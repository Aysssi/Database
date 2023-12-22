package com.example.jsonproductsshop.domain.repositories;

import com.example.jsonproductsshop.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByPriceBetweenAndAndBuyerIsNull(BigDecimal lower,BigDecimal higher);
}
