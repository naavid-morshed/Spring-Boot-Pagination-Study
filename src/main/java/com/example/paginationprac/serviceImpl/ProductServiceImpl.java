package com.example.paginationprac.serviceImpl;

import com.example.paginationprac.dto.ProductRequest;
import com.example.paginationprac.dto.ProductResponse;
import com.example.paginationprac.entities.Product;
import com.example.paginationprac.repositories.ProductRepo;
import com.example.paginationprac.services.ProductService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Override // default is ascending
    public List<ProductResponse> findByFieldWithSorting(String field) {
        return productRepo.findAll(Sort.by(field))
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
    // example: pageSize = 25, offset = 0, means first 25 items, offset = 3 would mean 25 items counting from 51-75
    public Page<ProductResponse> findProductsWithPagination(int offset, int pageSize) {
//        Pageable pageable = PageRequest.of(offset, pageSize);
        return productRepo.findAll(PageRequest.of(offset, pageSize))
                .map(ProductResponse::fromProduct);
    }

    @Override
    public Page<ProductResponse> findProductsWithPaginationAndField(int offset, int pageSize, String field) {
        Pageable pageable = PageRequest.of(offset, pageSize).withSort(Sort.by(field));

        return productRepo.findAll(pageable)
                .map(ProductResponse::fromProduct);
    }

    @Override
    public Page<ProductResponse> findProductsWithPaginationAndFieldAndDescending(int offset, int pageSize, String field) {
        Pageable pageable = PageRequest.of(offset, pageSize).withSort(Sort.by(Sort.Direction.DESC, field));

        return productRepo.findAll(pageable)
                .map(ProductResponse::fromProduct);
    }

    public Page<ProductResponse> combo(Integer offset, Integer pageSize, String field, Boolean ascending) {
        Pageable pageable = PageRequest.of(
                offset != null ? offset : 0,
                pageSize != null ? pageSize : 10,
                ascending == null || ascending ? Sort.Direction.ASC : Sort.Direction.DESC,
                StringUtils.isNotBlank(field) ? field : "id"
        );
        return productRepo.findAll(pageable).map(ProductResponse::fromProduct);
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
