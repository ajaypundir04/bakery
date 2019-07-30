package com.hexad.bakery.repositories;

import com.hexad.bakery.models.Pack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackRepository extends JpaRepository<Pack, Long> {
}
