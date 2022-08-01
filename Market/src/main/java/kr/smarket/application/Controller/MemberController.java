package kr.smarket.application.Controller;

import kr.smarket.application.DTO.Request.LoginRequest;
import kr.smarket.application.DTO.Request.SignUpBusinessRequest;
import kr.smarket.application.DTO.Request.SignUpRequest;
import kr.smarket.application.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup-business")
    public String signUpBusiness(
            @ModelAttribute(name = "request") @Valid SignUpBusinessRequest request,
            Model model,
            Principal principal
    ) {
        memberService.createMemberBusiness(request);
        return "login";
    }

    @PostMapping("/signup-client")
    public String signUpClient(
            @ModelAttribute(name = "request") @Valid SignUpRequest request,
            Model model,
            Principal principal
    ) {
        memberService.createMemberClient(request);
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @ModelAttribute(name = "request") @Valid LoginRequest request,
            Model model
    ) {
        UserDetails userDetails = memberService.loadUserByUsername(request.getUserId());
        System.out.println(memberService.getMember(userDetails).getUserType());
        model.addAttribute("userType",memberService.getMember(userDetails).getUserType());
        return "index";
    }
}
