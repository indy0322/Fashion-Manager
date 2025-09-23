package fashionmanager.kim.develop.repository;

import fashionmanager.kim.develop.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Integer> {
}
