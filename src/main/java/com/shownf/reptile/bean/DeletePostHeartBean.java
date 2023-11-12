package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestPostHeartDeleteDTO;
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
    DeletePostHeartDAOBean deletePostHeartDAOBean;
    UpdatePostHeartCountDAOBean updatePostHeartCountDAOBean;
    SavePostDAOBean savePostDAOBean;
    UpdateUserHeartCountDAOBean updateUserHeartCountDAOBean;

    @Autowired
    public DeletePostHeartBean(GetPostHeartDAOBean getPostHeartDAOBean, CheckPostIdPostDAOBean checkPostIdPostDAOBean, CheckUserIdPostDAOBean checkUserIdPostDAOBean, DeletePostHeartDAOBean deletePostHeartDAOBean, UpdatePostHeartCountDAOBean updatePostHeartCountDAOBean, SavePostDAOBean savePostDAOBean, UpdateUserHeartCountDAOBean updateUserHeartCountDAOBean) {
        this.getPostHeartDAOBean = getPostHeartDAOBean;
        this.checkPostIdPostDAOBean = checkPostIdPostDAOBean;
        this.checkUserIdPostDAOBean = checkUserIdPostDAOBean;
        this.deletePostHeartDAOBean = deletePostHeartDAOBean;
        this.updatePostHeartCountDAOBean = updatePostHeartCountDAOBean;
        this.savePostDAOBean = savePostDAOBean;
        this.updateUserHeartCountDAOBean = updateUserHeartCountDAOBean;
    }

    public Long exec(RequestPostHeartDeleteDTO requestPostHeartDeleteDTO){

        // 좋아요 아이디 찾기
        Long postHeartId = requestPostHeartDeleteDTO.getPostHeartId();

        // 아이디로 삭제할 좋아요 찾기
        PostHeartDAO postHeartDAO = getPostHeartDAOBean.exec(postHeartId);

        // 좋아요 해당하는 게시물 확인
        if (!checkPostIdPostDAOBean.exec(postHeartDAO, requestPostHeartDeleteDTO))
            return null;

        // 좋아요 해당하는 유저 확인
        if (!checkUserIdPostDAOBean.exec(postHeartDAO, requestPostHeartDeleteDTO))
            return null;

        // 좋아요 삭제
        deletePostHeartDAOBean.exec(postHeartDAO);

        // 게시물 좋아요 갯수 감소
        PostDAO postDAO = updatePostHeartCountDAOBean.exec(postHeartId, postHeartDAO);

        // 게시물 저장
        savePostDAOBean.exec(postDAO);

        // 게시물 좋아요 삭제시 유저 좋아요 수 감소
        updateUserHeartCountDAOBean.exec(requestPostHeartDeleteDTO);

        // postHeartId 반환
        return postHeartId;
    }
}
