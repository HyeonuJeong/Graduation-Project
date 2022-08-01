package kr.smarket.application.DTO.Request;

import lombok.Data;

@Data
public class RegisterProductRequest {
    String productName;
    String weight;
    String content;
    String price;
    Long id;
}
