package com.fpt.bbusbe.controller.request;

import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
public class UserUpdateRequest implements Serializable {
    private Long id;
    private String username;
    private String name;
    private String gender;
    private Date dob;
    private String email;
    private String avatar;
    private String phone;
    private String address;
}
