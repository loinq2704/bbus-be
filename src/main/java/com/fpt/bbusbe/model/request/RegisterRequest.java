package com.fpt.bbusbe.model.request;

import com.fpt.bbusbe.model.enums.UserType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
public class RegisterRequest implements Serializable {
    private String username;
    private String password;
    private String name;
}
