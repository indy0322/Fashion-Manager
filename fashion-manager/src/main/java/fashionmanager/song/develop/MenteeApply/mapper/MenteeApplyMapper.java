package fashionmanager.song.develop.MenteeApply.mapper;

import fashionmanager.song.develop.MenteeApply.dto.MenteeApplyResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MenteeApplyMapper {

    List<MenteeApplyResponseDTO> selectResultApply();
}
