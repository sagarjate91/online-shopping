package com.training.online_shopping.repository;

import com.training.online_shopping.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByCategory(String categoryName);

    List<Product> findByActive(int i);
}
