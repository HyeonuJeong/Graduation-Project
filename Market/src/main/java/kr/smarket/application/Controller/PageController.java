package kr.smarket.application.Controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.smarket.application.DTO.Request.LoginRequest;
import kr.smarket.application.DTO.Request.RegisterProductRequest;
import kr.smarket.application.DTO.Request.SignUpBusinessRequest;
import kr.smarket.application.DTO.Request.SignUpRequest;
import kr.smarket.application.DTO.Response.ProductResponse;
import kr.smarket.application.Domain.Member;
import kr.smarket.application.Domain.Product;
import kr.smarket.application.Service.CartService;
import kr.smarket.application.Service.MemberService;
import kr.smarket.application.Service.MypageService;
import kr.smarket.application.Service.OpinionService;
import kr.smarket.application.Service.ProductService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PageController {

    private final ProductService productService;
    private final MemberService memberService;
    private final MypageService mypageService;
    private final CartService cartService;
    private final OpinionService opinionService;

    @GetMapping("/")
    public String getIndexPage(Model model, Authentication authentication) {
        if(authentication==null) {
            return "index";
        }
        model.addAttribute("userType",memberService.getMember((UserDetails) authentication.getPrincipal()).getUserType());
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage(@ModelAttribute(name = "request") LoginRequest request) {
        return "login";
    }

    @GetMapping("/register")
    public String getRegisterPage(@ModelAttribute(name = "request") RegisterProductRequest request, Model model) {
        return "register";
    }

    @GetMapping("/signup-business")
    public String getSignupBusinessPage(@ModelAttribute(name = "request") SignUpBusinessRequest request, Model model) {
        return "registerBusiness";
    }

    @GetMapping("/signup-client")
    public String getSignupClientPage(@ModelAttribute(name = "request") SignUpRequest request, Model model) {
        return "registerClient";
    }

    @GetMapping("/opinion")
    public String getOpinionPage(Model model) {
        model.addAttribute("opinions",opinionService.getAllOpinions());
        model.addAttribute("opinion",null);
        return "opinion";
    }

    @GetMapping("/search")
    public String getSearchPage(
            @ModelAttribute(name = "data") ProductResponse response,
            @RequestParam(value = "marketName", defaultValue = "") String marketName,
            @RequestParam(value = "productName", defaultValue = "") String productName,
            @RequestParam(value = "page", defaultValue = "0") int page,
            Model model
    ) {
        Page<Product> productPage = productService.getProductPage(marketName,productName,page);
        model.addAttribute("marketName",marketName);
        model.addAttribute("productName",productName);
        model.addAttribute("startPage",(page) - (page % 10));
        model.addAttribute("nowPage",page);
        model.addAttribute("maxPage",10);
        model.addAttribute("totalPages",productPage.getTotalPages());
        model.addAttribute("list",productService.getAllProductList(productPage));
        return "search";
    }
    
    @GetMapping("/mypage")
    public String getMypagePage(
    		Model model, 
    		Authentication authentication,
    		@RequestParam(value = "page", defaultValue = "0") int page
    	) {
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
    
    @PostMapping("/pay")
    public String getPayPage(Authentication authentication, Model model) {    	
    	Member member = memberService.getMember((UserDetails) authentication.getPrincipal());
        List<ProductResponse> products = cartService.getCartProducts(member.getId());
        DecimalFormat amFormat = new DecimalFormat("###,###");
        int totalAm = 0;
        for(int i=0; i<products.size(); i++) {
        	totalAm+=products.get(i).getPrice();
        }
        
    	model.addAttribute("totalAm",amFormat.format(totalAm));
        return "pay";
    }
     
}











