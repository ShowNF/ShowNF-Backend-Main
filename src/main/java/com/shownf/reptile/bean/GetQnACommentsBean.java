package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.ResponseCommentsDTO;
import com.shownf.reptile.Model.DTO.qna.ResponseQnACommentGetDTO;
import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.Model.entity.qna.QnACommentDAO;
import com.shownf.reptile.bean.small.CheckDeleteQnACommentDAOBean;
import com.shownf.reptile.bean.small.CreateQnACommentDTOBean;
import com.shownf.reptile.bean.small.GetQnACommentsDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetQnACommentsBean {

    GetQnACommentsDAOBean getQnACommentsDAOBean;
    CheckDeleteQnACommentDAOBean checkDeleteQnACommentDAOBean;
    CreateQnACommentDTOBean createQnACommentDTOBean;

    @Autowired
    public GetQnACommentsBean(GetQnACommentsDAOBean getQnACommentsDAOBean, CheckDeleteQnACommentDAOBean checkDeleteQnACommentDAOBean, CreateQnACommentDTOBean createQnACommentDTOBean) {
        this.getQnACommentsDAOBean = getQnACommentsDAOBean;
        this.checkDeleteQnACommentDAOBean = checkDeleteQnACommentDAOBean;
        this.createQnACommentDTOBean = createQnACommentDTOBean;
    }

    // QnA 댓글 전체 조회
    public List<ResponseQnACommentGetDTO> exec(Long qnaPostId){

        // qnaPostId 로 게시물에 해당하는 댓글 찾기
        List<QnACommentDAO> qnaCommentDAOs = getQnACommentsDAOBean.exec(qnaPostId);

        // 댓글 조회시 삭제 확인
        List<QnACommentDAO> newQnACommentDAOs = checkDeleteQnACommentDAOBean.exec(qnaCommentDAOs);

        // DAO 객체 DTO 반환
        return createQnACommentDTOBean.exec(newQnACommentDAOs);
    }
}
