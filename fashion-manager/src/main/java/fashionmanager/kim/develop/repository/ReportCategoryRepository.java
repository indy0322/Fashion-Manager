package fashionmanager.kim.develop.repository;

import fashionmanager.kim.develop.entity.ReportCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReportCategoryRepository extends JpaRepository<ReportCategory,Integer> {

    @Query(value = "SELECT COALESCE(MAX(r.reportCategoryNum), 0) FROM ReportCategory r")
    int findMaxNum();
}
