package fashionmanager.baek.develop.repository;

import fashionmanager.baek.develop.entity.FashionPostItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FashionItemRepository extends JpaRepository<FashionPostItemEntity, Integer> {
}
