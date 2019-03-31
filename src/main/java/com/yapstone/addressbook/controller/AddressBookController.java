/**
 * 
 */
package com.yapstone.addressbook.controller;

import java.util.List;

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

/**
 * @author Akash
 *
 */
@RestController
@Getter
@Setter
public class AddressBookController {
	
	@Autowired
	private AddressBookService addressBookService;
	
	
	@GetMapping(value="/addressbooks")
	public ResponseEntity<List<AddressBook>> getAllAddressDetails() {
		return new ResponseEntity<List<AddressBook>>(addressBookService.getAddressDetails(), HttpStatus.OK);
	}
	
	@PostMapping(value="/addressbook")
	public ResponseEntity<AddressBook> createAddressDetails(@RequestBody AddressBook addressBook) {
		return new ResponseEntity<AddressBook>(addressBookService.createAddressDetails(addressBook), HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="/addressbook/{addressId}")
	public ResponseEntity<AddressBook> deleteAddressDetails(@PathVariable(name="addressId") Long addressId) {
		addressBookService.deleteAddressDetails(addressId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping(value="/addressbook/{addressId}")
	public ResponseEntity<AddressBook> updateAddressDetails(@RequestBody AddressBook addressBook, @PathVariable(name="addressId") Long addressId){
		return new ResponseEntity<AddressBook>(addressBookService.updateAddressDetails(addressBook, addressId), HttpStatus.OK);
		
	}
	
	@GetMapping(value="/addressbook/{addressId}")
	public ResponseEntity<AddressBook> getAddressDetails(@PathVariable(name="addressId") Long addressId){
		return new ResponseEntity<>(addressBookService.getAddressDetailsById(addressId), HttpStatus.OK);
	}
	
	

}
