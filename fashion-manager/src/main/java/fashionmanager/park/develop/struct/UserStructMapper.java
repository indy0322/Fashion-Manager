package fashionmanager.park.develop.struct;

import fashionmanager.park.develop.menu.DTO.BadgeDTO;
import fashionmanager.park.develop.menu.DTO.UserDTO;
import fashionmanager.park.develop.menu.Entity.Badge;
import fashionmanager.park.develop.menu.Entity.User;
import org.mapstruct.Mapper;


// 단순 Entity ↔ DTO 변환용(Mybatis랑 매핑 X)
@Mapper(componentModel = "spring")
public interface UserStructMapper {

    UserDTO toDto(User user);
    BadgeDTO toDto(Badge badge);

}