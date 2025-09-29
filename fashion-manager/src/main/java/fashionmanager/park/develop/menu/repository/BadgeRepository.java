package fashionmanager.park.develop.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fashionmanager.park.develop.menu.Entity.Badge;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Integer> {}