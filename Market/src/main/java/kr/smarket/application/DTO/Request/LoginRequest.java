package kr.smarket.application.DTO.Request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequest {

    @NotEmpty
    String userId;

    @NotEmpty
    String password;
}
