package com.gk.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gk.order.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{

}
