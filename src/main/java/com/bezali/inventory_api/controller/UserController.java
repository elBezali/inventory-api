package com.bezali.inventory_api.controller;

import com.bezali.inventory_api.common.ApiResponse;
import com.bezali.inventory_api.dto.user.UserCreateRequest;
import com.bezali.inventory_api.dto.user.UserResponse;
import com.bezali.inventory_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ApiResponse<List<UserResponse>> getAll() {
        return ApiResponse.ok("Berhasil mengambil list users", userService.getAll());
    }

    @PostMapping
    public ApiResponse<UserResponse> create(@Valid @RequestBody UserCreateRequest req) {
        return ApiResponse.ok("Berhasil membuat user", userService.create(req));
    }
}
