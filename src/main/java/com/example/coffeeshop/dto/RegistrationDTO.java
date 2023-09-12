package com.example.coffeeshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RegistrationDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
