package org.example.swcsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;

    @NotBlank
    @Column(name = "icon_class", nullable = false, length = 2048)
    private String imageUrl; // mapped to legacy column 'icon_class'

    public Category() {}

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Backward-compatibility: some databases still have a NOT NULL 'image_url' column
    // Populate this field to satisfy legacy schema constraints until a migration is run
    @NotBlank
    @Column(name = "image_url", nullable = false, length = 2048)
    private String legacyImageUrl = "";

    public String getLegacyImageUrl() {
        return legacyImageUrl;
    }

    public void setLegacyImageUrl(String legacyImageUrl) {
        this.legacyImageUrl = legacyImageUrl;
    }
}


