package kr.smarket.application.DTO.Response;

import kr.smarket.application.Domain.Product;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ProductResponse {
    private Long id;

    private String productName;

    private String marketName;

    private Integer weight;

    private Integer price;

    private String userName;

    private String phoneNumber;

    private String storeName;

    private String content;

    private String src;

    public ProductResponse(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.marketName = product.getMember().getMarketName();
        this.weight = product.getWeight();
        this.price = product.getPrice();
        this.userName = product.getMember().getUserName();
        this.phoneNumber = product.getMember().getPhoneNumber();
        this.storeName = product.getMember().getStoreName();
        this.content = product.getContent();
        this.src = product.getSrc();
    }
}
