package com.bezali.inventory_api.repository;

import com.bezali.inventory_api.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    boolean existsBySku(String sku);
}
