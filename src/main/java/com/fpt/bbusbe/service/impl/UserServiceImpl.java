package com.fpt.bbusbe.service.impl;

import com.fpt.bbusbe.model.entity.Role;
import com.fpt.bbusbe.model.entity.UserHasRole;
import com.fpt.bbusbe.model.enums.UserStatus;
import com.fpt.bbusbe.model.enums.UserType;
import com.fpt.bbusbe.model.request.UserCreationRequest;
import com.fpt.bbusbe.model.request.UserPasswordRequest;
import com.fpt.bbusbe.model.request.UserUpdateRequest;
import com.fpt.bbusbe.model.response.UserPageResponse;
import com.fpt.bbusbe.model.response.UserResponse;
import com.fpt.bbusbe.exception.InvalidDataException;
import com.fpt.bbusbe.exception.ResourceNotFoundException;
import com.fpt.bbusbe.model.entity.User;
import com.fpt.bbusbe.repository.RoleRepository;
import com.fpt.bbusbe.repository.UserHasRoleRepository;
import com.fpt.bbusbe.repository.UserRepository;
import com.fpt.bbusbe.service.EmailService;
import com.fpt.bbusbe.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j(topic = "USER-SERVICE")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserHasRoleRepository userHasRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Override
    public UserPageResponse findAll(String keyword, String sort, int page, int size) {
        log.info("findAll start");

        // Sorting
        Sort.Order order = new Sort.Order(Sort.Direction.ASC, "id");
        if (StringUtils.hasLength(sort)) {
            Pattern pattern = Pattern.compile("(\\w+?)(:)(.*)"); // tencot:asc|desc
            Matcher matcher = pattern.matcher(sort);
            if (matcher.find()) {
                String columnName = matcher.group(1);
                if (matcher.group(3).equalsIgnoreCase("asc")) {
                    order = new Sort.Order(Sort.Direction.ASC, columnName);
                } else {
                    order = new Sort.Order(Sort.Direction.DESC, columnName);
                }
            }
        }

        // Xu ly truong hop FE muon bat dau voi page = 1
        int pageNo = 0;
        if (page > 0) {
            pageNo = page - 1;
        }

        // Paging
        Pageable pageable = PageRequest.of(pageNo, size, Sort.by(order));

        Page<User> entityPage;

        if (StringUtils.hasLength(keyword)) {
            keyword = "%" + keyword.toLowerCase() + "%";
            entityPage = userRepository.searchByKeyword(keyword, pageable);
        } else {
            entityPage = userRepository.findAll(pageable);
        }

        return getUserPageResponse(page, size, entityPage);
    }

    @Override
    public UserResponse findById(Long id) {
        User user = getUserEntity(id);
        return UserResponse.builder()
                .id(id)
                .name(user.getName())
                .email(user.getEmail())
                .address(user.getAddress())
                .avatar(user.getAvatar())
                .dob(user.getDob())
                .gender(user.getGender())
                .phone(user.getPhone())
                .build();
    }

    @Override
    public UserResponse findByUsername(String username) {
        return null;
    }

    @Override
    public UserResponse findByEmail(String email) {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long save(UserCreationRequest req) {

        User userByEmail = userRepository.findByEmail(req.getEmail());
        if (userByEmail != null) {
            throw new InvalidDataException("User with this email: " + req.getEmail() + " already exists");
        }

        User userByName = userRepository.findByUsername(req.getUsername());
        if (userByName != null) {
            throw new InvalidDataException("User with this username: " + req.getUsername() + " already exists");
        }


        // insert into tbl_user
        User user = new User();
        user.setName(req.getName());
        user.setGender(req.getGender());
        user.setDob(req.getDob());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        user.setUsername(req.getUsername());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        user.setAddress(req.getAddress());
        user.setAvatar(req.getAvatar());
        user.setType(UserType.USER);
        user.setStatus(UserStatus.ACTIVE);
        log.info("Saving user {}", user);

        userRepository.save(user);

        //insert into tbl_user_has_role
        Role role = roleRepository.findByName(req.getRole().toString())
                .orElseThrow(() -> new ResourceNotFoundException("Role not found"));
        UserHasRole userHasRole = new UserHasRole(user, role);
        userHasRoleRepository.save(userHasRole);

        // send email confirm
        try {
            emailService.emailVerification(req.getEmail(), req.getName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return user.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserUpdateRequest req) {
        log.info("Updating user {}", req);
        //get user by id
        User user = getUserEntity(req.getId());
        //set data
        user.setName(req.getName());
        user.setGender(req.getGender());
        user.setDob(req.getDob());
        user.setEmail(req.getEmail());
        user.setPhone(req.getPhone());
        user.setUsername(req.getUsername());
        user.setStatus(req.getStatus());
        userRepository.save(user);

    }

    @Override
    public void delete(Long id) {
        //get user by id
        User user = getUserEntity(id);
        user.setStatus(UserStatus.INACTIVE);
        userRepository.save(user);
        log.info("Deleted user {}", id);
    }

    @Override
    public void changePassword(UserPasswordRequest req) {
        //get user by id
        User user = getUserEntity(req.getId());

        if (req.getPassword().equals(req.getConfirmPassword())) {
            user.setPassword(passwordEncoder.encode(req.getPassword()));
        }
        userRepository.save(user);
        log.info("Changing password for user {}", user);
    }

    @Override
    public void changeStatus(UserUpdateRequest req) {
        //get user by id
        User user = getUserEntity(req.getId());

        if (req.getStatus() != null) {
            user.setStatus(req.getStatus());
        }
        userRepository.save(user);
        log.info("Changing password for user {}", user);
    }

    /**
     * Get user by id
     * @param id
     * @return
     */
    private User getUserEntity(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    /**
     * Convert EserEntities to UserResponse
     *
     * @param page
     * @param size
     * @param userEntities
     * @return
     */
    private static UserPageResponse getUserPageResponse(int page, int size, Page<User> userEntities) {
        log.info("Convert User Entity Page");

        List<UserResponse> userList = userEntities.stream().map(entity -> UserResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .gender(entity.getGender())
                .dob(entity.getDob())
                .username(entity.getUsername())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .avatar(entity.getAvatar())
                .status(entity.getStatus())
                .roles(entity.getRoleNames())
                .build()
        ).toList();

        UserPageResponse response = new UserPageResponse();
        response.setPageNumber(page);
        response.setPageSize(size);
        response.setTotalElements(userEntities.getTotalElements());
        response.setTotalPages(userEntities.getTotalPages());
        response.setUsers(userList);

        return response;
    }
}
