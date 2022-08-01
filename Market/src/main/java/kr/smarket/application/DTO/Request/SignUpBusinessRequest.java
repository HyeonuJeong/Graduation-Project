package kr.smarket.application.DTO.Request;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;

@EqualsAndHashCode(callSuper = true)
@Data
public class SignUpBusinessRequest extends SignUpRequest {

    @NotEmpty
    String marketName;

    @NotEmpty
    String storeName;

    @NotEmpty
    String category;

    @NotEmpty
    String officeNumber;
}
