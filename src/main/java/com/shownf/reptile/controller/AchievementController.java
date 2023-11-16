package com.shownf.reptile.controller;

import com.shownf.reptile.Model.DTO.RequestPostDTO;
import com.shownf.reptile.Model.DTO.ResponseAchievementDTO;
import com.shownf.reptile.service.AchievementService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class AchievementController {

    AchievementService achievementService;

    @Autowired
    public AchievementController(AchievementService achievementService) {
        this.achievementService = achievementService;
    }

    // 게시물 조회
    @ApiOperation(value = "시작 안 한 업적 조회", notes = "유저 아이디로 시작 안 한 업적 조회")
    @GetMapping("achievement/user/{userId}")
    public List<ResponseAchievementDTO> getAchievements(@PathVariable Long userId){
        return achievementService.getAchievements(userId);
    }

}
