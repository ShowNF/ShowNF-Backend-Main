package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.ResponseReplysDTO;
import com.shownf.reptile.bean.small.CreateReplysDTOBean;
import com.shownf.reptile.bean.small.DeleteCheckReplyDAOBean;
import com.shownf.reptile.bean.small.GetReplysDAOBean;
import com.shownf.reptile.Model.entity.ReplyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetReplysBean {

    GetReplysDAOBean getReplysDAOBean;
    DeleteCheckReplyDAOBean deleteCheckReplyDAOBean;
    CreateReplysDTOBean createReplysDTOBean;

    @Autowired
    public GetReplysBean(GetReplysDAOBean getReplysDAOBean, DeleteCheckReplyDAOBean deleteCheckReplyDAOBean, CreateReplysDTOBean createReplysDTOBean) {
        this.getReplysDAOBean = getReplysDAOBean;
        this.deleteCheckReplyDAOBean = deleteCheckReplyDAOBean;
        this.createReplysDTOBean = createReplysDTOBean;
    }

    // 댓글에 해당하는 대댓글 전부 조회
    public List<ResponseReplysDTO> exec(Long commentId){

        // commentId로 게시물에 해당하는 댓글 찾기
        List<ReplyDAO> replyDAOs = getReplysDAOBean.exec(commentId);

        // 대댓글 조회시 삭제 확인
        List<ReplyDAO> newReplyDAOs = deleteCheckReplyDAOBean.exec(replyDAOs);

        // 대댓글 DTO 반환
        return createReplysDTOBean.exec(newReplyDAOs);
    }
}
