package fashionmanager.baek.develop.mapper;

import fashionmanager.baek.develop.dto.Criteria;
import fashionmanager.baek.develop.dto.SelectAllFashionPostDTO;
import fashionmanager.baek.develop.dto.SelectAllReviewPostDTO;
import fashionmanager.baek.develop.dto.SelectDetailReviewPostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewPostMapper {
    List<SelectAllReviewPostDTO> findAll();

    SelectDetailReviewPostDTO findById(int postNum);

    List<SelectAllReviewPostDTO> getListWithPaging(Criteria criteria);

    int getTotalCount();
}
