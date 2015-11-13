package com.location24x7.ecommerce.inventory.domain;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.util.StringUtils;

public class Product {

    private Long id;
    private String brand;
    private String name;
    private String description;
    private String size;
    private String colour;
    private String[] identifiers;
    private String category;
    
    public Product(String brand, String name, String size, String colour) {
        super();
        this.brand = brand;
        this.name = name;
        this.size = size;
        this.colour = colour;
    }

    public Product() {}

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }


    public void setId(Long id) {
        this.id = id;
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
        String concatenated = StringUtils.arrayToDelimitedString(
                new Object[] {
                        this.brand,
                        this.name,
                        (!StringUtils.isEmpty(this.identifiers) ? StringUtils.arrayToDelimitedString(identifiers, "-")
                                : ""), this.colour, this.size}, "-");
        concatenated = WordUtils.capitalize(concatenated, ' ', '-');
        return concatenated.replaceAll(" ", "");
    }

    public String getProductKey() {
        return toString();
    }

    public String[] getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(String[] identifiers) {
        this.identifiers = identifiers;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
