package com.shownf.reptile.controller;

import com.shownf.reptile.Model.DTO.RequestSaleSaveDTO;
import com.shownf.reptile.Model.DTO.ResponseSaleDTO;
import com.shownf.reptile.service.SaleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class SaleController {

    SaleService saleService;

    @Autowired
    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }


    // 분양글 조회
    @ApiOperation(value = "분양글 조회", notes = "분양글 아이디로 분양글 조회")
    @GetMapping("sale/{saleId}")
    public ResponseSaleDTO getSale(@PathVariable Long saleId){
        return saleService.getSale(saleId);
    }


    // 분양글 Page 로 전체 조회
    @ApiOperation(value = "분양글 전체 조회", notes = "분양글 5개씩 페이징 조회")
    @GetMapping("sale/all")
    public Page<ResponseSaleDTO> getSales(@PageableDefault(size=12)Pageable pageable){
        return saleService.getSales(pageable);
    }


    // 유저가 좋아요한 분양글 조회
    @ApiOperation(value = "내가 좋아요한 분양글 조회", notes = "유저 아이디로 좋아요 누른 분양글 조회")
    @GetMapping("sale/user/{userId}")
    public List<ResponseSaleDTO> getUserSaleHearts(@PathVariable Long userId){
        return saleService.getUserSaleHearts(userId);
    }


    // 분양글 저장
    @ApiOperation(value = "분양글 저장", notes = "분양글 작성시 저장")
    @PostMapping("sale")
    public ResponseEntity<Map<String, Object>> saveSale(@RequestBody RequestSaleSaveDTO requestSaleSaveDTO){
        Long saleId = saleService.saveSale(requestSaleSaveDTO);

        // HTTP 상태 변환
        HttpStatus httpStatus = (saleId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (saleId != null) ? "Save Success" : "Save Fail");
        requestMap.put("saleId", saleId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }
}
