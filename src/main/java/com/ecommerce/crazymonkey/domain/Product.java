package com.ecommerce.crazymonkey.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @NonNull
    private Long id;

    @NonNull
    private  String name;

    @NonNull
    private String lastName;
}
