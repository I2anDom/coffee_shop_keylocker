package com.example.coffeeshop.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private int id;
    private Timestamp timestamp;
    private List<DishDTO> dishes;
}
