package com.example.paginationprac.serviceImpl;

import com.example.paginationprac.dto.ProductRequest;
import com.example.paginationprac.dto.ProductResponse;
import com.example.paginationprac.entities.Product;
import com.example.paginationprac.repositories.ProductRepo;
import com.example.paginationprac.services.ProductService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Optional.of;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;

//    @PostConstruct
//    public void initDbProduct() {
//        List<Product> productList = IntStream.range(1, 200)
//                .mapToObj((int i) -> new Product("product_" + i, new Random().nextDouble(2000)))
//                .toList();
//
//        productRepo.saveAll(productList);
//    }

    public List<ProductResponse> getAllProducts() {
        return productRepo.findAll()
                .stream()
                .map(ProductResponse::fromProduct)
                .collect(Collectors.toList());
    }

    public Optional<ProductResponse> findById(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return of(product).map(ProductResponse::fromProduct);
    }

    public Optional<ProductResponse> createProduct(ProductRequest productRequest) {
        return of(new Product(productRequest))
                .map(productRepo::save)
                .map(ProductResponse::fromProduct);
    }

    public Optional<ProductResponse> updateProduct(ProductResponse productResponse) {
        return productRepo.findById(productResponse.id())
                .map((Product product) -> {
                    product.setName(productResponse.name());
                    product.setPrice(productResponse.price());

                    return product;
                })
                .map(productRepo::save)
                .map(ProductResponse::fromProduct);
    }

    public Long deleteProductById(Long id) {
        return productRepo.findById(id)
                .map((Product product) -> {
                    productRepo.delete(product);

                    return product.getId();
                })
                .orElse(-1L);
    }
}
