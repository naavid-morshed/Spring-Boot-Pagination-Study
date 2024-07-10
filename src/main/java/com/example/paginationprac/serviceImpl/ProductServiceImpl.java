package com.example.paginationprac.serviceImpl;

import com.example.paginationprac.dto.ProductRequest;
import com.example.paginationprac.dto.ProductResponse;
import com.example.paginationprac.entities.Product;
import com.example.paginationprac.repositories.ProductRepo;
import com.example.paginationprac.services.ProductService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepo.findAll()
                .stream()
                .map(ProductResponse::fromProduct)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductResponse> findById(Long id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return of(product).map(ProductResponse::fromProduct);
    }

    @Override
    public List<ProductResponse> findByFieldWithSorting(String field) {
        return productRepo.findAll(Sort.by(field)).stream()
                .map(ProductResponse::fromProduct)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> findByFieldWithSortingAscending(String field) {
        return productRepo.findAll(Sort.by(Sort.Direction.ASC, field))
                .stream()
                .map(ProductResponse::fromProduct)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductResponse> findByFieldWithSortingDescending(String field) {
        return productRepo.findAll(Sort.by(Sort.Direction.DESC, field))
                .stream()
                .map(ProductResponse::fromProduct)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductResponse> createProduct(ProductRequest productRequest) {
        return of(new Product(productRequest))
                .map(productRepo::save)
                .map(ProductResponse::fromProduct);
    }

    @Override
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

    @Override
    public Long deleteProductById(Long id) {
        return productRepo.findById(id)
                .map((Product product) -> {
                    productRepo.delete(product);

                    return product.getId();
                })
                .orElse(-1L);
//                .orElseThrow(() -> new RuntimeException("Product of ID : " + id + " Not Found. "));
    }
}
