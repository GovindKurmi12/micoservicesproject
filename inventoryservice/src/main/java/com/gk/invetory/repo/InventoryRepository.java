package com.gk.invetory.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gk.invetory.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	Inventory findByProductId(Long id);
   
}
