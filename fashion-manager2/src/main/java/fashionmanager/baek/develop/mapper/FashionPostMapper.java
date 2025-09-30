package fashionmanager.baek.develop.mapper;

import fashionmanager.baek.develop.dto.Criteria;
import fashionmanager.baek.develop.dto.SelectAllFashionPostDTO;
import fashionmanager.baek.develop.dto.SelectAllMentoringPostDTO;
import fashionmanager.baek.develop.dto.SelectDetailFashionPostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FashionPostMapper {


    List<SelectAllFashionPostDTO> findAll();

    SelectDetailFashionPostDTO findById(int postNum);

    List<SelectAllFashionPostDTO> getListWithPaging(Criteria criteria);

    int getTotalCount();

}
