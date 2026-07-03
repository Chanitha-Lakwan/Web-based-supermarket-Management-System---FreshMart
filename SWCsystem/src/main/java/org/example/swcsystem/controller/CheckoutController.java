package org.example.swcsystem.controller;

import jakarta.transaction.Transactional;
import org.example.swcsystem.model.Product;
import org.example.swcsystem.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {

    public static class CheckoutItem {
        private Long productId;
        private Integer qty;

        public Long getProductId() { return productId; }
        public void setProductId(Long productId) { this.productId = productId; }
        public Integer getQty() { return qty; }
        public void setQty(Integer qty) { this.qty = qty; }
    }

    private final ProductRepository productRepository;

    public CheckoutController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> checkout(@RequestBody List<CheckoutItem> items) {
        if (items == null || items.isEmpty()) {
            return ResponseEntity.badRequest().body("Cart is empty");
        }

        List<Product> updated = new ArrayList<>();

        for (CheckoutItem item : items) {
            if (item.getProductId() == null || item.getQty() == null) {
                return ResponseEntity.badRequest().body("Each item must have productId and qty");
            }
            Long productId = item.getProductId();
            int qty = item.getQty();
            if (qty <= 0) {
                return ResponseEntity.badRequest().body("Quantity must be positive");
            }

            Optional<Product> opt = productRepository.findById(productId);
            if (opt.isEmpty()) {
                return ResponseEntity.badRequest().body("Product not found: " + productId);
            }
            Product product = opt.get();
            Integer current = product.getQuantity() == null ? 0 : product.getQuantity();
            if (current < qty) {
                return ResponseEntity.badRequest().body("Insufficient stock for product: " + product.getTitle());
            }
            product.setQuantity(current - qty);
            updated.add(productRepository.save(product));
        }

        return ResponseEntity.ok(updated);
    }
}


