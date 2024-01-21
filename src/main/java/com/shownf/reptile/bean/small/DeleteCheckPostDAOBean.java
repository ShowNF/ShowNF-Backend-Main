package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.MetaDAO.PostMeta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeleteCheckPostDAOBean {

    // 대댓글 삭제 여부 확인
    public Page<PostMeta> exec(Page<PostMeta> postMetas){
        List<PostMeta> newPostMetas = new ArrayList<>();

        for (PostMeta postMeta : postMetas) {
            if (!postMeta.isDeleteCheck()) {
                newPostMetas.add(postMeta);
            }
        }

        return new PageImpl<>(newPostMetas, postMetas.getPageable(), newPostMetas.size());
    }
}
