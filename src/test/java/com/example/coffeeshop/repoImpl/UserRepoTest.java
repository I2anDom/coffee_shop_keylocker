package com.example.coffeeshop.repoImpl;

import com.example.coffeeshop.dto.CustomerDTO;
import com.example.coffeeshop.dto.RegistrationDTO;
import com.example.coffeeshop.repo.CustomerRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@ExtendWith(MockitoExtension.class)

public class UserRepoTest {
    @InjectMocks
    CustomerRepoImpl customerRepo;
    @Mock
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

//    @Test
//    void canGetCustomerByPhoneNumber(){
//        String phoneNumber = "04423";
//        RegistrationDTO registrationDTO = new RegistrationDTO(
//                "George",
//                "Nikotin",
//                phoneNumber
//        );
//
//        String query = "SELECT * FROM customer WHERE phone_number = :phoneNumber";
//        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource()
//                .addValue("phoneNumber", phoneNumber);
//
//        Mockito.when(namedParameterJdbcTemplate.query(
//                query, mapSqlParameterSource, new BeanPropertyRowMapper<>(CustomerDTO.class))
//                .thenReturn();
//    }
}
