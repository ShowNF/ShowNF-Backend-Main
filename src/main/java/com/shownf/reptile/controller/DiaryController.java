package com.shownf.reptile.controller;

import com.shownf.reptile.Model.DTO.RequestDiaryDTO;
import com.shownf.reptile.Model.DTO.RequestDiaryDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestDiarySaveDTO;
import com.shownf.reptile.Model.DTO.ResponseDiarysDTO;
import com.shownf.reptile.service.DiaryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class DiaryController {

    DiaryService diaryService;

    @Autowired
    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }


    // 다이어리 조회
    @ApiOperation(value = "다이어리 조회", notes = "다이어리 아이디로 다이어리 한 개를 조회한다.")
    @GetMapping("diary/{diaryId}")
    public RequestDiaryDTO getDiary(@PathVariable Long diaryId){
        return diaryService.getDiary(diaryId);
    }


    // 다이어리 월별로 조회
    @ApiOperation(value = "다이어리 월별로 조회", notes = "년도와 월을 입력하면 월에 존재하는 다이어리를 조회한다.")
    @GetMapping("diary/pet/{petId}/date/{year}/{month}")
    public List<ResponseDiarysDTO> getDiarys(@PathVariable Long petId, @PathVariable String year, @PathVariable String month){
        return diaryService.getDiarys(petId, year, month);
    }


    // 다이어리 저장
    @ApiOperation(value = "다이어리 저장", notes = "다이어리 작성시 저장")
    @PostMapping("diary")
    public ResponseEntity<Map<String, Object>> saveDiary(@RequestBody RequestDiarySaveDTO requestDiarySaveDTO){
        Long diaryId = diaryService.saveDiary(requestDiarySaveDTO);

        // HTTP 상태 변환
        HttpStatus httpStatus = (diaryId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (diaryId != null) ? "Save Success" : "Save Fail");
        requestMap.put("diaryId", diaryId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

    // 다이어리 삭제
    @ApiOperation(value = "다이어리 삭제", notes = "다이어리 삭제시 삭제")
    @DeleteMapping("diary")
    public ResponseEntity<Map<String, Object>> saveDiary(@RequestBody RequestDiaryDeleteDTO requestDiaryDeleteDTO){
        Long diaryId = diaryService.deleteDiary(requestDiaryDeleteDTO);

        // HTTP 상태 변환
        HttpStatus httpStatus = (diaryId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (diaryId != null) ? "Delete Success" : "Delete Fail");
        requestMap.put("diaryId", diaryId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }
}
