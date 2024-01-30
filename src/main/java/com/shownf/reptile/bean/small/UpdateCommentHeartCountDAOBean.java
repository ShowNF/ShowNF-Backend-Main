package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.CommentDAO;
import com.shownf.reptile.Model.entity.CommentHeartDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateCommentHeartCountDAOBean {

    GetCommentDAOBean getCommentDAOBean;

    @Autowired
    public UpdateCommentHeartCountDAOBean(GetCommentDAOBean getCommentDAOBean) {
        this.getCommentDAOBean = getCommentDAOBean;
    }
}
