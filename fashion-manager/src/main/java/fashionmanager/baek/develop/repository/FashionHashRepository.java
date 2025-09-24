package fashionmanager.baek.develop.repository;

import fashionmanager.baek.develop.entity.FashionHashTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FashionHashRepository extends JpaRepository<FashionHashTagEntity, Integer> {
}
