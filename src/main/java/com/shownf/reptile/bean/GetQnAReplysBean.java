package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.qna.ResponseQnAReplyGetDTO;
import com.shownf.reptile.Model.entity.qna.QnAReplyDAO;
import com.shownf.reptile.bean.small.CheckDeleteQnAReplyDAOBean;
import com.shownf.reptile.bean.small.CreateQnAReplyDTOBean;
import com.shownf.reptile.bean.small.GetQnAReplyDAOsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetQnAReplysBean {

    GetQnAReplyDAOsBean getQnAReplyDAOsBean;
    CheckDeleteQnAReplyDAOBean deleteCheckReplyDAOBean;
    CreateQnAReplyDTOBean createQnAReplysDTOBean;

    @Autowired
    public GetQnAReplysBean(GetQnAReplyDAOsBean getQnAReplyDAOsBean, CheckDeleteQnAReplyDAOBean deleteCheckReplyDAOBean, CreateQnAReplyDTOBean createQnAReplysDTOBean) {
        this.getQnAReplyDAOsBean = getQnAReplyDAOsBean;
        this.deleteCheckReplyDAOBean = deleteCheckReplyDAOBean;
        this.createQnAReplysDTOBean = createQnAReplysDTOBean;
    }

    // 댓글에 해당하는 대댓글 전부 조회
    public List<ResponseQnAReplyGetDTO> exec(Long qnaCommentId){

        // commentId로 게시물에 해당하는 댓글 찾기
        List<QnAReplyDAO> qnAReplyDAOs = getQnAReplyDAOsBean.exec(qnaCommentId);

        // 대댓글 조회시 삭제 확인
        List<QnAReplyDAO> newReplyDAOs = deleteCheckReplyDAOBean.exec(qnAReplyDAOs);

        // 대댓글 DTO 반환
        return createQnAReplysDTOBean.exec(newReplyDAOs);
    }
}
