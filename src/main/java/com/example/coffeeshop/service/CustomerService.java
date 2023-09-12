package com.example.coffeeshop.service;


import com.example.coffeeshop.dto.CustomerDTO;
import com.example.coffeeshop.dto.OrderDTO;
import com.example.coffeeshop.dto.RegistrationDTO;
import com.example.coffeeshop.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerService {
    private final CustomerRepo userRepo;

    public List<CustomerDTO> getAllCustomers(){
        return userRepo.getAllCustomers();
    }

    public CustomerDTO addCustomer(RegistrationDTO registrationDTO){
        return userRepo.saveUser(registrationDTO);
    }

    public CustomerDTO getCustomer(int id){
        return userRepo.getUser(id);
    }

    public CustomerDTO deleteUser(int id){
        return userRepo.deleteUser(id);
    }

    public void updateUser(int id, RegistrationDTO registrationDTO){
        userRepo.updateUser(id, registrationDTO);
    }

    public void updateUsersFirstName(int id, String firstName){
        userRepo.updateUsersFirstName(id, firstName);
    }

    public void updateUsersLastName(int id, String lastName){
        userRepo.updateUsersLastName(id, lastName);
    }

    public void updateUsersPhoneNumber(int id, String phoneNumber){
        userRepo.updateUsersPhoneNumber(id, phoneNumber);
    }

    public OrderDTO getActiveOrder(int userId){
        return userRepo.getActiveOrder(userId);
    }

    public Integer getActiveOrderId(int userId){
        return userRepo.getActiveOrderId(userId);
    }

    public void addOrderToUser(int userId, int orderId){
        userRepo.addOrderToUser(userId, orderId);
    }

    public CustomerDTO getUserByPhoneNumber(String phoneNumber){
        return userRepo.getUserByPhoneNumber(phoneNumber);
    }


}
