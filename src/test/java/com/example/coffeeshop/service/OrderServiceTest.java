package com.example.coffeeshop.service;

import com.example.coffeeshop.dto.DishDTO;
import com.example.coffeeshop.repo.CustomerRepo;
import com.example.coffeeshop.repo.OrderRepo;
import com.example.coffeeshop.repoImpl.CustomerRepoImpl;
import com.example.coffeeshop.repoImpl.OrderRepoImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepoImpl orderRepo;
    @Mock
    CustomerRepoImpl customerRepo;
    @InjectMocks
    private OrderService orderService;

    @Test
    public void canAddDishToActiveOrderWhenItExists(){
        when(customerRepo.getActiveOrderId(1)).thenReturn(1);
        orderService.addDishToOrder(1, 1);

        verify(orderRepo).addToOrder(1, 1);
    }

    @Test
    public void canAddDishToActiveOrderWhenItsDoesntExists(){
        when(customerRepo.getActiveOrderId(1)).thenReturn(null);
        when(orderRepo.createOrder()).thenReturn(1);
//        verify(customerRepo).addOrderToUser(1,1);
//        verify(orderRepo).addToOrder(1,1);

        orderService.addDishToOrder(1, 1);

        verify(orderRepo).addToOrder(1, 1);
    }
}
