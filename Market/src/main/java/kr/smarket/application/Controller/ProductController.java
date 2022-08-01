package kr.smarket.application.Controller;

import kr.smarket.application.DTO.Request.RegisterProductRequest;
import kr.smarket.application.DTO.Response.ProductResponse;
import kr.smarket.application.Domain.Enum.UserType;
import kr.smarket.application.Domain.Member;
import kr.smarket.application.Domain.Product;
import kr.smarket.application.Repository.MemberRepository;
import kr.smarket.application.Service.MemberService;
import kr.smarket.application.Service.ProductService;
import kr.smarket.application.Service.S3UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ProductController {

    private final S3UploadService s3UploadService;
    private final ProductService productService;
    private final MemberService memberService;

    @GetMapping("/product/{id}")
    @ResponseBody
    public ProductResponse getPost(Model model, Principal principal, @PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PostMapping("/register")
    public String upload(
            @RequestParam("image") MultipartFile file,
            @ModelAttribute(name = "request") RegisterProductRequest request,
            Model model,
            Authentication authentication
    ) throws IOException {
        String src = null;
        if (!file.isEmpty()) {
            src = s3UploadService.upload(file, "smarket");
        }
        productService.registerProduct(request, memberService.getMember((UserDetails) authentication.getPrincipal()), src);

        Page<Product> productPage = productService.getProductPage("", "", 0);
        model.addAttribute("marketName", "");
        model.addAttribute("productName", "");
        model.addAttribute("startPage", 0);
        model.addAttribute("nowPage", 0);
        model.addAttribute("maxPage", 10);
        model.addAttribute("totalPages", productPage.getTotalPages());
        model.addAttribute("list", productService.getAllProductList(productPage));
        return "search";
    }
    


}