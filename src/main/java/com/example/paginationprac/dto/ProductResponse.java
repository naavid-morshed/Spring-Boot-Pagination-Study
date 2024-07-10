package com.example.paginationprac.dto;

import com.example.paginationprac.entities.Product;

/**
 * DTO for {@link Product}
 */
public record ProductResponse(Long id, String name, Double price) {
    public static ProductResponse fromProduct(Product product){
       return new ProductResponse(product.getId(), product.getName(), product.getPrice());
    }
}