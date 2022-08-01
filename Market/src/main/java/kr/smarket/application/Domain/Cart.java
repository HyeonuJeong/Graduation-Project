package kr.smarket.application.Domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Cart")
@IdClass(CartId.class)
public class Cart implements Serializable {
 
  @Id
  @Column(nullable=false, name = "member_id")
  private Long memberId; 
 
  @Id
  @Column(nullable=false, name = "product_id")
  private Long productId;
 
}