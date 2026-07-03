package org.example.swcsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String title;

    @NotNull
    @Min(0)
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @NotBlank
    @Column(nullable = false)
    private String unit; // e.g., "/ each"

    @NotBlank
    @Column(nullable = false)
    private String category;

    @NotBlank
    @Column(name = "image_url", nullable = false, length = 2048)
    private String imageUrl;

    @Column(name = "description", length = 2000)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt = new Date();

    @NotNull
    @Min(0)
    @Column(name = "stock_qty", nullable = false)
    private Integer quantity = 0;

    @NotNull
    @Min(0)
    @Column(name = "reorder_level", nullable = false)
    private Integer reorderLevel = 0;

    // Keep compatibility with an existing DB column constraint
    @NotNull
    @Min(0)
    @Column(name = "low_stock_threshold", nullable = false)
    private Integer lowStockThreshold = 0;

    // Some databases may still have a legacy non-null column named 'quantity'.
    // Map it separately and keep it in sync so inserts won't fail.
    @NotNull
    @Min(0)
    @Column(name = "quantity", nullable = false)
    private Integer legacyQuantity = 0;

    public Product() {}

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(Integer reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public Integer getLowStockThreshold() {
        return lowStockThreshold;
    }

    public void setLowStockThreshold(Integer lowStockThreshold) {
        this.lowStockThreshold = lowStockThreshold;
    }

    @PrePersist
    @PreUpdate
    private void syncLegacyQuantity() {
        this.legacyQuantity = this.quantity != null ? this.quantity : 0;
    }
}


