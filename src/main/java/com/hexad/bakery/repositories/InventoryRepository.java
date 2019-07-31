package com.hexad.bakery.repositories;

import com.hexad.bakery.entities.Inventory;
import com.hexad.bakery.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Inventory findByProduct_code(String code);

    void deleteByProduct(Product product);
}
