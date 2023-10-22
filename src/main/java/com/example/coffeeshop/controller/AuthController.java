package com.example.coffeeshop.controller;

import com.example.coffeeshop.dto.OrderReprDTO;
import com.example.coffeeshop.dto.RegistrationDTO;
import com.example.coffeeshop.dto.CustomerDTO;
import com.example.coffeeshop.repo.CustomerRepo;
import com.example.coffeeshop.service.CustomerService;
import com.example.coffeeshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final OrderService orderService;
    private final CustomerService customerService;

    @PostMapping("/user")
    @PreAuthorize("hasRole('user')")
    public void registration(@ModelAttribute RegistrationDTO registrationDTO){
        customerService.addCustomer(registrationDTO);
    }

    @GetMapping("/user/{id}")
    @PreAuthorize("hasRole('user')")
    public CustomerDTO getUser(@PathVariable int id){
        return customerService.getCustomer(id);
    }

    @GetMapping("/users")
    @PreAuthorize("hasRole('user')")
    public List<CustomerDTO> getUsers(){
        return customerService.getAllCustomers();
    }

    @DeleteMapping("/user/{id}")
    @PreAuthorize("hasRole('user')")
    public void deleteUser(@PathVariable int id){
        customerService.deleteUser(id);
    }

    @PutMapping("/user/{id}")
    @PreAuthorize("hasRole('user')")
    public void updateUser(@PathVariable int id, @ModelAttribute RegistrationDTO infoToUpdate){
        customerService.updateUser(id, infoToUpdate);
    }

    @PatchMapping("/user/{id}/firstName/{firstName}")
    @PreAuthorize("hasRole('user')")
    public void updateUsersFirstName(@PathVariable int id, @PathVariable String firstName){
        customerService.updateUsersFirstName(id, firstName);
    }

    @PatchMapping("/user/{id}/lastName/{lastName}")
    @PreAuthorize("hasRole('user')")
    public void updateUsersLastName(@PathVariable int id, @PathVariable String lastName){
        customerService.updateUsersLastName(id, lastName);
    }

    @PatchMapping("/user/{id}/phoneNumber/{phoneNumber}")
    @PreAuthorize("hasRole('user')")
    public void updateUsersPhoneNumber(@PathVariable int id, @PathVariable String phoneNumber){
        customerService.updateUsersPhoneNumber(id, phoneNumber);
    }

    @GetMapping("/user/{id}/active_order")
    @PreAuthorize("hasRole('user')")
    public void getUserActiveOrder(@PathVariable int id){
        customerService.getActiveOrder(id);
    }

    @PostMapping("/user/{id}/add_dish/{dish_id}")
    @PreAuthorize("hasRole('user')")
    public void addDish(@PathVariable int id, @PathVariable int dish_id){
        orderService.addDishToOrder(id, dish_id);
    }

    @GetMapping("/login_page")
    public String getLoginPage(){
        return "Login page";
    }

    @PreAuthorize("hasRole('user')")
    @GetMapping("/getOrder")
    public List<OrderReprDTO> getOrder(){
        return orderService.getOrder(6);
    }
}
