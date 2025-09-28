package fashionmanager.park.develop.mapper;


import org.apache.ibatis.annotations.Mapper;
import fashionmanager.park.develop.menu.DTO.UserDTO;

@Mapper
public interface UserMapper {
    UserDTO selectUserByNum(int userNum);

    UserDTO selectUserWithBadges(int userNum);
}