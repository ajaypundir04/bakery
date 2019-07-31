package com.hexad.bakery.repositories;

import com.hexad.bakery.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByCode(String code);
}
