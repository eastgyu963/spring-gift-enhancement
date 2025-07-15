package gift.repository.wish;

import gift.entity.Wish;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishJpaRepository extends JpaRepository<Wish, Long> {

  List<Wish> findByMemberId(Long memberId);

  Optional<Wish> findByMemberIdAndProductId(Long memberId, Long productId);

  void deleteByMemberId(Long memberId);
  
  void deleteByMemberIdAndProductId(Long memberId, Long productId);
}
