package kr.smarket.application.DTO.Request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SignUpRequest {

    @NotEmpty
    String userName;

    @NotEmpty
    String userId;

    @NotEmpty
    String password;

    @NotEmpty
    String region;

    @NotEmpty
    String address;

    @NotEmpty
    String email;

    @NotEmpty
    String phoneNumber;
}
