package fashionmanager.baek.develop.repository;

import fashionmanager.baek.develop.entity.PhotoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<PhotoEntity, Integer> {
}
