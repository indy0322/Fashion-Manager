package fashionmanager.lee.develop.repository;

import com.fashion.community.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<엔티티 타입, PK 타입>
public interface CommentRepository extends JpaRepository<Comment, Long> {
}