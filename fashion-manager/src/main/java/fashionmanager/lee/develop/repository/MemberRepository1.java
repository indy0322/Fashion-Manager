package fashionmanager.lee.develop.repository;

import fashionmanager.lee.develop.entity.Member1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository1 extends JpaRepository<Member1, Integer> {

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Member m SET m.memberMonthlyGoodCount = 0")
    void resetAllMonthlyGoodCounts();
}