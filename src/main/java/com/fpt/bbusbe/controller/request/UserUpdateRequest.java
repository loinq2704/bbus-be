package com.fpt.bbusbe.controller.request;

import com.fpt.bbusbe.common.Gender;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@ToString
public class UserUpdateRequest implements Serializable {
    private Long id;
    private String username;
    private String name;
    private Gender gender;
    private Date dob;
    private String email;
    private String avatar;
    private String phone;
    private String address;
}
