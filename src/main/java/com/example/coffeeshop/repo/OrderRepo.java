package com.example.coffeeshop.repo;

import com.example.coffeeshop.dto.DishDTO;
import com.example.coffeeshop.dto.OrderReprDTO;

import java.util.List;

public interface OrderRepo {
    void addToOrder(int dish_id, int orderId);
    int createOrder();
    List<OrderReprDTO> getUserOrder(int userId);
}
