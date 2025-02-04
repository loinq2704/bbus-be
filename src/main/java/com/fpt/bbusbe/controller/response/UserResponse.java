package com.fpt.bbusbe.controller.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@Data
public class UserResponse implements Serializable {
    private Long id;
    private String name;
    private String gender;
    private Date dob;
    private String email;
    private String avatar;
    private String phone;
    private String address;

}
