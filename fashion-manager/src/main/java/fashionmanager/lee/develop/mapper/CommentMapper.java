package fashionmanager.lee.develop.mapper;

import fashionmanager.lee.develop.dto.CommentDTO;
import fashionmanager.lee.develop.dto.CommentPostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CommentMapper {

    List<CommentDTO> findCommentsByPost(@Param("postType") String postType, @Param("postNum") Integer postNum);

    Optional<CommentDTO> findCommentByNum(Integer num);

    void insertComment(CommentPostDTO commentPostDto);

    void updateComment(@Param("num") Integer num, @Param("content") String content);

    void deleteComment(Integer num);
}
