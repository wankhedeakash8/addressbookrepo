package com.yapstone.addressbook.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yapstone.addressbook.model.AddressBook;
import com.yapstone.addressbook.repository.AddressBookRepository;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter 
public class AddressBookService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddressBookService.class);
	
	@Autowired
	private AddressBookRepository addressBookRepository;
	
	public List<AddressBook> getAddressDetails() {
		LOGGER.info("Inside the getAddressDetails");
		List<AddressBook> addressBooks = new ArrayList<>();
		addressBookRepository.findAll().forEach(addressBooks :: add);
		LOGGER.info("AddressBooks fetched from backend {}", addressBooks.size());
		return addressBooks;
	}
	
	public AddressBook createAddressDetails(AddressBook addressBook) {
		LOGGER.info("Inside the createAddressDetails");
		return addressBookRepository.save(addressBook);
	}
	
	public AddressBook updateAddressDetails(AddressBook addressBook, Long addressBookId) {
		LOGGER.info("Updating details for addressBookId {}", addressBookId);
		AddressBook addressBookToUpdate =getAddressDetailsById(addressBookId);
		
		if(addressBook.getAddress()!=null) {
			LOGGER.info("Updating Address details for addressId {}", addressBook.getAddress().getAddressId());
			if(addressBook.getAddress().getAddressLine1()!=null) {
				addressBookToUpdate.getAddress().setAddressLine1(addressBook.getAddress().getAddressLine1());
			}
			
			if(addressBook.getAddress().getAddressLine2() != null) {
				addressBookToUpdate.getAddress().setAddressLine2(addressBook.getAddress().getAddressLine2());
			}
			
			if(addressBook.getAddress().getCity() != null) {
				addressBookToUpdate.getAddress().setCity(addressBook.getAddress().getCity());
			}
			
			if(addressBook.getAddress().getCountry() != null) {
				addressBookToUpdate.getAddress().setCountry(addressBook.getAddress().getCountry());
			}
		}
		
		if(addressBook.getEmailAddress()!=null) {
			addressBookToUpdate.setEmailAddress(addressBook.getEmailAddress());
		}
		
		if(addressBook.getFirstName()!=null) {
			addressBookToUpdate.setFirstName(addressBook.getFirstName());
		}
		
		if(addressBook.getLastName() != null) {
			addressBookToUpdate.setLastName(addressBook.getLastName());
		}
		
		if(addressBook.getPhoneNumber() != null) {
			addressBookToUpdate.setPhoneNumber(addressBook.getPhoneNumber());
		}
		return addressBookRepository.save(addressBookToUpdate);
	}
	
	public void deleteAddressDetails(Long addressBookId) {
		LOGGER.info("Deleting details for addressBookId {}", addressBookId);
		addressBookRepository.deleteById(addressBookId);
	}
	
	public AddressBook getAddressDetailsById(Long addressBookId) {
		LOGGER.info("Getting details for addressBookId {}", addressBookId);
		return addressBookRepository.findAddressBookByAddressBookId(addressBookId);
	}
	
}


