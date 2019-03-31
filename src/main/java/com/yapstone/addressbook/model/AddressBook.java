package com.yapstone.addressbook.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
@Table(name="ADDRESS_BOOK")
public class AddressBook implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ADDRESS_ID")
	private Long addressId;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;
	
	@Column(name="ADDRESS")
	private String address;
	
	@Column(name="EMAIL_ADDRESS")
	private String emailAddress;

}
