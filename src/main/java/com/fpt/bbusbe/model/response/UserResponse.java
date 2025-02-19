package com.fpt.bbusbe.model.response;

import com.fpt.bbusbe.model.entity.UserHasRole;
import com.fpt.bbusbe.model.enums.Gender;
import com.fpt.bbusbe.model.enums.Role;
import com.fpt.bbusbe.model.enums.UserStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
    private UserStatus status;
    private List<String> roles;

}
