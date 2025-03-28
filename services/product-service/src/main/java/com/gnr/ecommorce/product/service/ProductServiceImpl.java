package com.gnr.ecommorce.product.service;

import com.gnr.ecommorce.product.exception.ProductPurchaseException;
import com.gnr.ecommorce.product.mapper.ProductMapper;
import com.gnr.ecommorce.product.record.ProductPurchaseRequest;
import com.gnr.ecommorce.product.record.ProductPurchaseResponse;
import com.gnr.ecommorce.product.record.ProductRequest;
import com.gnr.ecommorce.product.record.ProductResponse;
import com.gnr.ecommorce.product.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        var product = productMapper.toProduct(productRequest);
        return productRepository.save(product).getId();
    }

    @Override
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
        var requestedProductIds = request.stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var availableProductsInRepo = productRepository.findAllByIdInOrderById(requestedProductIds);
        if(!Objects.equals(requestedProductIds.size(), availableProductsInRepo.size())) {
            throw new ProductPurchaseException("One or More products doesn't exists");
        }

        List<ProductPurchaseRequest> requestedProducts= request.stream().sorted(Comparator.comparing(ProductPurchaseRequest::productId)).toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for(int i=0; i < availableProductsInRepo.size(); i++) {
            var repoProduct = availableProductsInRepo.get(i);
            var requestedProduct = requestedProducts.get(i);
            if(repoProduct.getAvailableQuantity() < requestedProduct.quantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: "+repoProduct.getId());
            }
            repoProduct.setAvailableQuantity(repoProduct.getAvailableQuantity()-requestedProduct.quantity());
            productRepository.save(repoProduct);
            repoProduct.setAvailableQuantity(requestedProduct.quantity());
            purchasedProducts.add(productMapper.toProductPurchaseResponse(repoProduct));
        }
        return purchasedProducts;
    }

    @Override
    public ProductResponse findByProductId(Integer productId) {
        return productRepository.findById(productId)
                .map(productMapper::toProductResponse).orElseThrow(() -> new EntityNotFoundException(""));
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::toProductResponse)
                .collect(Collectors.toList());
    }
}
