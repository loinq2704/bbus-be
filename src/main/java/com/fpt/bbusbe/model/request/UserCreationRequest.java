package com.fpt.bbusbe.model.request;

import com.fpt.bbusbe.model.enums.Gender;
import com.fpt.bbusbe.model.enums.Role;
import com.fpt.bbusbe.model.enums.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@ToString
public class UserCreationRequest implements Serializable {
    @NotBlank(message = "username must not be blank")
    private String username;

    private String password;

    @Email(message = "Email is invalid format!")
    private String email;

    private String phone;

    @NotBlank(message = "name must not be blank")
    private String name;

    private String address;

    private Gender gender;

    private Date dob;

    private String avatar;

    private Role role;

}
