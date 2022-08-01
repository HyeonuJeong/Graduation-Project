package kr.smarket.application.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import kr.smarket.application.Domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
	List<Cart> findAllByMemberId(long member_id);
	Cart findByMemberId(long member_id);
	void deleteById(long memberId);
	
	@Transactional
    @Modifying
	@Query("DELETE FROM Cart c " +
            "WHERE c.memberId = :member_id ")
	void deleteAllByIdInQuery(Long member_id);
	
}
