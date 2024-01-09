package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.PostContentDAO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdatePostContentDeleteCheckDAOBean {

    // Delete the Post Content
    public List<PostContentDAO> exec(List<PostContentDAO> postContentDAOs){

        for (PostContentDAO postContentDAO : postContentDAOs)
            postContentDAO.setDeleteCheck(true);

        return postContentDAOs;
    }
}
