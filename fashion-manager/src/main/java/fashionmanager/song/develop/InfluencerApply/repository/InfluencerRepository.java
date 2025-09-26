package fashionmanager.song.develop.InfluencerApply.repository;

import fashionmanager.song.develop.InfluencerApply.aggregate.InfluencerApplyEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface InfluencerRepository extends JpaRepository<InfluencerApplyEntity, Integer> {

    Optional<InfluencerApplyEntity> findByNumAndMemberNum(Integer num, Integer memberNum);


    @Modifying
    @Transactional
    @Query(value = """
    UPDATE Influencer_Apply
       SET 
           title   = :title,
           content = :content,
           accept  = :accept
     WHERE num = :num
       AND member_num = :memberNum
""", nativeQuery = true)
    int updateInfluencerApply(@Param("num") Integer num,
                        @Param("memberNum") Integer memberNum,
                        @Param("title") String title,
                        @Param("content") String content,
                        @Param("accept") String accept);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Influencer_Apply WHERE title = :title AND member_num = :memberNum", nativeQuery = true)
    int deleteInfluencerApplyByTitleAndMemberNum(@Param("title") String title, @Param("memberNum") Integer memberNum);
}