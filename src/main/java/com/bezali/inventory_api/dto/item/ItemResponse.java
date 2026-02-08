package com.bezali.inventory_api.dto.item;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ItemResponse {
    private Long id;
    private String sku;
    private String name;
    private Integer stock;
    private Instant createdAt;
}
