package com.example.coffeeshop.repo;

import com.example.coffeeshop.dto.DishDTO;

public interface OrderRepo {
    void addToOrder(int dish_id, int orderId);
    int createOrder();
}
