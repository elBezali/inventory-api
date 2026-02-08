package com.bezali.inventory_api.dto.item;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItemCreateRequest {

    @NotBlank(message = "sku wajib diisi")
    private String sku;

    @NotBlank(message = "name wajib diisi")
    private String name;

    @NotNull(message = "stock wajib diisi")
    @Min(value = 0, message = "stock minimal 0")
    private Integer stock;
}
