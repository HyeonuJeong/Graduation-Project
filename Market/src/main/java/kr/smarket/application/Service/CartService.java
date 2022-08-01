package kr.smarket.application.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.smarket.application.DTO.Response.ProductResponse;
import kr.smarket.application.Domain.Cart;
import kr.smarket.application.Domain.Member;
import kr.smarket.application.Domain.Product;
import kr.smarket.application.Repository.CartRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;
    private final MemberService memberService;

    public List<Cart> getCart(Long memberId) {
    	List<Cart> cart = cartRepository.findAllByMemberId(memberId);
        return cart;
    }
    
    public List<ProductResponse> getCartProducts(Long id) {
        List<Cart> carts = getCart(id);   
        List<ProductResponse> products = new ArrayList();
        if(carts.size()>0) {
        	for(Cart cart : carts) {
        		products.add(productService.getProduct(cart.getProductId()));
        	}
        }
        
        return products;
    }
    
    @Transactional
    public Cart addCart(UserDetails userDetails, Long productId) {
    	Member member = memberService.getMember(userDetails);
    	Product product = new Product();
    	product.setId(productId);
        Cart cart = new Cart();         
        cart.setMemberId(member.getId());
        cart.setProductId(product.getId());
      
        return cartRepository.save(cart);
    }
    
    @Transactional
    public void deleteCart(UserDetails userDetails) {
    	Member member = memberService.getMember(userDetails);
      
        cartRepository.deleteAllByIdInQuery(member.getId());        
    }
}
