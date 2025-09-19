package fashionmanager.kim.develop.repository;

import fashionmanager.kim.develop.entity.ReviewCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReviewCategoryRepository extends JpaRepository<ReviewCategory,Integer> {

    @Query(value = "SELECT COALESCE(MAX(r.reviewCategoryNum), 0) FROM ReviewCategory r")
    int findMaxNum();
}
