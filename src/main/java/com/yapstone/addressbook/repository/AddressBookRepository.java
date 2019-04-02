package com.yapstone.addressbook.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.yapstone.addressbook.model.AddressBook;

@Repository
public interface AddressBookRepository extends PagingAndSortingRepository<AddressBook, Long>{
	
	public AddressBook findAddressBookByAddressBookId(Long addressBookId);

}
