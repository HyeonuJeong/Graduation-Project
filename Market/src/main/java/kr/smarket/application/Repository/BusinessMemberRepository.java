package kr.smarket.application.Repository;

import kr.smarket.application.Domain.BusinessMember;
import kr.smarket.application.Domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessMemberRepository extends JpaRepository<BusinessMember, Long> {
    BusinessMember findByUserId(String userId);
}
