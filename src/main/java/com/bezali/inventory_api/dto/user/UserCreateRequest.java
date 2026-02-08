package com.bezali.inventory_api.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCreateRequest {

    @NotBlank(message = "name wajib diisi")
    private String name;

    @NotBlank(message = "email wajib diisi")
    @Email(message = "format email tidak valid")
    private String email;
}

