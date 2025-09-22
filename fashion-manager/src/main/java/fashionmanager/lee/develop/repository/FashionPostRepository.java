package fashionmanager.lee.develop.repository;


import fashionmanager.lee.develop.entity.FashionPost;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FashionPostRepository extends JpaRepository<FashionPost, Integer> {
}