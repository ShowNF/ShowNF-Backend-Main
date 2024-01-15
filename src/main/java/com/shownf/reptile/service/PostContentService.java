package com.shownf.reptile.service;

import com.shownf.reptile.Model.DTO.RequestPostContentUpdateDTO;
import com.shownf.reptile.bean.UpdatePostContentBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostContentService {

    UpdatePostContentBean updatePostContentBean;

    @Autowired
    public PostContentService(UpdatePostContentBean updatePostContentBean) {
        this.updatePostContentBean = updatePostContentBean;
    }

    // Update the postContent
    public Long updatePostContent(RequestPostContentUpdateDTO requestPostContentUpdateDTO){
        return updatePostContentBean.exec(requestPostContentUpdateDTO);
    }
}
