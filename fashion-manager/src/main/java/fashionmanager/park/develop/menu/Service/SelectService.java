package fashionmanager.park.develop.menu.Service;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fashionmanager.park.develop.mapper.UserMapper;
import fashionmanager.park.develop.menu.DTO.UserDTO;


@Service
@Slf4j
public class SelectService {

    private final UserMapper userMapper;

    @Autowired
    public SelectService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    // 회원번호로 회원 조회
    public UserDTO findUserByNum(int userNum) {
        return userMapper.selectUserByNum(userNum);
    }





}
