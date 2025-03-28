package com.gnr.ecommorce.product.controller;

import com.gnr.ecommorce.product.record.ProductPurchaseRequest;
import com.gnr.ecommorce.product.record.ProductPurchaseResponse;
import com.gnr.ecommorce.product.record.ProductRequest;
import com.gnr.ecommorce.product.record.ProductResponse;
import com.gnr.ecommorce.product.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Integer> createProduct(
        @RequestBody @Valid ProductRequest productRequest
    ) {
        return ResponseEntity.ok(productService.createProduct(productRequest));
    }

    @PostMapping("/purchase")
    public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(
        @RequestBody List<ProductPurchaseRequest> request
    ) {
        return ResponseEntity.ok(productService.purchaseProducts(request));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<ProductResponse> findByProduct(
            @PathVariable("product-id") Integer productId
    ){
        return ResponseEntity.ok(productService.findByProductId(productId));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProducts(

    ) {
        return ResponseEntity.ok(productService.findAllProducts());
    }
 }
