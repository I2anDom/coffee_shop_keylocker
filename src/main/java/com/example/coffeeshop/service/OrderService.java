package com.example.coffeeshop.service;

import com.example.coffeeshop.dto.DishDTO;
import com.example.coffeeshop.dto.OrderDTO;
import com.example.coffeeshop.dto.OrderReprDTO;
import com.example.coffeeshop.repo.OrderRepo;
import com.example.coffeeshop.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;
    private final CustomerRepo userRepo;

    public void addDishToOrder(int userId, int dishId){
        Integer activeOrderId = userRepo.getActiveOrderId(userId);
        if(activeOrderId == null){
            int orderId = orderRepo.createOrder();
            userRepo.addOrderToUser(userId, orderId);
            orderRepo.addToOrder(dishId, orderId);
        } else{
            orderRepo.addToOrder(dishId, activeOrderId);
        }
    }

    public List<OrderReprDTO> getOrder(int userId){
        return orderRepo.getUserOrder(userId);
    }
}
