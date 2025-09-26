package fashionmanager.song.develop.MenteeApply.repository;

import fashionmanager.song.develop.MenteeApply.aggregate.MenteeApplyEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface MenteeRepository extends JpaRepository<MenteeApplyEntity, Integer> {

    @Modifying
    @Transactional
    @Query(value = """
    UPDATE MENTEE_APPLY
       SET 
           content = :content
     WHERE num = :num
       AND member_num = :memberNum
""", nativeQuery = true)
    int updateMenteeApply(@Param("num") Integer num,
                          @Param("memberNum") Integer memberNum,
                          @Param("content") String content,
                          @Param("MentoringPostNum") String title,
                          @Param("accept") String accept);


    @Modifying
    @Transactional
    @Query(value = """
        DELETE FROM mentee_apply
         WHERE ((:content IS NULL AND content IS NULL) OR content = :content)
           AND mentoring_post_num = :mentoringPostNum
           AND member_num = :memberNum
    """, nativeQuery = true)
    int deleteMenteeApplyByMentoringPostNumAndMemberNum(@Param("content") String content,
                                    @Param("mentoringPostNum") Integer mentoringPostNum,
                                    @Param("memberNum") Integer memberNum);
}

