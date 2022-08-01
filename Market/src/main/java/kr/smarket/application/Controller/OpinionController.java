package kr.smarket.application.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.smarket.application.DTO.Request.ModifyMypageRequest;
import kr.smarket.application.DTO.Request.RegisterOpinionRequest;
import kr.smarket.application.DTO.Response.OpinionResponse;
import kr.smarket.application.Service.OpinionService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class OpinionController {

    private final OpinionService opinionService;

    @GetMapping("/opinion/{id}")
    @ResponseBody
    public OpinionResponse getPost(@PathVariable Long id) {
        return opinionService.getOpinion(id);
    }

    @GetMapping("/opinions")
    public String getSearchedOpinions(
            @RequestParam(value = "content", defaultValue = "") String content,
            Model model
    ) {
        model.addAttribute("opinions",opinionService.getSearchedOpinions(content));
        return "opinion";
    }
    
    @PostMapping("/opinion")
    public String createOpinion(
            @ModelAttribute(name = "request") RegisterOpinionRequest request,
            Authentication authentication,
            Model model
    ) {
        OpinionResponse opinion = opinionService.createOpinion(request.getContent(), (UserDetails) authentication.getPrincipal());
        System.out.println(opinion);
        model.addAttribute("opinions",opinionService.getAllOpinions());
        model.addAttribute("opinion",opinion);
        return "opinion";
    }
}
