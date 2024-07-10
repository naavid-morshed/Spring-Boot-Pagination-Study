package com.example.paginationprac.controllers;

import com.example.paginationprac.dto.ProductRequest;
import com.example.paginationprac.dto.ProductResponse;
import com.example.paginationprac.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController()
@RequestMapping("api/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public Optional<ProductResponse> findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping("add-product")
    public Optional<ProductResponse> createProduct(ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @PutMapping("update-product")
    public Optional<ProductResponse> updateProduct(ProductResponse productResponse) {
        return productService.updateProduct(productResponse);
    }

    @DeleteMapping("{id}")
    public Long deleteProductById(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }
}
