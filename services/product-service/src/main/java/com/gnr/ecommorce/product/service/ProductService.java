package com.gnr.ecommorce.product.service;

import com.gnr.ecommorce.product.record.ProductPurchaseRequest;
import com.gnr.ecommorce.product.record.ProductPurchaseResponse;
import com.gnr.ecommorce.product.record.ProductRequest;
import com.gnr.ecommorce.product.record.ProductResponse;
import java.util.List;

public interface ProductService {
    Integer createProduct(ProductRequest productRequest);

    List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request);

    ProductResponse findByProductId(Integer productId);

    List<ProductResponse> findAllProducts();
}
