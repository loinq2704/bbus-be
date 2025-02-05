package com.fpt.bbusbe.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserPasswordRequest {
    @NotNull(message = "id must not be null")
    @Min(value = 1, message = "userId must be equal or greater than 1")
    private Long id;

    @NotBlank(message = "id must not be blank")
    private String password;

    @NotBlank(message = "id must not be blank")
    private String confirmPassword;
}
