package io.leo.coconut.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author Leo
 */
@Data
public class LoginDto {

    @NotBlank(message = "username cannot be blank")
    private String username;

    @NotBlank(message = "password cannot be blank")
    private String password;

}
