package fashionmanager.kim.develop.repository;

import fashionmanager.kim.develop.entity.MessageCategory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface MessageCategoryRepository extends CrudRepository<MessageCategory,Integer> {
    @Query(value = "SELECT COALESCE(MAX(r.messageCategoryNum), 0) FROM MessageCategory r")
    int findMaxNum();
}
