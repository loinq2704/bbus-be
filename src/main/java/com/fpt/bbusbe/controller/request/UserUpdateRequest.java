package com.fpt.bbusbe.controller.request;

import com.fpt.bbusbe.common.Gender;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@ToString
public class UserUpdateRequest implements Serializable {
    @NotBlank(message = "username must not be blank")
    @Min(value = 1, message = "userId must be equal or greater than 1")
    private Long id;

    @NotBlank(message = "username must not be blank")
    private String username;

    @NotBlank(message = "username must not be blank")
    private String name;

    private Gender gender;

    private Date dob;

    private String email;

    private String avatar;

    private String phone;

    private String address;

}
