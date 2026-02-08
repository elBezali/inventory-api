package com.bezali.inventory_api.controller;

import com.bezali.inventory_api.common.ApiResponse;
import com.bezali.inventory_api.dto.item.ItemCreateRequest;
import com.bezali.inventory_api.dto.item.ItemResponse;
import com.bezali.inventory_api.dto.item.ItemUpdateRequest;
import com.bezali.inventory_api.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public ApiResponse<List<ItemResponse>> getAll() {
        return ApiResponse.ok("Berhasil mengambil list items", itemService.getAll());
    }

    @GetMapping("/{id}")
    public ApiResponse<ItemResponse> getById(@PathVariable Long id) {
        return ApiResponse.ok("Berhasil mengambil detail item", itemService.getById(id));
    }

    @PostMapping
    public ApiResponse<ItemResponse> create(@Valid @RequestBody ItemCreateRequest req) {
        return ApiResponse.ok("Berhasil membuat item", itemService.create(req));
    }

    @PutMapping("/{id}")
    public ApiResponse<ItemResponse> update(@PathVariable Long id, @Valid @RequestBody ItemUpdateRequest req) {
        return ApiResponse.ok("Berhasil update item", itemService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Object> delete(@PathVariable Long id) {
        itemService.delete(id);
        return ApiResponse.ok("Berhasil hapus item", null);
    }
}

