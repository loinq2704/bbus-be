package com.fpt.bbusbe.controller;


import com.fpt.bbusbe.exception.ForBiddenException;
import com.fpt.bbusbe.model.entity.User;
import com.fpt.bbusbe.model.request.UserCreationRequest;
import com.fpt.bbusbe.model.request.UserPasswordRequest;
import com.fpt.bbusbe.model.request.UserUpdateRequest;
import com.fpt.bbusbe.model.response.UserResponse;
import com.fpt.bbusbe.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/user")
@Tag(name = "User Controller")
@Slf4j(topic = "USER-CONTROLLER")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get user list", description = "API retrieve users from db")
    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('sysadmin', 'admin')")
    public ResponseEntity<Object> getList(@RequestParam(required = false) String keyword,
                                          @RequestParam(required = false) String sort,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        log.info("Get user list");

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.OK.value());
        result.put("message", "user list");
        result.put("data", userService.findAll(keyword, sort, page, size));

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "Get user detail", description = "API retrieve user detail by ID")
    @GetMapping("/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> getUserDetail(@PathVariable("userId")
                                                @Min(value = 1, message = "userId must be equal or greater than 1") Long id
    ) {
        log.info("Get user detail by ID: {}", id);

        // Get logged in user
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User userDetails = (User) auth.getPrincipal();
        Long loggedInUser = userDetails.getId();

        //Check id
        if (!loggedInUser.equals(id)) {
            throw new ForBiddenException("You are not allowed to access this resource");
        }

        UserResponse u = userService.findById(id);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.OK.value());
        result.put("message", "get user detail");
        result.put("data", u);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "Create user", description = "API add new user to db")
    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('sysadmin', 'admin')")
    public ResponseEntity<Object> createUser(@RequestBody @Valid UserCreationRequest userCreationRequest
    ) {
        log.info("Create user: {}", userCreationRequest);

        userService.save(userCreationRequest);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.CREATED.value());
        result.put("message", "user created successfully");
        result.put("data", "");

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Operation(summary = "Import user by excel", description = "API import users to db")
    @PostMapping("/import")
    @PreAuthorize("hasAnyAuthority('sysadmin', 'admin')")
    public ResponseEntity<Object> importUsers(@RequestParam("file") MultipartFile file
    ) {
        log.info("Import users from file");

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.CREATED.value());
        result.put("message", "user created successfully");
        result.put("data", userService.importUsersFromFile(file));

        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @Operation(summary = "Update user", description = "API update user in db")
    @PutMapping("/upd")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> updateUser(@RequestBody @Valid UserUpdateRequest userUpdateRequest
    ) {
        log.info("Updating user: {}", userUpdateRequest);

        userService.update(userUpdateRequest);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.ACCEPTED.value());
        result.put("message", "user updated successfully");
        result.put("data", "");

        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Change password", description = "API change password for user")
    @PatchMapping("/change-pwd")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> changePassword(@RequestBody @Valid UserPasswordRequest userPasswordRequest
    ) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.ACCEPTED.value());
        result.put("message", "user change password successfully");
        result.put("data", "");
        userService.changePassword(userPasswordRequest);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Update status", description = "API change status account for user")
    @PatchMapping("/change-status")
    @PreAuthorize("hasAnyAuthority('sysadmin', 'admin')")
    public ResponseEntity<Object> changeStatus(@RequestBody @Valid UserUpdateRequest userUpdateRequest
    ) {
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.ACCEPTED.value());
        result.put("message", "user change status successfully");
        result.put("data", "");
        userService.changeStatus(userUpdateRequest);
        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

    @GetMapping("/confirm-email")
    @PreAuthorize("isAuthenticated()")
    public void confirmEmail(@RequestParam String secretCode, HttpServletResponse response) throws IOException {
        log.info("Confirm email: {}", secretCode);

        try {
            // TODO check or compare secretCode from database
        } catch (Exception e) {
            log.error("Confirm email was failure!, errorMessage = {}", e.getMessage());
        } finally {
            response.sendRedirect("https://google.com");
        }
    }

    @Operation(summary = "Delete user", description = "API inactivate user")
    @DeleteMapping("/del/{userId}")
    @PreAuthorize("hasAnyAuthority('sysadmin', 'admin')")
    public ResponseEntity<Object> deleteUser(@PathVariable("userId") Long id
    ) {
        log.info("Deleting user: {}", id);

        userService.delete(id);

        Map<String, Object> result = new LinkedHashMap<>();
        result.put("status", HttpStatus.ACCEPTED.value());
        result.put("message", "user deleted successfully");
        result.put("data", "");

        return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
    }

}
