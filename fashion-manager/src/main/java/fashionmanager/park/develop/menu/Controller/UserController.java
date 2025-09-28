package fashionmanager.park.develop.menu.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import fashionmanager.park.develop.common.Pagination;
import fashionmanager.park.develop.common.PagingButtonInfo;
import fashionmanager.park.develop.menu.DTO.UserDTO;
import fashionmanager.park.develop.menu.Entity.User;
import fashionmanager.park.develop.menu.Service.SelectService;
import fashionmanager.park.develop.menu.Service.UserService;

@Controller
@RequestMapping("/menu")
@Slf4j
public class UserController {

    private UserService userService;
    private final SelectService selectService;

    public UserController(UserService userService, SelectService selectService)
    {
        this.userService = userService;
        this.selectService = selectService;
    }




    @GetMapping("/selectResult")
    public String selectResult(@RequestParam int userNum, Model model) {
        UserDTO user = selectService.findUserByNum(userNum);
        model.addAttribute("user", user);   // 👈 여기서 모델에 담음
        return "menu/selectResult";         // templates/menu/selectResult.html
    }


    // 1. 회원 조회 페이지

    @GetMapping("/select")
    public void selectMenu() {

    }



     // 2. 회원 조회 페이지 결과

//    @GetMapping("/selectResult")
//    public String findUserById(@RequestParam int userNum, Model model) {
//        UserDTO user = userService.findUserById(userNum);  // 서비스에서 조회
//        model.addAttribute("user", user);
//        return "menu/selectResult";
//    }


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

    // 6. 메시지 수신 여부 수정

    @GetMapping("/MessageResult")
    public String showMessageResult(@RequestParam int userNum, Model model) {
        UserDTO user = userService.findUserById(userNum);
        model.addAttribute("user", user);
        return "menu/MessageResult";
    }

    @GetMapping("/modifyMessage")
    public void showMenuPage() {

    }

    @PostMapping("/modifyMessage")
    public String modifyMessage(UserDTO modifyMessage) {
        userService.modifyMessage(modifyMessage);

        return "redirect:/menu/MessageResult?userNum=" + modifyMessage.getUserNum();
    }


    // 7. 신고 누적 or 하루 신고 가능 횟수 수정(관리자 권한)

    @GetMapping("/ReportResult")
    public String showReportResult(@RequestParam int userNum, Model model) {
        UserDTO user = userService.findUserById(userNum);
        model.addAttribute("user", user);
        return "menu/ReportResult";
    }

    @GetMapping("/modifyReport")
    public void showReportPage() {

    }

    @PostMapping("/modifyReport")
    public String modifyReport(UserDTO modifyReport) {
        userService.modifyReport(modifyReport);

        return "redirect:/menu/ReportResult?userNum=" + modifyReport.getUserNum();
    }



    // 8. 회원탈퇴 기능

    @GetMapping("/delete")
    public void deleteMenuPage() {}

    @PostMapping("/delete")
    public String deleteUserMenu(@RequestParam int userNum) {
        userService.userDelete(userNum);

        return "redirect:/menu/list";
    }





}
