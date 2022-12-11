package com.mts.teta.controller;

import com.mts.teta.controller.dto.FridgeCreatedResponse;
import com.mts.teta.controller.dto.FridgeResponse;
import com.mts.teta.domain.Fridge;
import com.mts.teta.domain.Product;
import com.mts.teta.repository.FridgeRepository;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class APIController {
    private final FridgeRepository fridgeRepository;

    @GetMapping("/fridge/{fridgeId}")
    public FridgeResponse getFridgeById(@PathVariable Long fridgeId) {
        return fridgeRepository.findById(fridgeId)
                   .map(FridgeResponse::new)
                   .orElseThrow(() -> new NoSuchElementException("Fridge is not found by id: " + fridgeId));
    }

    @PostMapping("/fridge/fridge")
    public FridgeCreatedResponse createNewFridge(@RequestParam String name) {
        final var fridge = fridgeRepository.save(Fridge.newFridge(name));
        return new FridgeCreatedResponse(fridge.getId());
    }

    @PostMapping("/product/{fridgeId}")
    @Transactional
    public void addNewProduct(@PathVariable Long fridgeId,
                              @RequestParam Product.Type type,
                              @RequestParam int count) {
        final var fridge = fridgeRepository.findById(fridgeId).orElseThrow();
        fridge.addProduct(type, count);
    }
}
