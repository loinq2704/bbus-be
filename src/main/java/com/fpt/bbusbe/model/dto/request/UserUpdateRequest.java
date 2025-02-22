package com.fpt.bbusbe.model.dto.request;

import com.fpt.bbusbe.model.enums.Gender;
import com.fpt.bbusbe.model.enums.UserStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@ToString
public class UserUpdateRequest implements Serializable {
    @Min(value = 1, message = "userId must be equal or greater than 1")
    private Long id;

//    @NotBlank(message = "username must not be blank")
    private String username;

    private String name;

    private Gender gender;

    private Date dob;

    private String email;

    private String avatar;

    private String phone;

    private String address;

    private UserStatus status;

}
