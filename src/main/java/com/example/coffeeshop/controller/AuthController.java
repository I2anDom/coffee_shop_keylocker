package com.example.coffeeshop.controller;

import com.example.coffeeshop.dto.RegistrationDTO;
import com.example.coffeeshop.dto.CustomerDTO;
import com.example.coffeeshop.repo.CustomerRepo;
import com.example.coffeeshop.service.CustomerService;
import com.example.coffeeshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final OrderService orderService;
    private final CustomerService customerService;

    @PostMapping("/user")
    public void registration(@ModelAttribute RegistrationDTO registrationDTO){
        customerService.addCustomer(registrationDTO);
    }

    @GetMapping("/user/{id}")
    public CustomerDTO getUser(@PathVariable int id){
        return customerService.getCustomer(id);
    }

    @GetMapping("/users")
    public List<CustomerDTO> getUsers(){
        return customerService.getAllCustomers();
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable int id){
        customerService.deleteUser(id);
    }

    @PutMapping("/user/{id}")
    public void updateUser(@PathVariable int id, @ModelAttribute RegistrationDTO infoToUpdate){
        customerService.updateUser(id, infoToUpdate);
    }

    @PatchMapping("/user/{id}/firstName/{firstName}")
    public void updateUsersFirstName(@PathVariable int id, @PathVariable String firstName){
        customerService.updateUsersFirstName(id, firstName);
    }

    @PatchMapping("/user/{id}/lastName/{lastName}")
    public void updateUsersLastName(@PathVariable int id, @PathVariable String lastName){
        customerService.updateUsersLastName(id, lastName);
    }

    @PatchMapping("/user/{id}/phoneNumber/{phoneNumber}")
    public void updateUsersPhoneNumber(@PathVariable int id, @PathVariable String phoneNumber){
        customerService.updateUsersPhoneNumber(id, phoneNumber);
    }

    @GetMapping("/user/{id}/active_order")
    public void getUserActiveOrder(@PathVariable int id){
        customerService.getActiveOrder(id);
    }

    @PostMapping("/user/{id}/add_dish/{dish_id}")
    public void addDish(@PathVariable int id, @PathVariable int dish_id){
        orderService.addDishToOrder(id, dish_id);
    }
}
