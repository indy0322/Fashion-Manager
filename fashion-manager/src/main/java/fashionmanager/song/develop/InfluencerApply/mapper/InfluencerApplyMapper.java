package fashionmanager.song.develop.InfluencerApply.mapper;

import fashionmanager.song.develop.InfluencerApply.dto.InfluencerApplyResponseDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InfluencerApplyMapper {

    List<InfluencerApplyResponseDTO> selectResultApply();
}