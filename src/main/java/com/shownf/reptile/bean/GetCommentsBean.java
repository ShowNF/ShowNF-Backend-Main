package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.ResponseCommentsDTO;
import com.shownf.reptile.bean.small.CreateCommentsDTOBean;
import com.shownf.reptile.bean.small.DeleteCheckCommentDAOBean;
import com.shownf.reptile.bean.small.GetCommentsDAOBean;
import com.shownf.reptile.Model.entity.CommentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetCommentsBean {

    GetCommentsDAOBean getCommentsDAOBean;
    CreateCommentsDTOBean createCommentsDTOBean;
    DeleteCheckCommentDAOBean deleteCheckCommentDAOBean;

    @Autowired
    public GetCommentsBean(GetCommentsDAOBean getCommentsDAOBean, CreateCommentsDTOBean createCommentsDTOBean, DeleteCheckCommentDAOBean deleteCheckCommentDAOBean) {
        this.getCommentsDAOBean = getCommentsDAOBean;
        this.createCommentsDTOBean = createCommentsDTOBean;
        this.deleteCheckCommentDAOBean = deleteCheckCommentDAOBean;
    }

    // 댓글 전체 조회
    public List<ResponseCommentsDTO> exec(Long postId){

        // postId 로 게시물에 해당하는 댓글 찾기
        List<CommentDAO> commentDAOs = getCommentsDAOBean.exec(postId);

        // 댓글 조회시 삭제 확인
        List<CommentDAO> newCommentDAOs = deleteCheckCommentDAOBean.exec(commentDAOs);

        // DAO 객체 DTO 반환
        return createCommentsDTOBean.exec(newCommentDAOs);
    }
}
