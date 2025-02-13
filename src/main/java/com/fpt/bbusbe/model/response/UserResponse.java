package com.fpt.bbusbe.model.response;

import com.fpt.bbusbe.model.enums.Gender;
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
    private String username;
    private String name;
    private Gender gender;
    private Date dob;
    private String email;
    private String avatar;
    private String phone;
    private String address;

}
