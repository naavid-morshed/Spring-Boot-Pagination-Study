package com.example.paginationprac.controllers;

import com.example.paginationprac.dto.ProductRequest;
import com.example.paginationprac.dto.ProductResponse;
import com.example.paginationprac.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping(path = "api/v1/product")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping()
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("field/{field}")
    public List<ProductResponse> findByFieldWithSorting(@PathVariable String field) {
        return productService.findByFieldWithSorting(field);
    }

    @GetMapping("id/{id}")
    public Optional<ProductResponse> findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping("add-product")
    public Optional<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @PutMapping("update-product")
    public Optional<ProductResponse> updateProduct(@RequestBody ProductResponse productResponse) {
        return productService.updateProduct(productResponse);
    }

    @DeleteMapping("{id}")
    public Long deleteProductById(@PathVariable Long id) {
        return productService.deleteProductById(id);
    }
}
