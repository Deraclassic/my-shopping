package com.dera.model;

import lombok.Data;

@Data
public class Cart extends Product{
    private int quantity;
    public Cart() {
    }

}
