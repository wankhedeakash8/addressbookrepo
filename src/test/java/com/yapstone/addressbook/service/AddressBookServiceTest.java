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

import com.yapstone.addressbook.model.AddressBook;
import com.yapstone.addressbook.repository.AddressBookRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressBookServiceTest {
	
	@InjectMocks
	private AddressBookService addressBookService;
	
	@Mock
	private AddressBookRepository addressBookRepository;
	
	List<AddressBook> addressBooks;
	
	AddressBook addressBook;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		addressBookService.setAddressBookRepository(addressBookRepository);
		
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
		Mockito.when(addressBookRepository.findAddressBookByAddressId(Mockito.anyLong())).thenReturn(addressBook);
		addressBook.setAddress("Dublin");
		AddressBook addressBookResponse = addressBookService.updateAddressDetails(addressBook, 1L);
		assertEquals("Dublin", addressBookResponse.getAddress());
	}
	
	@Test
	public void testDeleteAddressDetails() {
		doNothing().when(addressBookRepository).deleteById(Mockito.any(Long.class));
		addressBookService.deleteAddressDetails(1234L);
		assertTrue("Remove method executed successfully", true);
	}
	
	@Test
	public void testGetAddressDetailsById() {
		Mockito.when(addressBookRepository.findAddressBookByAddressId(Mockito.any(Long.class))).thenReturn(addressBook);
		AddressBook addressBookResponse = addressBookService.getAddressDetailsById(1234L);
		assertEquals("John",addressBookResponse.getFirstName());
	}

}
