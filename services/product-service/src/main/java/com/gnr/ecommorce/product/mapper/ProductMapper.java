package com.gnr.ecommorce.product.mapper;

import com.gnr.ecommorce.product.entity.Category;
import com.gnr.ecommorce.product.entity.Product;
import com.gnr.ecommorce.product.record.ProductPurchaseResponse;
import com.gnr.ecommorce.product.record.ProductRequest;
import com.gnr.ecommorce.product.record.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toProduct(ProductRequest productRequest) {
        return Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .availableQuantity(productRequest.availableQuantity())
                .price(productRequest.price())
                .category(Category.builder()
                        .id(productRequest.categoryId())
                        .build())
                .build();
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getDescription()
        );
    }

    public ProductPurchaseResponse toProductPurchaseResponse(Product product) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getAvailableQuantity()
        );
    }
}
