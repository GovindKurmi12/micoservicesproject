package com.gk.order.service.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gk.order.model.Address;
import com.gk.order.repository.AddressRepository;

@Component
public class AddressHelper {

	@Autowired
	private AddressRepository addressRepository;

	public Address checkAddressExist(Address address) {
		Address existingAddress = null;
		if (address != null && address.getId() != null) {
			existingAddress = addressRepository.findById(address.getId()).orElse(null);
			if (existingAddress != null) {
				existingAddress.setCity(address.getCity());
				existingAddress.setStreet(address.getStreet());
				existingAddress.setCountry(address.getCountry());
				return existingAddress;
			}
		}
		return address;
	}
}
