package kr.smarket.application.DTO.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class CartResponse {
    Long id;
    Long userId;
    Long productId;
}
