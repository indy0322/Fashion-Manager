package fashionmanager.baek.develop.mapper;

import fashionmanager.baek.develop.dto.SelectAllPostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewPostMapper {
    List<SelectAllPostDTO> findAll();
}
