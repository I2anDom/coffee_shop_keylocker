package com.example.coffeeshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    public static RegistrationDTO getRegistrationDTO(CustomerDTO customerDTO){
        return RegistrationDTO.builder()
                .firstName(customerDTO.getFirstName())
                .lastName(customerDTO.getLastName())
                .phoneNumber(customerDTO.getPhoneNumber())
                .build();
    }
}
