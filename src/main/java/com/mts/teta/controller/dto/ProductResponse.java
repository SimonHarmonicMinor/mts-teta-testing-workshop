package com.mts.teta.controller.dto;

import com.mts.teta.domain.Product;

public record ProductResponse(Long id, Product.Type type, int count) {
    public ProductResponse(Product product) {
        this(product.getId(), product.getType(), product.getCount());
    }
}
