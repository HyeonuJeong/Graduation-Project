package kr.smarket.application.Service;

import kr.smarket.application.DTO.Response.OpinionResponse;
import kr.smarket.application.Domain.Member;
import kr.smarket.application.Domain.Opinion;
import kr.smarket.application.Repository.OpinionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OpinionService {

    private final OpinionRepository opinionRepository;
    private final MemberService memberService;

    public List<OpinionResponse> getAllOpinions() {
    	List<OpinionResponse> list = convertToResponseList(opinionRepository.findAllByOrderByIdDesc());
        return list;
    }

    private List<OpinionResponse> convertToResponseList(List<Opinion> opinions) {
        return opinions.stream()
                .map(v -> OpinionResponse
                        .builder()
                        .id(v.getId())
                        .content(v.getContent())
                        .userName(v.getMember().getUserName())
                        .build())
                .collect(Collectors.toList());
    }

    public List<OpinionResponse> getSearchedOpinions(String content) {
        return convertToResponseList(opinionRepository.findOpinionByContent(content));
    }

    public OpinionResponse getOpinion(Long id) {
        return convertToResponse(opinionRepository.findOpinionById(id));
    }

    private OpinionResponse convertToResponse(Opinion opinion) {
        return OpinionResponse.builder()
                .id(opinion.getId())
                .userName(opinion.getMember().getUserName())
                .content(opinion.getContent())
                .time(opinion.getCreatedAt().toString())
                .build();
    }

    public OpinionResponse createOpinion(String content, UserDetails userDetails) {
        Member member = memberService.getMember(userDetails);
        Opinion opinion = opinionRepository.save(
                Opinion.builder()
                        .content(content)
                        .member(member)
                        .build()
        );
        return convertToResponse(opinion);
    }


}
