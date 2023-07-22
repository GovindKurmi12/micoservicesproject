package com.gk.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gk.order.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long>{

}
