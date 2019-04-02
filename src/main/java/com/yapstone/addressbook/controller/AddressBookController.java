/**
 * 
 */
package com.yapstone.addressbook.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yapstone.addressbook.model.AddressBook;
import com.yapstone.addressbook.service.AddressBookService;

import lombok.Getter;
import lombok.Setter;

@RestController
@Getter
@Setter
public class AddressBookController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AddressBookController.class);
	
	@Autowired
	private AddressBookService addressBookService;
	
	
	@GetMapping(value="/addressbook")
	public ResponseEntity<List<AddressBook>> getAllAddressDetails() {
		LOGGER.info("Inside getAllAddressDetails method");
		return new ResponseEntity<List<AddressBook>>(addressBookService.getAddressDetails(), HttpStatus.OK);
	}
	
	@PostMapping(value="/addressbook")
	public ResponseEntity<AddressBook> createAddressDetails(@RequestBody AddressBook addressBook) {
		LOGGER.info("Inside createAddressDetails method");
		return new ResponseEntity<AddressBook>(addressBookService.createAddressDetails(addressBook), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="/addressbook/{addressBookId}")
	public ResponseEntity<AddressBook> deleteAddressDetails(@PathVariable(name="addressBookId") Long addressBookId) {
		LOGGER.info("Deleting addressbook details for addressBookId"+addressBookId);
		addressBookService.deleteAddressDetails(addressBookId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(value="/addressbook/{addressBookId}")
	public ResponseEntity<AddressBook> updateAddressDetails(@RequestBody AddressBook addressBook, @PathVariable(name="addressBookId") Long addressBookId){
		LOGGER.info("Updating addressbook details for addressBookId"+addressBookId);
		return new ResponseEntity<AddressBook>(addressBookService.updateAddressDetails(addressBook, addressBookId), HttpStatus.OK);
		
	}
	
	@GetMapping(value="/addressbook/{addressBookId}")
	public ResponseEntity<AddressBook> getAddressDetails(@PathVariable(name="addressBookId") Long addressBookId){
		LOGGER.info("Getting addressbook details for addressBookId"+addressBookId);
		return new ResponseEntity<>(addressBookService.getAddressDetailsById(addressBookId), HttpStatus.OK);
	}
	
	

}
