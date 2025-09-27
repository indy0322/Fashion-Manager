package fashionmanager.baek.develop.mapper;

import fashionmanager.baek.develop.dto.SelectAllPostDTO;
import fashionmanager.baek.develop.dto.SelectDetailPostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MentoringPostMapper {
    List<SelectAllPostDTO> findAll();

    SelectDetailPostDTO findById(int postNum);
}
