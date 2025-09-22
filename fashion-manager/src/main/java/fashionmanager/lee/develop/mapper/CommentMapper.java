package fashionmanager.lee.develop.mapper;

import fashionmanager.lee.develop.dto.CommentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {
    /**
     * 특정 패션 게시글에 달린 모든 댓글 목록을 조회합니다.
     */
    List<CommentDTO> findByFashionPostNum(@Param("fashionPostNum") Long fashionPostNum);

    /**
     * 특정 후기 게시글에 달린 모든 댓글 목록을 조회합니다.
     */
    List<CommentDTO> findByReviewPostNum(@Param("reviewPostNum") Long reviewPostNum);
}