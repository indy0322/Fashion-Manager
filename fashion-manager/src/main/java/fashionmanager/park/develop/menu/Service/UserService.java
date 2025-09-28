package fashionmanager.park.develop.menu.Service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import fashionmanager.park.develop.mapper.UserMapper;
import fashionmanager.park.develop.menu.DTO.UserDTO;
import fashionmanager.park.develop.menu.Entity.User;
import fashionmanager.park.develop.menu.repository.UserRepository;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userMapper = userMapper;
    }


    /* 설명. ModelMapper 안쓰고 수동으로 매핑하는 법 */
    UserDTO userToUserDTO(User user) {


        return modelMapper.map(user, UserDTO.class);
    }


//    // mybatis 회원 조회
//
//    public UserDTO findUserByNum(int userNum) {
//        return userMapper.selectUserByNum(userNum);
//    }


    // 1. 회원가입 기능

    @Transactional
    public User registUser(UserDTO newUser) {
        User user = modelMapper.map(newUser, User.class);
        System.out.println(user);
        User savedUser= userRepository.save(user);

        return savedUser;
    }




    // 2. 회원 전체를 조회하는 기능

    public Page<UserDTO> findUserList(Pageable pageable) {

        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("userNum").descending());
        Page<User> userList = userRepository.findAll(pageable);

        /* 설명. Page는 자체로 stream이다. */
        return userList.map(menu -> modelMapper.map(menu, UserDTO.class));

    }

    // 3. 회원 번호를 불러와 특정 회원을 조회하는 기능

    public UserDTO findUserById(int userNum) {

        User user = userRepository.findById(userNum)
                .orElseThrow(IllegalArgumentException::new);
        return userToUserDTO(user);

    }



    // 4. 회원 정보를 수정하는 기능

    @Transactional
    public void modifyMenu(UserDTO modifyMenu) {

        User foundUser = userRepository.findById(modifyMenu.getUserNum()).get();


        // 아이디 중복 검사 (본인 제외)
        if (!foundUser.getUserId().equals(modifyMenu.getUserId()) &&
                userRepository.existsByUserId(modifyMenu.getUserId())) {
            throw new RuntimeException("이미 사용 중인 아이디입니다.");
        }

        // 이메일 중복 검사 (본인 제외)
        if (!foundUser.getUserEmail().equals(modifyMenu.getUserEmail()) &&
                userRepository.existsByUserEmail(modifyMenu.getUserEmail())) {
            throw new RuntimeException("이미 사용 중인 이메일입니다.");
        }


        foundUser.setUserId(modifyMenu.getUserId());
        foundUser.setUserPwd(modifyMenu.getUserPwd());

        foundUser.setUserEmail(modifyMenu.getUserEmail());



        foundUser.setUserName(modifyMenu.getUserName());

        foundUser.setUserAge(modifyMenu.getUserAge());

        foundUser.setUserGender(modifyMenu.getUserGender());



    }





    // 5. 메시지 수신여부를 수정하는 기능

    @Transactional
    public void modifyMessage(UserDTO modifyMessage) {

        User foundUser = userRepository.findById(modifyMessage.getUserNum()).get();
        foundUser.setUserMessageAllow(modifyMessage.getUserMessageAllow());

    }


    @Transactional
    public void modifyReport(UserDTO modifyReport) {

        User foundUser = userRepository.findById(modifyReport.getUserNum()).get();
        foundUser.setReportCount(modifyReport.getReportCount());
        foundUser.setDailyReportCount(modifyReport.getDailyReportCount());
    }



    // 7. 회원 정보를 삭제하는 기능

    @Transactional
    public void userDelete(int userNum) {
        userRepository.deleteById(userNum);
    }

}