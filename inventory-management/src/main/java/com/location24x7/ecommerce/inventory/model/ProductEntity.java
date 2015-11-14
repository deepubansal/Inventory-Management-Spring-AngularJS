package com.location24x7.ecommerce.inventory.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "product")
public class ProductEntity implements EntityType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String brand;
    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String size;
    @Column
    private String colour;
    @Column
    private String identifiers;
    @Column
    private String category;

    public ProductEntity() {
    }

    public ProductEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSize() {
        return size;
    }

    public String getColour() {
        return colour;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "Product [id=" + getId() + ", brand=" + brand + ", name=" + name + ", description=" + description
                + ", size=" + size + ", colour=" + colour + "]";
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(String identifiers) {
        this.identifiers = identifiers;
    }
}
