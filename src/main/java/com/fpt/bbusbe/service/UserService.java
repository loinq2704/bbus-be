package com.fpt.bbusbe.service;

import com.fpt.bbusbe.model.request.UserCreationRequest;
import com.fpt.bbusbe.model.request.UserPasswordRequest;
import com.fpt.bbusbe.model.request.UserUpdateRequest;
import com.fpt.bbusbe.model.response.UserPageResponse;
import com.fpt.bbusbe.model.response.UserResponse;

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
