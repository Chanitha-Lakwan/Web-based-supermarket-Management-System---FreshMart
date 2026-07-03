package org.example.swcsystem.repository;

import org.example.swcsystem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @org.springframework.data.jpa.repository.Query("select p from Product p where p.quantity <= p.reorderLevel")
    java.util.List<Product> findLowStock();
}


