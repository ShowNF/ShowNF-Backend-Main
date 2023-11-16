package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.RequestPostDeleteDTO;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.bean.small.GetPostDAOBean;
import org.springframework.stereotype.Component;

@Component
public class DeletePostBean {

    GetPostDAOBean getPostDAOBean;

    public Long exec(RequestPostDeleteDTO requestPostDeleteDTO){

        // 게시물 아이디 찾기
        long postId = requestPostDeleteDTO.getPostId();

        // 아이디로 삭제할 게시글 찾기
        PostDAO postDAO = getPostDAOBean.exec(postId);
        if (postDAO == null) return 0L;

        // 게시물 deleteCheck 값 true 변경
        postDAO.setDeleteCheck(true);

        // 게시물 만든 유저인지 확인

        // 게시물 삭제시 유저 게시물, 좋아요, 댓글 수 ㄱ마소

        // 게시물 저장

        // 유저 저장

        // postId 반환
        return postId;
    }
}
