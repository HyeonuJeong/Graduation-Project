package kr.smarket.application.Service;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kr.smarket.application.DTO.Request.RegisterProductRequest;
import kr.smarket.application.DTO.Response.ProductResponse;
import kr.smarket.application.Domain.Member;
import kr.smarket.application.Domain.Product;
import kr.smarket.application.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final EntityManager entityManager;

    public Product registerProduct(RegisterProductRequest request, Member member, String src) {
        return productRepository.save(
                Product.builder()
                        .content(request.getContent())
                        .member(member)
                        .price(Integer.parseInt(request.getPrice()))
                        .productName(request.getProductName())
                        .src(src)
                        .weight(Integer.parseInt(request.getWeight()))
                        .build()
        );
    }

    public List<ProductResponse> getAllProductList(Page<Product> products) {
        return products.stream()
                .map(ProductResponse::new)
                .collect(Collectors.toList());
    }

    public Page<Product> getProductPage(String marketName, String productName, int page) {
        PageRequest pageRequest = PageRequest.of(page, 6, Sort.by(Sort.Direction.DESC,"id"));
        if (marketName.length()!=0) {
        	Page<Product> result = productRepository.findAllByMarketName(marketName,pageRequest);
            return result;
        }
        if (productName.length()!=0) {
        	Page<Product> result = productRepository.findAllByProductName(productName,pageRequest);
            return result;
//            return productRepository.findAllByProductName(productName,pageRequest);
        }
        return productRepository.findAll(pageRequest);
    }

    public ProductResponse getProduct(Long id) {
        Product product = productRepository.findProductById(id);
        ProductResponse response = new ProductResponse();
        response.setId(product.getId());
        response.setContent(product.getContent());
        response.setMarketName(product.getMember().getMarketName());
        response.setPhoneNumber(product.getMember().getPhoneNumber());
        response.setPrice(product.getPrice());
        response.setProductName(product.getProductName());
        response.setSrc(product.getSrc());
        response.setStoreName(product.getMember().getStoreName());
        response.setUserName(product.getMember().getUserName());
        response.setWeight(product.getWeight());
        return response;
    }

    
}
