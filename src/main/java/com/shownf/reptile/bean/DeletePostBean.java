package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestPostDeleteDTO;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.GetPostDAOBean;
import com.shownf.reptile.bean.small.SavePostDAOBean;
import com.shownf.reptile.bean.small.SaveUserDAOBean;
import com.shownf.reptile.bean.small.UpdateUserPostCountDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeletePostBean {

    GetPostDAOBean getPostDAOBean;
    UpdateUserPostCountDAOBean updateUserPostCountDAOBean;


    SavePostDAOBean savePostDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public DeletePostBean(GetPostDAOBean getPostDAOBean, UpdateUserPostCountDAOBean updateUserPostCountDAOBean, SavePostDAOBean savePostDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.getPostDAOBean = getPostDAOBean;
        this.updateUserPostCountDAOBean = updateUserPostCountDAOBean;
        this.savePostDAOBean = savePostDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }
    public Long exec(RequestPostDeleteDTO requestPostDeleteDTO){

        // 게시물 아이디 찾기
        long postId = requestPostDeleteDTO.getPostId();

        // 아이디로 삭제할 게시글 찾기
        PostDAO postDAO = getPostDAOBean.exec(postId);
        if (postDAO == null) return 0L;

        // 게시물 deleteCheck 값 true 변경
        postDAO.setDeleteCheck(true);

        // 게시물 만든 유저인지 확인

        // 게시물 삭제시 유저 게시물, 좋아요, 댓글 수 감소
        UserDAO userDAO = updateUserPostCountDAOBean.exec(requestPostDeleteDTO, postDAO);
        if (userDAO == null) return 0L;


        // 게시물 저장
        savePostDAOBean.exec(postDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO);

        // postId 반환
        return postId;
    }
}
