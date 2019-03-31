package com.yapstone.addressbook.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.yapstone.addressbook.model.AddressBook;

@Repository
public interface AddressBookRepository extends CrudRepository<AddressBook, Long>{
	
	public AddressBook findAddressBookByAddressId(Long addressId);

}
