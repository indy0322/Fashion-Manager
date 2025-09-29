package fashionmanager.song.develop.InfluencerApply.mapper;

import fashionmanager.song.develop.InfluencerApply.dto.InfluencerApplyCreateRequestDTO;
import fashionmanager.song.develop.InfluencerApply.dto.InfluencerApplyResponseDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface InfluencerApplyMapper {

    List<InfluencerApplyResponseDTO> selectResultApply();

    int insertInfluencerApply(InfluencerApplyCreateRequestDTO req);


    // 수정, 삭제 하려는 Param들 확인
    int updateInfluencerApply(@Param("num") int num,
                              @Param("title") String title,
                              @Param("content") String content,
                              @Param("memberNum") int memberNum);

    int deleteInfluencerApplyByTitleAndMember(@Param("title") String title,
                                              @Param("memberNum") int memberNum);
}
