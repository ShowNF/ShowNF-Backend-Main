package com.shownf.reptile.controller;

import com.shownf.reptile.Model.DTO.RequestPostDTO;
import com.shownf.reptile.Model.DTO.RequestPostDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestPostSaveDTO;
import com.shownf.reptile.Model.DTO.ResponsePostsDTO;
import com.shownf.reptile.service.PostService;
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
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class PostController {

    PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    // 게시물 조회
    @ApiOperation(value = "게시물 조회", notes = "게시물 아이디로 게시물 조회")
    @GetMapping("post/{postId}")
    public RequestPostDTO getPost(@PathVariable Long postId){
        return postService.getPostDAO(postId);
    }


    // 마이페이지 게시물 전체 조회
    @ApiOperation(value = "마이페이지 게시물 전체 조회", notes = "유저 아이디로 게시물 전체 조회")
    @GetMapping("post/mypage/user/{userId}")
    public Page<RequestPostDTO> getPosts(@PathVariable Long userId, @PageableDefault(size=15, sort="uploadTime", direction = Sort.Direction.DESC) Pageable pageable){
        return postService.getPosts(userId, pageable);
    }


    // 핫 게시물 조회
    @ApiOperation(value = "인기게시물 조회", notes = "좋아요를 많이 받은 순으로 게시물 5개씩 페이징 조회")
    @GetMapping("post/hot")
    public Page<RequestPostDTO> getHotPosts(@PageableDefault(size=15, sort="heartCount", direction = Sort.Direction.DESC) Pageable pageable){
        return postService.getHotPosts(pageable);
    }


    // 카테고리별 게시물 조회
    @ApiOperation(value = "카테고리별 조회", notes = "카테고리를 입력받아 해당하는 게시물 5개씩 페이징 조회")
    @GetMapping("post/category/{category}")
    public Page<RequestPostDTO> getCategoryPosts(@PathVariable String category, @PageableDefault(size=5, sort="uploadTime", direction = Sort.Direction.DESC) Pageable pageable){
        return postService.getCategoryPosts(category, pageable);
    }


    // 유저가 좋아요한 게시물 조회
    @ApiOperation(value = "유저가 좋아요한 게시물 조회", notes = "유저 아이드를 입력받아 해당하는 게시물 15개씩 페이징 조회")
    @GetMapping("post/user/{userId}")
    public Page<ResponsePostsDTO> getUserPostHearts(@PathVariable Long userId, @PageableDefault(size=5, sort="uploadTime", direction = Sort.Direction.DESC) Pageable pageable){
        return postService.getUserPostHearts(userId, pageable);
    }


    // 게시물 저장
    @ApiOperation(value = "게시물 저장", notes = "게시물 작성시 저장")
    @PostMapping("post")
    public ResponseEntity<Map<String, Object>> savePost(@RequestBody RequestPostSaveDTO requestPostSaveDTO){
        Long postId = postService.savePostDAO(requestPostSaveDTO);

        // HTTP 상태 변환
        HttpStatus httpStatus = (postId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (postId != null) ? "Save Success" : "Save Fail");
        requestMap.put("postId", postId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

    // 게시물 삭제
    @ApiOperation(value = "게시물 삭제", notes = "게시물 삭제시 삭제")
    @DeleteMapping("post")
    public ResponseEntity<Map<String, Object>> deletePost(@RequestBody RequestPostDeleteDTO requestPostDeleteDTO, HttpServletRequest request){
        Long postId = postService.deletePostDAO(requestPostDeleteDTO, request);

        // HTTP 상태 변환
        HttpStatus httpStatus = (postId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (postId != null) ? "Delete Success" : "Delete Fail");
        requestMap.put("postId", postId);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }
}
