package com.yapstone.addressbook.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.yapstone.addressbook.model.Address;
import com.yapstone.addressbook.model.AddressBook;
import com.yapstone.addressbook.repository.AddressBookRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressBookServiceTest {
	
	@InjectMocks
	private AddressBookService addressBookService;
	
	@Mock
	private AddressBookRepository addressBookRepository;
	
	private List<AddressBook> addressBooks;
	
	private AddressBook addressBook;
	
	private Address address;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		addressBookService.setAddressBookRepository(addressBookRepository);
		
		address = new Address();
		
		address.setAddressId(1L);
		address.setAddressLine1("Wall Street");
		address.setAddressLine2("New Avenue");
		address.setCity("Dublin");
		address.setCountry("Ireland");
		
		addressBook = new AddressBook();
		addressBook.setAddressBookId(1234L);
		addressBook.setFirstName("John");
		addressBook.setLastName("Doe");
		addressBook.setEmailAddress("john.doe@test.com");
		addressBook.setAddress(address);
		addressBook.setPhoneNumber("089654739");
		
		addressBooks = new ArrayList<>();
		addressBooks.add(addressBook);
	}
	
	@Test
	public void testGetAddressDetails() {
		
		Mockito.when(addressBookRepository.findAll()).thenReturn(addressBooks);
		List<AddressBook> addressDetails = addressBookService.getAddressDetails();
		assertEquals("John",addressDetails.get(0).getFirstName());
	}
	
	@Test
	public void testCreateAddressDetails() {
		
		Mockito.when(addressBookRepository.save(Mockito.any(AddressBook.class))).thenReturn(addressBook);
		AddressBook addressBookResponse = addressBookService.createAddressDetails(addressBook);
		assertEquals("John",addressBookResponse.getFirstName());
		
	}
	
	@Test
	public void testUpdateAddressDetails() {
		Mockito.when(addressBookRepository.save(Mockito.any())).thenReturn(addressBook);
		Mockito.when(addressBookRepository.findAddressBookByAddressBookId(Mockito.anyLong())).thenReturn(addressBook);
		
		
		address.setAddressLine1("Wall Street1234");
		address.setAddressLine2("New Avenue12321");
		address.setCity("Dublin");
		address.setCountry("Ireland");
		
		addressBook.setAddress(address);
		AddressBook addressBookResponse = addressBookService.updateAddressDetails(addressBook, 1L);
		assertEquals("Wall Street1234", addressBookResponse.getAddress().getAddressLine1());
	}
	
	@Test
	public void testDeleteAddressDetails() {
		doNothing().when(addressBookRepository).deleteById(Mockito.any(Long.class));
		addressBookService.deleteAddressDetails(1234L);
		assertTrue("Remove method executed successfully", true);
	}
	
	@Test
	public void testGetAddressDetailsById() {
		Mockito.when(addressBookRepository.findAddressBookByAddressBookId(Mockito.any(Long.class))).thenReturn(addressBook);
		AddressBook addressBookResponse = addressBookService.getAddressDetailsById(1234L);
		assertEquals("John",addressBookResponse.getFirstName());
	}

}
