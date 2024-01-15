package com.shownf.reptile.controller;

import com.shownf.reptile.Model.DTO.ResponsePostMetaDTO;
import com.shownf.reptile.service.PostMetaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class PostMetaController {

    PostMetaService postMetaService;

    @Autowired
    public PostMetaController(PostMetaService postMetaService) {
        this.postMetaService = postMetaService;
    }

    // postId로 meta data 조회
    @ApiOperation(value = "post meta data 조회", notes = "postId로 meta data 조회")
    @GetMapping("postMeta/{postId}")
    public ResponsePostMetaDTO getPostMeta(@PathVariable Long postId){
        return postMetaService.getPostMeta(postId);
    }

}
