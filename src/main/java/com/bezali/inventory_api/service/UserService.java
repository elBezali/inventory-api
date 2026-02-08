package com.bezali.inventory_api.service;

import com.bezali.inventory_api.dto.user.UserCreateRequest;
import com.bezali.inventory_api.dto.user.UserResponse;
import com.bezali.inventory_api.entity.User;
import com.bezali.inventory_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponse> getAll() {
        return userRepository.findAll().stream().map(this::toResponse).toList();
    }

    public UserResponse create(UserCreateRequest req) {
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new IllegalArgumentException("Email sudah terdaftar");
        }

        User user = User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .build();

        userRepository.save(user);
        return toResponse(user);
    }

    private UserResponse toResponse(User u) {
        return UserResponse.builder()
                .id(u.getId())
                .name(u.getName())
                .email(u.getEmail())
                .createdAt(u.getCreatedAt())
                .build();
    }
}
