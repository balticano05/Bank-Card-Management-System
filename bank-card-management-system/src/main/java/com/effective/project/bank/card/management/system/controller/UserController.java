package com.effective.project.bank.card.management.system.controller;

import com.effective.project.bank.card.management.system.dto.request.UserUpdateRequest;
import com.effective.project.bank.card.management.system.dto.response.UserUpdateResponse;
import com.effective.project.bank.card.management.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/users/{userId}")
    public UserUpdateResponse updateUser(
            @PathVariable Long userId,
            @RequestBody UserUpdateRequest userUpdateRequest
    ) {
        return userService.updateUser(userId, userUpdateRequest);
    }

    @DeleteMapping("/users/{userId}")
    public Long deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }

}