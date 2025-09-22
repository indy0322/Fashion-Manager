package fashionmanager.lee.develop.repository;



import fashionmanager.lee.develop.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;


public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Optional<Comment> findByIdAndMember_Id(Integer id, Integer memberId);
}