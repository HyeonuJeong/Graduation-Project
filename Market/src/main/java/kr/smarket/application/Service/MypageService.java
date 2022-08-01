package kr.smarket.application.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kr.smarket.application.DTO.Request.RegisterProductRequest;
import kr.smarket.application.DTO.Response.ProductResponse;
import kr.smarket.application.Domain.Cart;
import kr.smarket.application.Domain.Member;
import kr.smarket.application.Domain.Product;
import kr.smarket.application.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MypageService {

    private final ProductRepository productRepository;
    private final EntityManager entityManager;

    private final ProductService productService;
    private final MemberService memberService;
    private final CartService cartService;
    private final OpinionService opinionService;
    
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
    

}
