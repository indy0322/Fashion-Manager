package fashionmanager.baek.develop.repository;

import fashionmanager.baek.develop.entity.FashionPostEntity;
import fashionmanager.baek.develop.entity.PostReactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostReactionRepository extends JpaRepository<PostReactionEntity, Integer> {


    Optional<PostReactionEntity> findByMemberNumAndPostNumAndPostCategoryNum(int memberNum, int postNum, int categoryNum);
}
