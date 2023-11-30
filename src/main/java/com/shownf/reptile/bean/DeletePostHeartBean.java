package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestPostHeartDeleteDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.*;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.Model.entity.PostHeartDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeletePostHeartBean {

    GetPostHeartDAOBean getPostHeartDAOBean;
    CheckPostIdPostDAOBean checkPostIdPostDAOBean;
    CheckUserIdPostDAOBean checkUserIdPostDAOBean;
    UpdatePostHeartCountDAOBean updatePostHeartCountDAOBean;
    UpdateUserHeartCountDAOBean updateUserHeartCountDAOBean;
    UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean;
    UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean;
    DeletePostHeartDAOBean deletePostHeartDAOBean;
    SavePostDAOBean savePostDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public DeletePostHeartBean(GetPostHeartDAOBean getPostHeartDAOBean, CheckPostIdPostDAOBean checkPostIdPostDAOBean, CheckUserIdPostDAOBean checkUserIdPostDAOBean, UpdatePostHeartCountDAOBean updatePostHeartCountDAOBean, UpdateUserHeartCountDAOBean updateUserHeartCountDAOBean, UpdateUserReceiveHeartDAOBean updateUserReceiveHeartDAOBean, UpdateUserSendHeartDAOBean updateUserSendHeartDAOBean, DeletePostHeartDAOBean deletePostHeartDAOBean, SavePostDAOBean savePostDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.getPostHeartDAOBean = getPostHeartDAOBean;
        this.checkPostIdPostDAOBean = checkPostIdPostDAOBean;
        this.checkUserIdPostDAOBean = checkUserIdPostDAOBean;
        this.updatePostHeartCountDAOBean = updatePostHeartCountDAOBean;
        this.updateUserHeartCountDAOBean = updateUserHeartCountDAOBean;
        this.updateUserReceiveHeartDAOBean = updateUserReceiveHeartDAOBean;
        this.updateUserSendHeartDAOBean = updateUserSendHeartDAOBean;
        this.deletePostHeartDAOBean = deletePostHeartDAOBean;
        this.savePostDAOBean = savePostDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    public Long exec(RequestPostHeartDeleteDTO requestPostHeartDeleteDTO){

        // 게시물 아이디와 유저 아이디로 객체 찾기
        PostHeartDAO postHeartDAO = getPostHeartDAOBean.exec(requestPostHeartDeleteDTO);

        // 취소 중복 배제
        if (postHeartDAO == null)
            return 0L;

        // 좋아요 해당하는 게시물 확인
        if (!checkPostIdPostDAOBean.exec(postHeartDAO, requestPostHeartDeleteDTO))
            return null;

        // 좋아요 해당하는 유저 확인
        if (!checkUserIdPostDAOBean.exec(postHeartDAO, requestPostHeartDeleteDTO))
            return null;

        // 게시물 좋아요 갯수 감소
        PostDAO postDAO = updatePostHeartCountDAOBean.exec(null, postHeartDAO);
        if (postDAO == null) return 0L;

        // 게시물 좋아요 삭제시 유저 좋아요 수 감소
        UserDAO userDAO = updateUserHeartCountDAOBean.exec(null, postDAO);
        if (userDAO == null) return 0L;

        // 좋아요 sender, receiver 추가
        UserDAO userDAO1 = updateUserReceiveHeartDAOBean.exec(null, postDAO);
        if (userDAO1 == null) return 0L;

        UserDAO userDAO2 = updateUserSendHeartDAOBean.exec(null, postHeartDAO);
        if (userDAO2 == null) return 0L;

        // 좋아요 삭제
        deletePostHeartDAOBean.exec(postHeartDAO);

        // 게시물 저장
        savePostDAOBean.exec(postDAO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO);
        saveUserDAOBean.exec(userDAO1);
        saveUserDAOBean.exec(userDAO2);

        // postHeartId 반환
        return postHeartDAO.getPostHeartId();
    }
}
