package com.fpt.bbusbe.controller.request;

import com.fpt.bbusbe.common.Gender;
import com.fpt.bbusbe.common.UserType;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@ToString
public class UserCreationRequest implements Serializable {
    private String username;
    private String name;
    private Gender gender;
    private Date dob;
    private String email;
    private String avatar;
    private String phone;
    private UserType type;
}
