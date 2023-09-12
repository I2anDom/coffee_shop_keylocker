package com.example.coffeeshop.service;

import com.example.coffeeshop.dto.CustomerDTO;
import com.example.coffeeshop.dto.OrderDTO;
import com.example.coffeeshop.dto.RegistrationDTO;
import com.example.coffeeshop.repoImpl.CustomerRepoImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

	@Mock
	private CustomerRepoImpl customerRepoImpl;
	@InjectMocks
	private CustomerService customerService;

	@Test
	void canGetAllCustomers(){
		customerService.getAllCustomers();

		verify(customerRepoImpl).getAllCustomers();
	}

	@Test
	void canAddCustomer(){
		RegistrationDTO registrationDTO = new RegistrationDTO(
				"George",
				"Nikotin",
				"04423"
		);
		customerService.addCustomer(registrationDTO);

		ArgumentCaptor<RegistrationDTO> argumentCaptor =
				ArgumentCaptor.forClass(RegistrationDTO.class);

		verify(customerRepoImpl).saveUser(argumentCaptor.capture());

		RegistrationDTO capturedRegistration = argumentCaptor.getValue();

		assertThat(capturedRegistration).isEqualTo(registrationDTO);
	}

	@Test
	void canGetCustomerById(){
		CustomerDTO customerDTO = CustomerDTO.builder()
				.id(1)
				.firstName("George")
				.lastName("Nikotin")
				.phoneNumber("04423")
				.build();

		when(customerRepoImpl.getUser(1)).thenReturn(customerDTO);

		CustomerDTO savedCustomer = customerService.getCustomer(1);

		Assertions.assertThat(savedCustomer).isEqualTo(customerDTO);
	}

	@Test
	void canGetUserByPhoneNumber_success(){
		CustomerDTO customerDTO = CustomerDTO.builder()
				.id(1)
				.firstName("George")
				.lastName("Nikotin")
				.phoneNumber("04423")
				.build();

		when(customerRepoImpl.getUserByPhoneNumber("04423")).thenReturn(customerDTO);

		CustomerDTO returnedVal = customerService.getUserByPhoneNumber("04423");

		assertThat(returnedVal).isEqualTo(customerDTO);
	}

	@Test
	public void canDeleteUser(){
		CustomerDTO customerDTO = CustomerDTO.builder()
				.id(1)
				.firstName("George")
				.lastName("Nikotin")
				.phoneNumber("04423")
				.build();

//		when(customerRepoImpl.saveUser(CustomerDTO.getRegistrationDTO(customerDTO))).thenReturn(customerDTO);
		when(customerRepoImpl.deleteUser(1)).thenReturn(customerDTO);

		CustomerDTO returnedVal = customerService.deleteUser(1);

		assertThat(returnedVal).isEqualTo(customerDTO);
	}

	@Test
	public void canGetActiveOrderId(){
		when(customerRepoImpl.getActiveOrderId(1)).thenReturn(1);

		Integer activeOrderId = customerService.getActiveOrderId(1);
		assertThat(activeOrderId).isEqualTo(1);
	}

	@Test
	public void canGetActiveOrder(){
		OrderDTO orderDTO = OrderDTO.builder()
						.id(1)
						.dishes(null)
						.timestamp(null)
						.build();

		when(customerRepoImpl.getActiveOrder(1)).thenReturn(orderDTO);

		OrderDTO activeOrder = customerService.getActiveOrder(1);

		Assertions.assertThat(activeOrder).isEqualTo(orderDTO);
	}

	@Test
	public void canAddOrderToUser(){
		customerService.addOrderToUser(1, 1);

		verify(customerRepoImpl).addOrderToUser(1, 1);
	}

}
