package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.RequestCommentDeleteDTO;
import com.shownf.reptile.Model.DTO.RequestCommentSaveDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.repository.PostRepositoryJPA;
import com.shownf.reptile.repository.UserRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserCommentCountDAOBean {

    PostRepositoryJPA postRepositoryJPA;
    UserRepositoryJPA userRepositoryJPA;

    @Autowired
    public UpdateUserCommentCountDAOBean(UserRepositoryJPA userRepositoryJPA, PostRepositoryJPA postRepositoryJPA) {
        this.postRepositoryJPA = postRepositoryJPA;
        this.userRepositoryJPA = userRepositoryJPA;
    }

    // 댓글 추가시 유저 댓글 수 증가
    public void exec(RequestCommentSaveDTO requestCommentSaveDTO){

        // 댓글 추가된 게시물 유저 아이디 찾기
        Long userId = postRepositoryJPA.findById(requestCommentSaveDTO.getPostId()).get().getUserId();

        // 유저 아이디로 유저 찾기
        UserDAO userDAO = userRepositoryJPA.findById(userId).get();

        // 유저 commentCount 증가
        userDAO.setCommentCount(userDAO.getCommentCount() + 1);

        // 유저 저장
        userRepositoryJPA.save(userDAO);
    }

    
}
