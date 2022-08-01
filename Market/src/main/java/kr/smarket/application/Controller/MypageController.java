package kr.smarket.application.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.smarket.application.DTO.Request.ModifyMypageRequest;
import kr.smarket.application.DTO.Request.RegisterProductRequest;
import kr.smarket.application.DTO.Response.ProductResponse;
import kr.smarket.application.Domain.Member;
import kr.smarket.application.Domain.Product;
import kr.smarket.application.Service.CartService;
import kr.smarket.application.Service.MemberService;
import kr.smarket.application.Service.ProductService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MypageController {
    private final ProductService productService;
	private final MemberService memberService;
	private final CartService cartService;

    @PostMapping("/mypage")
    @ResponseBody
    public void updateMemberInfo(@RequestBody ModifyMypageRequest request, Model model, Authentication authentication) {
    	memberService.updateMember((UserDetails) authentication.getPrincipal(), request.getUserName(), request.getPhoneNumber());    
    }
    
    @PostMapping("/payFin")
    public String payOrderFinish(
    		Model model, 
    		Authentication authentication,
    		@RequestParam(value = "page", defaultValue = "0") int page
    	) {
    	cartService.deleteCart((UserDetails) authentication.getPrincipal());
    	Member member = memberService.getMember((UserDetails) authentication.getPrincipal());
        List<ProductResponse> products = cartService.getCartProducts(member.getId());   

        int pLength = products.size();        
        int startIndex = page*6;
        
        int endIndex = pLength;
        if(((page+1)*6) < pLength) endIndex = (page+1) * 6;
        
        int totalPages = products.size()/6;
        if(pLength%6 > 0) totalPages+=1;

        List<ProductResponse> productsPage = new ArrayList();
        for(int i=startIndex; i<endIndex; i++) {
    		productsPage.add(products.get(i));
    	}
        
        model.addAttribute("userInfo",member);
        model.addAttribute("products",productsPage);
        
        model.addAttribute("startPage",(page) - (page % 10));
        model.addAttribute("nowPage",page);
        model.addAttribute("maxPage",10);
        model.addAttribute("totalPages",totalPages);
        return "mypage";
    }  
    
    @PostMapping("/addCart")
    @ResponseBody
    public void addCart(@RequestBody RegisterProductRequest request, Authentication authentication) {
    	Product product = new Product();
    	product.setId(request.getId());
    	
    	cartService.addCart((UserDetails) authentication.getPrincipal(), request.getId());
    }
    
 }