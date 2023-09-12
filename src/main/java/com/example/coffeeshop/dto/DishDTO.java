package com.example.coffeeshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DishDTO {
    private int price;
    private float weight;
    private int categoryId;
    private String name;
}
