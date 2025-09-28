package park.ohgiraffers.fashionmanager.menu.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import park.ohgiraffers.fashionmanager.common.Pagination;
import park.ohgiraffers.fashionmanager.common.PagingButtonInfo;
import park.ohgiraffers.fashionmanager.menu.DTO.UserDTO;
import park.ohgiraffers.fashionmanager.menu.Entity.User;
import park.ohgiraffers.fashionmanager.menu.Service.UserService;

@Controller
@RequestMapping("/menu")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    // 1. 회원 조회 페이지

    @GetMapping("/select")
    public void selectMenu() {

    }


    // 2. 회원 조회 페이지 결과

    @GetMapping("/selectResult")
    public String findUserById(@RequestParam int userNum, Model model) {
        UserDTO user = userService.findUserById(userNum);  // 서비스에서 조회
        model.addAttribute("user", user);
        return "menu/selectResult";
    }


    // 3. 전체 회원 조회

    @GetMapping("/list")
    public String findUserList(@PageableDefault(size=15) Pageable pageable, Model model) {
        log.debug("pageable: {}", pageable);

        Page<UserDTO> userList = userService.findUserList(pageable);

        /* 설명. Page객체를 통해 PagingButtonInfo(front가 페이징 처리 버튼을 그리기 위한 재료를 지닌) 추출 */
        PagingButtonInfo paging = Pagination.getPagingButtonInfo(userList);

        model.addAttribute("userList", userList);
        model.addAttribute("paging", paging);

        return "menu/list";
    }


    // 4. 회원가입 기능

    @GetMapping("/regist")
    public void registMenu() {}


    @PostMapping("/regist")
    public String registMenu(UserDTO newUser) {
        User savedUser = userService.registUser(newUser);

        return "redirect:/menu/selectResult?userNum=" + savedUser.getUserNum();
    }


    // 5. 회원정보 수정 <인적사항 수정>

    @GetMapping("/modify")
    public void modifyMenuPage() {}

    @PostMapping("/modify")
    public String modifyMenu(UserDTO modifyMenu) {
        userService.modifyMenu(modifyMenu);

        return "redirect:/menu/selectResult?userNum=" + modifyMenu.getUserNum();


    }



    // 6. 회원탈퇴 기능

    @GetMapping("/delete")
    public void deleteMenuPage() {}

    @PostMapping("/delete")
    public String deleteUserMenu(@RequestParam int userNum) {
        userService.userDelete(userNum);

        return "redirect:/menu/list";
    }





}
