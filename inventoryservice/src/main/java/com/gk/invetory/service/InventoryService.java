package com.gk.invetory.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import com.gk.invetory.dto.InventoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gk.invetory.model.Inventory;
import com.gk.invetory.model.Order;
import com.gk.invetory.repo.InventoryRepository;
import com.gk.invetory.service.external.call.ExternalServiceCall;

import jakarta.transaction.Transactional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository repoInventoryRepository;

    @Autowired
    private ExternalServiceCall externalServiceCall;

    public List<Inventory> addAllInventory(List<Inventory> inventories) {
        List<Inventory> inventList = new ArrayList<>();
        inventories.forEach(invent -> {
            boolean productData = externalServiceCall.findProduct(invent.getId());
            if (productData) {
                repoInventoryRepository.saveAndFlush(invent);
                inventList.add(invent);
            }
        });

        return inventList;
    }

    @Transactional
    public boolean checkProduct(Order order) {
        return false;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    public Long getTotalStock(Long productId) {
        Inventory inventory = repoInventoryRepository.findByProductId(productId);
        return inventory.getStock();
    }

    public Inventory createInventory(InventoryDto inventoryDto) {
        Inventory inventory = convertInventoryDtoToInventory(inventoryDto);
        return repoInventoryRepository.save(inventory);
    }

    public Inventory convertInventoryDtoToInventory(InventoryDto inventoryDto) {
        Inventory inventory = new Inventory();
        inventory.setId(inventoryDto.getId());
        inventory.setProductId(inventoryDto.getProductId());
        inventory.setStock(inventoryDto.getStock());
        return inventory;
    }

}
