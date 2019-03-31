package com.yapstone.addressbook.controller;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yapstone.addressbook.model.AddressBook;
import com.yapstone.addressbook.service.AddressBookService;

@RunWith(SpringRunner.class)
@WebMvcTest(value=AddressBookController.class, secure=false)
public class AddressBookControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AddressBookService addressBookService;
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	List<AddressBook> addressBooks;
	
	AddressBook addressBook;
	
	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
		addressBook = new AddressBook();
		addressBook.setAddressId(1234L);
		addressBook.setFirstName("John");
		addressBook.setLastName("Doe");
		addressBook.setEmailAddress("john.doe@test.com");
		addressBook.setAddress("London");
		addressBook.setPhoneNumber("089654739");
		
		addressBooks = new ArrayList<>();
		addressBooks.add(addressBook);
		
	}
	
	@Test
	public void testGetAllAddressDetails() throws Exception {
		when(addressBookService.getAddressDetails()).thenReturn(addressBooks);
		mockMvc.perform(MockMvcRequestBuilders.get("/addressbooks").
				accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	public void testCreateAddressDetails() throws Exception {
		
		when(addressBookService.createAddressDetails(Mockito.any())).thenReturn(addressBook);
		
		AddressBook addressBookRequest = new AddressBook();
		addressBookRequest.setFirstName("John");
		addressBookRequest.setLastName("Doe");
		addressBookRequest.setEmailAddress("john.doe@test.com");
		addressBookRequest.setAddress("London");
		addressBookRequest.setPhoneNumber("089654739");
		
		ObjectMapper objectMapper = new ObjectMapper();
		String addressBookJson = objectMapper.writeValueAsString(addressBookRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/addressbook").contentType(MediaType.APPLICATION_JSON).
				content(addressBookJson)).andExpect(status().isCreated()).andDo(print());
		
	}
	
	@Test
	public void testUpdateAddressDetails() throws Exception{
		when(addressBookService.updateAddressDetails(Mockito.any(), Mockito.any())).thenReturn(addressBook);
		AddressBook addressBookRequest = new AddressBook();
		addressBookRequest.setFirstName("John");
		addressBookRequest.setLastName("Doe");
		addressBookRequest.setEmailAddress("john.doe@test.com");
		addressBookRequest.setAddress("London");
		addressBookRequest.setPhoneNumber("089654739");
		
		ObjectMapper objectMapper = new ObjectMapper();
		String addressBookJson = objectMapper.writeValueAsString(addressBookRequest);
		
		mockMvc.perform(MockMvcRequestBuilders.put("/addressbook/1").contentType(MediaType.APPLICATION_JSON).
				content(addressBookJson)).andExpect(status().isOk()).andDo(print());
	}
	
	@Test
	public void testDeleteAddressDetails() throws Exception{
		doNothing().when(addressBookService).deleteAddressDetails(Mockito.any());
		mockMvc.perform(MockMvcRequestBuilders.delete("/addressbook/1234").
				accept(MediaType.APPLICATION_JSON)).andExpect(status().isNoContent())
		.andDo(print());
		
	}
	
	@Test
	public void testGetAddressDetails() throws Exception{
		when(addressBookService.getAddressDetails()).thenReturn(addressBooks);
		mockMvc.perform(MockMvcRequestBuilders.get("/addressbook/1234").
				accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andDo(print());
		
	}
	
	
}
