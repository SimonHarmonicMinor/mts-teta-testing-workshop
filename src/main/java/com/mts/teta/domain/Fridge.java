package com.mts.teta.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PACKAGE;

@Entity
@Table(name = "fridge")
@Getter
@Setter(PACKAGE)
public class Fridge {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @NotNull
    private String name;

    @OneToMany(mappedBy = "fridge", cascade = PERSIST)
    private List<Product> products = new ArrayList<>();

    public static Fridge newFridge(String name) {
        final var fridge = new Fridge();
        fridge.setName(name);
        return fridge;
    }

    public void addProduct(Product.Type type, int count) {
        final var product = new Product();
        product.setType(type);
        product.setCount(count);
        product.setFridge(this);
        this.products.add(product);
    }
}
