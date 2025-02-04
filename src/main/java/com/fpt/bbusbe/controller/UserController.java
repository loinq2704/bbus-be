package com.fpt.bbusbe.controller;


import com.fpt.bbusbe.controller.request.UserCreationRequest;
import com.fpt.bbusbe.controller.request.UserPasswordRequest;
import com.fpt.bbusbe.controller.request.UserUpdateRequest;
import com.fpt.bbusbe.controller.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
@Tag(name = "User Controller")
public class UserController {

    @Operation(summary = "Get user list", description = "API retrieve users from db")
    @GetMapping("/list")
    public Map<String, Object> getList(@RequestParam(required = false) String keyword,
                                       @RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size) {
        List<UserResponse> users = new ArrayList<>();
        UserResponse u = UserResponse.builder()
                .id(1L)
                .name("John")
                .email("john@gmail.com")
                .address("123 Main Street")
                .avatar("xxx")
                .dob(new Date())
                .gender("Male")
                .phone("123456789")
                .build();
        UserResponse u1 = UserResponse.builder().build();
        users.add(u);
        users.add(u1);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.OK.value());
        result.put("message", "user list");
        result.put("data", users);

        return result;
    }

    @Operation(summary = "Get user detail", description = "API retrieve user detail by ID")
    @GetMapping("/{userId}")
    public Map<String, Object> getUserDetail(@PathVariable("userId") @Min(1) Long id
    ) {
        UserResponse u = UserResponse.builder()
                .id(1L)
                .name("John")
                .email("john@gmail.com")
                .address("123 Main Street")
                .avatar("xxx")
                .dob(new Date())
                .gender("Male")
                .phone("123456789")
                .build();

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.OK.value());
        result.put("message", "user list");
        result.put("data", u);

        return result;
    }

    @Operation(summary = "Create user", description = "API add new user to db")
    @PostMapping("/add")
    public Map<String, Object> createUser(UserCreationRequest userCreationRequest
    ) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.CREATED.value());
        result.put("message", "user created successfully");
        result.put("data", 5);

        return result;
    }

    @Operation(summary = "Update user", description = "API update user in db")
    @PutMapping("/upd")
    public Map<String, Object> updateUser(UserUpdateRequest userUpdateRequest
    ) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.ACCEPTED.value());
        result.put("message", "user updated successfully");
        result.put("data", "");

        return result;
    }

    @Operation(summary = "Change password", description = "API change password for user")
    @PatchMapping("/change-pwd")
    public Map<String, Object> changePassword(UserPasswordRequest userPasswordRequest
    ) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.NO_CONTENT.value());
        result.put("message", "user password successfully");
        result.put("data", "");

        return result;
    }

    @Operation(summary = "Delete user", description = "API inactivate user")
    @DeleteMapping("/del/{userId}")
    public Map<String, Object> deleteUser(@PathVariable("userId") Long id
    ) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.RESET_CONTENT.value());
        result.put("message", "user deleted successfully");
        result.put("data", "");

        return result;
    }

}
