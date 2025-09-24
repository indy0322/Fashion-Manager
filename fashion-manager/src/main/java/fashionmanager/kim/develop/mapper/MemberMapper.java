package fashionmanager.kim.develop.mapper;

import fashionmanager.kim.develop.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    MemberDTO selectMessageAllow(@Param("selectMemberId")String memberId);

    MemberDTO selectMemberByNum(@Param("selectMemberNum")int memberNum);
}
