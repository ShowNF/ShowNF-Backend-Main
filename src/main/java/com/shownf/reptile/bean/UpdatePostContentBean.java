package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestPostContentUpdateDTO;
import com.shownf.reptile.Model.entity.PostContentDAO;
import com.shownf.reptile.bean.small.GetPostContentDAOBean;
import com.shownf.reptile.bean.small.SavePostContentsDAOBean;
import com.shownf.reptile.bean.small.UpdatePostContentDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdatePostContentBean {

    GetPostContentDAOBean getPostContentDAOBean;
    UpdatePostContentDAOBean updatePostContentDAOBean;
    SavePostContentsDAOBean savePostContentsDAOBean;

    @Autowired
    public UpdatePostContentBean(GetPostContentDAOBean getPostContentDAOBean, UpdatePostContentDAOBean updatePostContentDAOBean, SavePostContentsDAOBean savePostContentsDAOBean) {
        this.getPostContentDAOBean = getPostContentDAOBean;
        this.updatePostContentDAOBean = updatePostContentDAOBean;
        this.savePostContentsDAOBean = savePostContentsDAOBean;
    }

    // Update the postContent
    public Long exec(RequestPostContentUpdateDTO requestPostContentUpdateDTO){

        // postContent 가져오기
        PostContentDAO postContentDAO = getPostContentDAOBean.exec(requestPostContentUpdateDTO.getPostContentId());
        if (postContentDAO == null) return 0L;

        // postContent 수정
        PostContentDAO updatePostContentDAO = updatePostContentDAOBean.exec(requestPostContentUpdateDTO, postContentDAO);

        // postContent 저장
        savePostContentsDAOBean.exec(updatePostContentDAO);

        return requestPostContentUpdateDTO.getPostContentId();
    }
}
