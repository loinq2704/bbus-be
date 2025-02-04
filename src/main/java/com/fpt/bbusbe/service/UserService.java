package com.fpt.bbusbe.service;

import com.fpt.bbusbe.controller.request.UserCreationRequest;
import com.fpt.bbusbe.controller.request.UserPasswordRequest;
import com.fpt.bbusbe.controller.request.UserUpdateRequest;
import com.fpt.bbusbe.controller.response.UserPageResponse;
import com.fpt.bbusbe.controller.response.UserResponse;

import java.util.List;

public interface UserService {

    UserPageResponse findAll(String keyword, String sort, int page, int size);

    UserResponse findById(Long id);

    UserResponse findByUsername(String username);

    UserResponse findByEmail(String email);

    Long save(UserCreationRequest req);

    void update(UserUpdateRequest req);

    void delete(Long id);

    void changePassword(UserPasswordRequest req);
}
