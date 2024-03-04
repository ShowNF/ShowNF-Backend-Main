package com.shownf.reptile.controller.qna;

import com.shownf.reptile.Model.DTO.qna.*;
import com.shownf.reptile.service.qna.QnACommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class QnACommentController {

    QnACommentService qnaCommentService;

    @Autowired
    public QnACommentController(QnACommentService qnaCommentService) {
        this.qnaCommentService = qnaCommentService;
    }

    // QnA Comment 전체 조회
    @ApiOperation(value = "QnA 댓글 전체 조회", notes = "QnA게시판 아이디에 해당하는 댓글을 찾는다.")
    @GetMapping("qna/comment/{qnaPostId}")
    public List<ResponseQnACommentGetDTO> getQnAComments(@PathVariable Long qnaPostId){
        return qnaCommentService.getQnAComments(qnaPostId);
    }

    // QnA Comment 저장
    @ApiOperation(value = "QnA 댓글 저장", notes = "QnA댓글 작성시 저장한다.")
    @PostMapping("qna/comment")
    public ResponseEntity<Map<String, Object>> saveQnAComment(@RequestBody RequestQnACommentSaveDTO requestCommentSaveDTO){
        Long qnaCommentId = qnaCommentService.saveQnAComment(requestCommentSaveDTO);

        // HTTP 상태 변환
        HttpStatus httpStatus = (qnaCommentId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (qnaCommentId != null) ? "Save Success" : "Save Fail");
        requestMap.put("qnaCommentId", qnaCommentId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

    // QnA Comment 채택
    @ApiOperation(value = "QnA 댓글 채택", notes = "QnA댓글 채택시 저장한다.")
    @PostMapping("qna/comment/selection")
    public ResponseEntity<Map<String, Object>> saveQnACommentSelection(@RequestBody RequestQnACommentSelectionSaveDTO requestQnACommentSelectionSaveDTO, HttpServletRequest request){
        ResponseQnACommentGetDTO responseQnACommentGetDTO = qnaCommentService.saveQnACommentSelection(requestQnACommentSelectionSaveDTO, request);

        // HTTP 상태 변환
        HttpStatus httpStatus = (responseQnACommentGetDTO != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (responseQnACommentGetDTO != null) ? "Selection Success" : "Selection Fail");
        requestMap.put("qnaCommentId", responseQnACommentGetDTO);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }


    // QnA Comment 수정
    @ApiOperation(value = "QnA 댓글 수정", notes = "QnA댓글 수정시 저장한다.")
    @PutMapping("qna/comment")
    public ResponseEntity<Map<String, Object>> updateQnAComment(@RequestBody RequestQnACommentUpdateDTO requestQnACommentUpdateDTO, HttpServletRequest request){
        Long qnaCommentId = qnaCommentService.updateQnAComment(requestQnACommentUpdateDTO, request);

        // HTTP 상태 변환
        HttpStatus httpStatus = (qnaCommentId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (qnaCommentId != null) ? "Update Success" : "Update Fail");
        requestMap.put("qnaCommentId", qnaCommentId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

    // QnA Comment 삭제
    @ApiOperation(value = "QnA 댓글 삭제", notes = "QnA댓글 삭제시 저장한다.")
    @DeleteMapping("qna/comment")
    public ResponseEntity<Map<String, Object>> deleteQnAComment(@RequestBody RequestQnACommentDeleteDTO requestQnACommentDeleteDTO, HttpServletRequest request){
        Long qnaCommentId = qnaCommentService.deleteQnAComment(requestQnACommentDeleteDTO, request);

        // HTTP 상태 변환
        HttpStatus httpStatus = (qnaCommentId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (qnaCommentId != null) ? "Delete Success" : "Delete Fail");
        requestMap.put("qnaCommentId", qnaCommentId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

    // QnA Comment 채택 삭제
    @ApiOperation(value = "QnA 댓글 채택 삭제", notes = "QnA댓글 채택 삭제시 저장한다.")
    @DeleteMapping("qna/comment/selection")
    public ResponseEntity<Map<String, Object>> deleteQnACommentSelection(@RequestBody RequestQnACommentSelectionDeleteDTO requestQnACommentSelectionDeleteDTO, HttpServletRequest request){
        ResponseQnACommentGetDTO responseQnACommentGetDTO = qnaCommentService.deleteQnACommentSelection(requestQnACommentSelectionDeleteDTO, request);

        // HTTP 상태 변환
        HttpStatus httpStatus = (responseQnACommentGetDTO != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (responseQnACommentGetDTO != null) ? "Selection Delete Success" : "Selection Delete Fail");
        requestMap.put("qnaCommentId", responseQnACommentGetDTO);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }
}