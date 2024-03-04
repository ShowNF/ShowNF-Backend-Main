package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.qna.RequestQnACommentSelectionDeleteDTO;
import com.shownf.reptile.Model.DTO.qna.ResponseQnACommentGetDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.Model.entity.qna.QnACommentDAO;
import com.shownf.reptile.Model.entity.qna.QnAPostDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class DeleteQnACommentSelectionBean {

    GetQnACommentDAOBean getQnACommentDAOBean;
    GetQnAPostDAOBean getQnAPostDAOBean;
    GetUserDAOBean getUserDAOBean;
    CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean;
    UpdateUserExpDAOBean updateUserExpDAOBean;
    SaveQnACommentDAOBean saveQnACommentDAOBean;
    SaveUserDAOBean saveUserDAOBean;
    CreateQnACommentDTOBean createQnACommentDTOBean;

    @Autowired
    public DeleteQnACommentSelectionBean(GetQnACommentDAOBean getQnACommentDAOBean, GetQnAPostDAOBean getQnAPostDAOBean, GetUserDAOBean getUserDAOBean, CheckUserAccessTokenDAOBean checkUserAccessTokenDAOBean, UpdateUserExpDAOBean updateUserExpDAOBean, SaveQnACommentDAOBean saveQnACommentDAOBean, SaveUserDAOBean saveUserDAOBean, CreateQnACommentDTOBean createQnACommentDTOBean) {
        this.getQnACommentDAOBean = getQnACommentDAOBean;
        this.getQnAPostDAOBean = getQnAPostDAOBean;
        this.getUserDAOBean = getUserDAOBean;
        this.checkUserAccessTokenDAOBean = checkUserAccessTokenDAOBean;
        this.updateUserExpDAOBean = updateUserExpDAOBean;
        this.saveQnACommentDAOBean = saveQnACommentDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
        this.createQnACommentDTOBean = createQnACommentDTOBean;
    }

    // QnA 댓글 채택 삭제
    public ResponseQnACommentGetDTO exec(RequestQnACommentSelectionDeleteDTO requestQnACommentSelectionDeleteDTO, HttpServletRequest request){

        // 채택 삭제할 QnA 댓글 가져오기
        QnACommentDAO qnACommentDAO = getQnACommentDAOBean.exec(requestQnACommentSelectionDeleteDTO.getQnaCommentId());
        if (qnACommentDAO == null) return null;

        // 채택 삭제할 QnA 댓글의 QnA 게시글 가져오기
        QnAPostDAO qnAPostDAO = getQnAPostDAOBean.exec(qnACommentDAO.getQnaPostId());
        if (qnAPostDAO == null) return null;

        // 채택 삭제한 유저와 QnA 게시글 유저 일치여부
        if (!(requestQnACommentSelectionDeleteDTO.getUserId().equals(qnAPostDAO.getUserId()))) return null;

        // 중복 제거
        if (qnACommentDAO.getUserId().equals(qnAPostDAO.getUserId())) return null;

        // 유저 권한 확인
        UserDAO userDAO = getUserDAOBean.exec(requestQnACommentSelectionDeleteDTO.getUserId());
        if (userDAO == null) return null;
        if (!checkUserAccessTokenDAOBean.exec(userDAO, request)) return null;

        // 댓글 채택 여부 변경
        qnACommentDAO.setSelection(false);

        // 채택 삭제된 유저 객체 가져오기
        UserDAO qnaCommentUserDAO = getUserDAOBean.exec(qnACommentDAO.getUserId());
        if (qnaCommentUserDAO == null) return null;

        // 유저 채택수 감소
        qnaCommentUserDAO.setSelectionCount(qnaCommentUserDAO.getSelectionCount() - 1);

        // 유저 경험치 감소
        updateUserExpDAOBean.exec(qnaCommentUserDAO, requestQnACommentSelectionDeleteDTO);

        // 채택 삭제된 QnA 댓글 저장
        saveQnACommentDAOBean.exec(qnACommentDAO);

        // 채택 삭제된 유저 저장
        saveUserDAOBean.exec(qnaCommentUserDAO);

        return createQnACommentDTOBean.exec(qnACommentDAO);
    }
}
