package com.gk.invetory.controller;

import java.util.List;

import com.gk.invetory.dto.InventoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gk.invetory.model.Inventory;
import com.gk.invetory.model.Order;
import com.gk.invetory.service.InventoryService;

@RequestMapping("inventory")
@RestController
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;

	@PostMapping("addinventory")
	public List<Inventory> addAllInventory(@RequestBody List<Inventory> inventories) {
		return inventoryService.addAllInventory(inventories);
	}

	@PostMapping("checkproduct")
	public boolean checkProduct(@RequestBody Order order) {
		return inventoryService.checkProduct(order);
	}

	@PostMapping("createinventory")
	public Inventory createInventory(@RequestBody InventoryDto inventoryDto){
		return inventoryService.createInventory(inventoryDto);
	}

	@GetMapping("gettotalstock/{id}")
	public Long getTotalStock(@PathVariable Long id) {
		return inventoryService.getTotalStock(id);
	}
}
