package com.fpt.bbusbe.controller.request;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserPasswordRequest {
    private Long id;
    private String password;
    private String confirmPassword;
}
