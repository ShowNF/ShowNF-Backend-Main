package com.shownf.reptile.controller;

import com.shownf.reptile.Model.DTO.RequestSaleHeartDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestSaleHeartSaveDTO;
import com.shownf.reptile.service.SaleHeartService;
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
public class SaleHeartController {

    SaleHeartService saleHeartService;

    @Autowired
    public SaleHeartController(SaleHeartService saleHeartService) {
        this.saleHeartService = saleHeartService;
    }

    // 좋아요 누른 분양글 아이디 전체 조회
    @ApiOperation(value = "분양글 아이디 전체 조회", notes = "좋아요 누른 분양글 아이디 전체 조회")
    @GetMapping("saleHeart/user/{userId}")
    public List<Long> getSaleIds(@PathVariable Long userId){
        return saleHeartService.getSaleIds(userId);
    }


    // 분양글 좋아요 저장
    @ApiOperation(value = "분양글 좋아요 저장", notes = "분양글에 좋아요를 누를시 저장한다.")
    @PostMapping("saleHeart")
    public ResponseEntity<Map<String, Object>> saveSaleHeart(@RequestBody RequestSaleHeartSaveDTO requestSaleHeartSaveDTO){
        Long saleHeartId = saleHeartService.saveSaleHeart(requestSaleHeartSaveDTO);

        // HTTP 상태 변환
        HttpStatus httpStatus = (saleHeartId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (saleHeartId != null) ? "Save Success" : "Save Fail");
        requestMap.put("saleHeartId", saleHeartId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

    // 분양글 좋아요 삭제
    @ApiOperation(value = "분양글 좋아요 삭제", notes = "분양글에 좋아요를 누를시 삭제한다.")
    @DeleteMapping("saleHeart")
    public ResponseEntity<Map<String, Object>> deleteSaleHeart(@RequestBody RequestSaleHeartDeleteDTO requestSaleHeartDeleteDTO){
        Long saleHeartId = saleHeartService.deleteSaleHeart(requestSaleHeartDeleteDTO);

        // HTTP 상태 반환
        HttpStatus httpStatus = (saleHeartId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (saleHeartId != null) ? "Delete Success" : "Delete Fail");
        requestMap.put("saleHeartId", saleHeartId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }
}
