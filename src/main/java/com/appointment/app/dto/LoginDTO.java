package com.appointment.app.dto;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDTO {
    @ApiParam("Username")
    @NotBlank(message = "username is required")
    private String username;

    @ApiParam("Password")
    @NotBlank(message = "password is required")
    private String password;
}
