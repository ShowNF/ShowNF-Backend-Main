package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.qna.RequestQnAPostSaveDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveQnAPostBean {

    CreateUniqueIdBean createUniqueIdBean;
    UpdateUserPostCountDAOBean updateUserPostCountDAOBean;
    UpdateUserExpDAOBean updateUserExpDAOBean;
    SaveQnAPostDAOBean saveQnAPostDAOBean;
    SaveQnAPostMetaDAOBean saveQnAPostMetaDAOBean;
    SaveUserDAOBean saveUserDAOBean;

    @Autowired
    public SaveQnAPostBean(CreateUniqueIdBean createUniqueIdBean, UpdateUserPostCountDAOBean updateUserPostCountDAOBean, UpdateUserExpDAOBean updateUserExpDAOBean, SaveQnAPostDAOBean saveQnAPostDAOBean, SaveQnAPostMetaDAOBean saveQnAPostMetaDAOBean, SaveUserDAOBean saveUserDAOBean) {
        this.createUniqueIdBean = createUniqueIdBean;
        this.updateUserPostCountDAOBean = updateUserPostCountDAOBean;
        this.updateUserExpDAOBean = updateUserExpDAOBean;
        this.saveQnAPostDAOBean = saveQnAPostDAOBean;
        this.saveQnAPostMetaDAOBean = saveQnAPostMetaDAOBean;
        this.saveUserDAOBean = saveUserDAOBean;
    }

    // QnA 게시글 저장
    public Long exec(RequestQnAPostSaveDTO requestQnAPostSaveDTO){

        // qna postId 생성
        Long qnaPostId = createUniqueIdBean.exec();

        // qna 게시물 저장 시 유저 게시물 수 증가
        UserDAO userDAO = updateUserPostCountDAOBean.exec(requestQnAPostSaveDTO);
        if (userDAO == null) return 0L;

        // 경험치 추가
        userDAO = updateUserExpDAOBean.exec(requestQnAPostSaveDTO, userDAO);

        // QnA 게시물 저장
        saveQnAPostDAOBean.exec(qnaPostId, requestQnAPostSaveDTO);

        // QnA 게시물 메타 데이터 저장
        saveQnAPostMetaDAOBean.exec(qnaPostId, requestQnAPostSaveDTO);

        // 유저 저장
        saveUserDAOBean.exec(userDAO);

        // 게시물 postId 반환
        return qnaPostId;
    }
}
