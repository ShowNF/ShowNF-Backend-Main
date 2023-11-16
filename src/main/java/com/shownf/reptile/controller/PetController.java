package com.shownf.reptile.controller;

import com.shownf.reptile.Model.DTO.ResponsePetDTO;
import com.shownf.reptile.Model.DTO.RequestPetSaveDTO;
import com.shownf.reptile.service.PetService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class PetController {

    PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }


    // 마이펫 조회
    @ApiOperation(value = "마이펫 조회", notes = "마이펫 아이디로 마이펫찾기")
    @GetMapping("pet/{petId}")
    public ResponsePetDTO getPet(@PathVariable Long petId){
        return petService.getPet(petId);
    }


    // 마이펫 전체 조회
    @ApiOperation(value = "마이펫 전체 조회", notes = "유저 아이디로 찾은 마이펫 12개씩 페이징 조회")
    @GetMapping("pet/user/{userId}")
    public Page<ResponsePetDTO> getHotPosts(@PathVariable Long userId, @PageableDefault(size=12, sort="uploadTime", direction = Sort.Direction.ASC) Pageable pageable){
        return petService.getPets(userId, pageable);
    }


    // 마이펫 저장
    @ApiOperation(value = "마이펫 저장", notes = "마이펫 작성시 저장")
    @PostMapping("pet")
    public ResponseEntity<Map<String, Object>> savePet(@RequestBody RequestPetSaveDTO requestPetSaveDTO){
        Long petId = petService.savePet(requestPetSaveDTO);

        // HTTP 상태 변환
        HttpStatus httpStatus = (petId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (petId != null) ? "Save Success" : "Save Fail");
        requestMap.put("petId", petId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }
}
