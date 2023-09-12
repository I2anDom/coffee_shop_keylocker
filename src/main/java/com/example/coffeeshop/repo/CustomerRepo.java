package com.example.coffeeshop.repo;

import com.example.coffeeshop.dto.OrderDTO;
import com.example.coffeeshop.dto.RegistrationDTO;
import com.example.coffeeshop.dto.CustomerDTO;

import java.util.List;

public interface CustomerRepo {
    CustomerDTO saveUser(RegistrationDTO registrationDTO);
    CustomerDTO getUser(int id);
    CustomerDTO deleteUser(int id);
    void updateUser(int id, RegistrationDTO registrationDTO);
    void updateUsersFirstName(int id, String firstName);
    void updateUsersLastName(int id, String lastName);
    void updateUsersPhoneNumber(int id, String phoneNumber);
    OrderDTO getActiveOrder(int userId);
    Integer getActiveOrderId(int userId);
    void addOrderToUser(int userId, int orderId);
    CustomerDTO getUserByPhoneNumber(String phone);
    List<CustomerDTO> getAllCustomers();
}
