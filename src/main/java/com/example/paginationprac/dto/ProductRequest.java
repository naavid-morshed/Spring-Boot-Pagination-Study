package com.example.paginationprac.dto;

import com.example.paginationprac.entities.Product;

/**
 * DTO for {@link Product}
 */
public record  ProductRequest(String name, Double price) {
}