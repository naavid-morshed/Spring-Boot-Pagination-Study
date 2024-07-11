package com.example.paginationprac.services;

import com.example.paginationprac.dto.ProductRequest;
import com.example.paginationprac.dto.ProductResponse;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductResponse> getAllProducts();
    Optional<ProductResponse> findById(Long id);
    List<ProductResponse> findByFieldWithSorting(String field);
    Page<ProductResponse> findProductsWithPagination(int offset, int pageSize);
    Page<ProductResponse> findProductsWithPaginationAndField(int offset, int pageSize, String field);
    Page<ProductResponse> findProductsWithPaginationAndFieldAndDescending(int offset, int pageSize, String field);
    Page<ProductResponse> combo(Integer offset, Integer pageSize, String field, Boolean ascending);
    List<ProductResponse> findByFieldWithSortingDescending(String field);
    Optional<ProductResponse> createProduct(ProductRequest productRequest);
    Optional<ProductResponse> updateProduct(ProductResponse productResponse);
    Long deleteProductById(Long id);
}
