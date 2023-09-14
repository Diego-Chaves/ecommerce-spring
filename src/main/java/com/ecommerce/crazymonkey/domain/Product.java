package com.ecommerce.crazymonkey.domain;

import lombok.Data;
import lombok.NonNull;

@Data
public class Product {
    @NonNull
    private Long id;

    @NonNull
    private  String name;

    private String lastName;
}
