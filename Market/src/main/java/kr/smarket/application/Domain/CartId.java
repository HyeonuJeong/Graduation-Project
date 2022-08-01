package kr.smarket.application.Domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class CartId implements Serializable {
	
  private Long memberId;
  private Long productId;
}

