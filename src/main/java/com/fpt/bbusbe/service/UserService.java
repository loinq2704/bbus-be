package com.fpt.bbusbe.service;

import com.fpt.bbusbe.model.entity.User;
import com.fpt.bbusbe.model.request.UserCreationRequest;
import com.fpt.bbusbe.model.request.UserPasswordRequest;
import com.fpt.bbusbe.model.request.UserUpdateRequest;
import com.fpt.bbusbe.model.response.UserPageResponse;
import com.fpt.bbusbe.model.response.UserResponse;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    UserPageResponse findAll(String keyword, String sort, int page, int size);

    UserResponse findById(Long id);

    UserResponse findByUsername(String username);

    UserResponse findByEmail(String email);

    User save(UserCreationRequest req);

    List<User> importUsersFromFile(MultipartFile file);

    void update(UserUpdateRequest req);

    void delete(Long id);

    void changePassword(UserPasswordRequest req);

    void changeStatus(@Valid UserUpdateRequest req);

}
