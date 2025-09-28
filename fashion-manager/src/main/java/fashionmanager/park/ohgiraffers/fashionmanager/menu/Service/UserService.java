package park.ohgiraffers.fashionmanager.menu.Service;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import park.ohgiraffers.fashionmanager.menu.DTO.UserDTO;
import park.ohgiraffers.fashionmanager.menu.Entity.User;
import park.ohgiraffers.fashionmanager.menu.repository.UserRepository;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    /* 설명. ModelMapper 안쓰고 수동으로 매핑하는 법 */
    UserDTO userToUserDTO(User user) {


        return modelMapper.map(user, UserDTO.class);
    }


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
        foundUser.setUserId(modifyMenu.getUserId());
    }



    // 5. 회원 정보를 삭제하는 기능

    @Transactional
    public void userDelete(int userNum) {
        userRepository.deleteById(userNum);
    }

}