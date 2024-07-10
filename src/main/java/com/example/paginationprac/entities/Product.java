package com.example.paginationprac.entities;

import com.example.paginationprac.dto.ProductRequest;
import com.example.paginationprac.dto.ProductResponse;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;

    public Product(ProductRequest productRequest) {
        this.setName(productRequest.name());
        this.setPrice(productRequest.price());
    }

    public Product(ProductResponse productResponse) {
        this.setId(productResponse.id());
        this.setName(productResponse.name());
        this.setPrice(productResponse.price());
    }

    public Product(String name, Double price) {
        this.setName(name);
        this.setPrice(price);
    }
}