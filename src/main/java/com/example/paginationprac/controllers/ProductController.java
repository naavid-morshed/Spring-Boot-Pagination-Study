package com.example.paginationprac.controllers;

import com.example.paginationprac.dto.ProductRequest;
import com.example.paginationprac.dto.ProductResponse;
import com.example.paginationprac.services.ProductService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping(path = "api/v1/product")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("id/{id}")
    public Optional<ProductResponse> findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @GetMapping("{field}")
    public List<ProductResponse> findByFieldWithSorting(@PathVariable String field) {
        return productService.findByFieldWithSorting(field);
    }

    @GetMapping("des/{field}")
    public List<ProductResponse> findByFieldWithSortingg(@PathVariable String field) {
        return productService.findByFieldWithSortingDescending(field);
    }

//    @GetMapping("page")
//    public Page<ProductResponse> findProductsWithPagination(@PathParam("offset") int offset, @PathParam("pageSize") int pageSize) {
//        return productService.findProductsWithPagination(offset, pageSize);
//    }

    @GetMapping("page/{offset}/{pageSize}")
    public Page<ProductResponse> findProductsWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        return productService.findProductsWithPagination(offset, pageSize);
    }

    @GetMapping("page/{offset}/{pageSize}/{field}")
    public Page<ProductResponse> findProductsWithPaginationAndField(
            @PathVariable int offset,
            @PathVariable int pageSize,
            @PathVariable String field
    ) {
        return productService.findProductsWithPaginationAndField(offset, pageSize, field);
    }

    @GetMapping("page-desc/{offset}/{pageSize}/{field}")
    public Page<ProductResponse> findProductsWithPaginationAndFieldAndDescending(
            @PathVariable int offset,
            @PathVariable int pageSize,
            @PathVariable String field
    ) {
        return productService.findProductsWithPaginationAndFieldAndDescending(offset, pageSize, field);
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

    @GetMapping("combo")
    public Page<ProductResponse> combo(
            @PathParam("offset") Integer offset,
            @PathParam("pageSize") Integer pageSize,
            @PathParam("field") String field,
            @PathParam("ascending") Boolean ascending
    ) {
        return productService.combo(offset, pageSize, field, ascending);
    }
}
