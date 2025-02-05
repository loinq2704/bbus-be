package com.fpt.bbusbe.controller.request;

import com.fpt.bbusbe.common.Gender;
import com.fpt.bbusbe.common.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
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

    @NotBlank(message = "name must not be blank")
    private String name;

    private Gender gender;

    private Date dob;

    @Email(message = "Email invalid")
    private String email;

    private String avatar;

    private String phone;

    private UserType type;
}
