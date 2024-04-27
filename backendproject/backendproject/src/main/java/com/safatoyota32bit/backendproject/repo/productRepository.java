package com.safatoyota32bit.backendproject.repo;

import com.safatoyota32bit.backendproject.entity.product;
import org.springframework.data.jpa.repository.JpaRepository;
public interface productRepository extends JpaRepository <product, Integer>{
}

