package fashionmanager.kim.develop.controller;

import fashionmanager.kim.develop.dto.BlacklistDTO;
import fashionmanager.kim.develop.service.BlacklistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/blacklist")
public class BlacklistController {

    private final BlacklistService bs;

    public BlacklistController(BlacklistService blacklistService) {
        this.bs = blacklistService;
    }

    @GetMapping("/selectblacklist")
    public ResponseEntity<String> selectBlacklist(){
        List<BlacklistDTO> blacklist = bs.selectBlacklist();
        for(BlacklistDTO black : blacklist){
            log.info("BlacklistDTO: {}", black);
        }
        return null;
    }
}
