package kr.smarket.application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import kr.smarket.application.Domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByUserId(String userId);
}
