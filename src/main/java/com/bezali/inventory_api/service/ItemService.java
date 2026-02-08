package com.bezali.inventory_api.service;

import com.bezali.inventory_api.dto.item.ItemCreateRequest;
import com.bezali.inventory_api.dto.item.ItemResponse;
import com.bezali.inventory_api.dto.item.ItemUpdateRequest;
import com.bezali.inventory_api.entity.Item;
import com.bezali.inventory_api.exception.NotFoundException;
import com.bezali.inventory_api.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<ItemResponse> getAll() {
        return itemRepository.findAll().stream().map(this::toResponse).toList();
    }

    public ItemResponse getById(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item tidak ditemukan"));
        return toResponse(item);
    }

    public ItemResponse create(ItemCreateRequest req) {
        if (itemRepository.existsBySku(req.getSku())) {
            throw new IllegalArgumentException("SKU sudah terdaftar");
        }

        Item item = Item.builder()
                .sku(req.getSku())
                .name(req.getName())
                .stock(req.getStock())
                .build();

        itemRepository.save(item);
        return toResponse(item);
    }

    public ItemResponse update(Long id, ItemUpdateRequest req) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item tidak ditemukan"));

        item.setName(req.getName());
        item.setStock(req.getStock());

        itemRepository.save(item);
        return toResponse(item);
    }

    public void delete(Long id) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Item tidak ditemukan"));
        itemRepository.delete(item);
    }

    private ItemResponse toResponse(Item i) {
        return ItemResponse.builder()
                .id(i.getId())
                .sku(i.getSku())
                .name(i.getName())
                .stock(i.getStock())
                .createdAt(i.getCreatedAt())
                .build();
    }
}
