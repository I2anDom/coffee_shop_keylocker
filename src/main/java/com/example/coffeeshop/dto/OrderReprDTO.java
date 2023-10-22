package com.example.coffeeshop.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class OrderReprDTO {
    private int dishId;
    private int price;
    private float weight;
    private String name;
    private int count;
}
