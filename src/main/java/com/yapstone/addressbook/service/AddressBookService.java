package com.yapstone.addressbook.service;

import java.util.ArrayList;
import java.util.List;

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

	@Autowired
	private AddressBookRepository addressBookRepository;
	
	public List<AddressBook> getAddressDetails() {
		List<AddressBook> addressBooks = new ArrayList<>();
		addressBookRepository.findAll().forEach(addressBooks :: add);
		return addressBooks;
	}
	
	public AddressBook createAddressDetails(AddressBook addressBook) {
		return addressBookRepository.save(addressBook);
	}
	
	public AddressBook updateAddressDetails(AddressBook addressBook, Long addressId) {
		AddressBook addressBookToUpdate =getAddressDetailsById(addressId);
		if(addressBook.getAddress()!=null) {
			addressBookToUpdate.setAddress(addressBook.getAddress());
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
	
	public void deleteAddressDetails(Long addressId) {
		addressBookRepository.deleteById(addressId);
	}
	
	public AddressBook getAddressDetailsById(Long addressId) {
		return addressBookRepository.findAddressBookByAddressId(addressId);
	}
	
}


