package com.mts.teta.controller.dto;

import com.mts.teta.domain.Fridge;

import java.util.List;

public record FridgeResponse(Long id, String name, List<ProductResponse> products) {
    public FridgeResponse(Fridge fridge) {
        this(fridge.getId(), fridge.getName(), fridge.getProducts().stream().map(ProductResponse::new).toList());
    }
}
