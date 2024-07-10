package com.example.paginationprac.services;

import com.example.paginationprac.dto.ProductRequest;
import com.example.paginationprac.dto.ProductResponse;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductResponse> getAllProducts();
    Optional<ProductResponse> findById(Long id);
    Optional<ProductResponse> createProduct(ProductRequest productRequest);
    Optional<ProductResponse> updateProduct(ProductResponse productResponse);
    Long deleteProductById(Long id);
}
