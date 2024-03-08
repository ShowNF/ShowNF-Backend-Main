package com.shownf.reptile.controller.qna;

import com.shownf.reptile.Model.DTO.qna.RequestQnAPostDeleteDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnAPostSaveDTO;
import com.shownf.reptile.Model.DTO.qna.RequestQnAPostUpdateDTO;
import com.shownf.reptile.Model.DTO.qna.ResponseQnAPostGetDTO;
import com.shownf.reptile.service.qna.QnAPostService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class QnAPostController {

    QnAPostService qnAPostService;

    @Autowired
    public QnAPostController(QnAPostService qnAPostService) {
        this.qnAPostService = qnAPostService;
    }

    // QnA 게시물 조회
    @ApiOperation(value = "QnA 게시물 조회", notes = "QnA 게시물 아이디로 게시물 조회")
    @GetMapping("qna/post/{qnaPostId}")
    public ResponseQnAPostGetDTO getQnAPost(@PathVariable Long qnaPostId, @RequestParam(required = false) Long userId){
        return qnAPostService.getQnAPostDAO(qnaPostId, userId);
    }

    // 핫 QnA 게시물 전체 조회
    @ApiOperation(value = "핫 QnA 게시물 전체 조회", notes = "핫 QnA 게시물 전체 조회")
    @GetMapping("qna/post/hot")
    public Page<Long> getHotQnAPosts(@PageableDefault(size=15, sort="heartCount", direction = Sort.Direction.DESC) Pageable pageable){
        return qnAPostService.getQnAPostsDAO(pageable);
    }

    // 마이페이지 QnA 게시물 전체 조회
    @ApiOperation(value = "마이페이지 QnA 게시물 전체 조회", notes = "유저 아이디로 QnA 게시물 전체 조회")
    @GetMapping("qna/post/mypage/user/{userId}")
    public Page<Long> getQnAPosts(@PathVariable Long userId, @PageableDefault(size=15, sort="uploadTime", direction = Sort.Direction.DESC) Pageable pageable){
        return qnAPostService.getQnAPostsDAO(userId, pageable);
    }

    // 유저가 좋아요한 게시물 조회
    @ApiOperation(value = "유저가 좋아요한 게시물 조회", notes = "유저 아이드를 입력받아 해당하는 게시물 15개씩 페이징 조회")
    @GetMapping("qna/post/user/{userId}")
    public Page<Long> getUserQnAPostHearts(@PathVariable Long userId, @PageableDefault(size=5, sort="uploadTime", direction = Sort.Direction.DESC) Pageable pageable){
        return qnAPostService.getUserQnAPostHearts(userId, pageable);
    }

    // QnA 게시물 저장
    @ApiOperation(value = "QnA 게시물 저장", notes = "QnA 게시물 작성시 저장")
    @PostMapping("qna/post")
    public ResponseEntity<Map<String, Object>> saveQnAPost(@RequestBody RequestQnAPostSaveDTO requestQnAPostSaveDTO){
        Long qnaPostId = qnAPostService.saveQnAPostDAO(requestQnAPostSaveDTO);

        // HTTP 상태 변환
        HttpStatus httpStatus = (qnaPostId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (qnaPostId != null) ? "Save Success" : "Save Fail");
        requestMap.put("qnaPostId", qnaPostId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

    // QnA 게시물 수정
    @ApiOperation(value = "QnA 게시물 수정", notes = "QnA 게시물 수정")
    @PutMapping("qna/post")
    public ResponseEntity<Map<String, Object>> updateQnAPost(@RequestBody RequestQnAPostUpdateDTO requestQnAPostSaveDTO, HttpServletRequest request){
        Long qnaPostId = qnAPostService.updateQnAPostDAO(requestQnAPostSaveDTO, request);

        // HTTP 상태 변환
        HttpStatus httpStatus = (qnaPostId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (qnaPostId != null) ? "Update Success" : "Update Fail");
        requestMap.put("qnaPostId", qnaPostId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

    // QnA 게시물 삭제
    @ApiOperation(value = "QnA 게시물 삭제", notes = "QnA 게시물 삭제")
    @DeleteMapping("qna/post")
    public ResponseEntity<Map<String, Object>> deleteQnAPost(@RequestBody RequestQnAPostDeleteDTO requestQnAPostDeleteDTO, HttpServletRequest request){
        Long qnaPostId = qnAPostService.deleteQnAPostDAO(requestQnAPostDeleteDTO, request);

        // HTTP 상태 변환
        HttpStatus httpStatus = (qnaPostId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (qnaPostId != null) ? "Delete Success" : "Delete Fail");
        requestMap.put("qnaPostId", qnaPostId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }
}
