package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.Model.entity.ReplyDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteCheckPostDAOBean {

    // 대댓글 삭제 여부 확인
    public Page<PostDAO> exec(Page<PostDAO> postDAOs){
        List<PostDAO> newPostDAOs = new ArrayList<>();

        for (PostDAO postDAO : postDAOs.getContent()) {
            if (!postDAO.isDeleteCheck()) {
                newPostDAOs.add(postDAO);
            }
        }

        return new PageImpl<>(newPostDAOs, postDAOs.getPageable(), newPostDAOs.size());
    }
}
