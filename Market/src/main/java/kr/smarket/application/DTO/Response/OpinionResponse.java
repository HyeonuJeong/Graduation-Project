package kr.smarket.application.DTO.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class OpinionResponse {
    Long id;
    String content;
    String userName;
    String time;
}
