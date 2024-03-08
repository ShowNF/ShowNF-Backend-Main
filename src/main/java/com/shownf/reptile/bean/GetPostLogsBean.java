package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.ResponseMetaGetDTO;
import com.shownf.reptile.Model.MetaDAO.PostMeta;
import com.shownf.reptile.Model.MetaDAO.QnAPostMeta;
import com.shownf.reptile.Model.entity.PostContentDAO;
import com.shownf.reptile.Model.entity.PostDAO;
import com.shownf.reptile.Model.entity.PostLogDAO;
import com.shownf.reptile.Model.entity.qna.QnAPostDAO;
import com.shownf.reptile.bean.small.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GetPostLogsBean {

    GetPostLogsDAOBean getPostLogsDAOBean;
    GetQnAPostMetaDAOBean getQnAPostMetaDAOBean;
    GetPostMetaDAOBean getPostMetaDAOBean;
    GetPostContentDAOBean getPostContentDAOBean;

    @Autowired
    public GetPostLogsBean(GetPostLogsDAOBean getPostLogsDAOBean, GetQnAPostMetaDAOBean getQnAPostMetaDAOBean, GetPostMetaDAOBean getPostMetaDAOBean, GetPostContentDAOBean getPostContentDAOBean) {
        this.getPostLogsDAOBean = getPostLogsDAOBean;
        this.getQnAPostMetaDAOBean = getQnAPostMetaDAOBean;
        this.getPostMetaDAOBean = getPostMetaDAOBean;
        this.getPostContentDAOBean = getPostContentDAOBean;
    }


    // Get posts log
    public List<ResponseMetaGetDTO> exec(Long userId){

        // userId로 최근 조회한 게시물 가져오기
        List<PostLogDAO> postLogDAOs = getPostLogsDAOBean.exec(userId);

        // 최근 조회한 게시물에서 키값이 postId이고, 벨류가 type인 데이터  뽑기
        List<ResponseMetaGetDTO> result = new ArrayList<>();

        // 최근 조회한 게시물에서 키값이 postId이고, 벨류가 type인 데이터 뽑기
        for (PostLogDAO postLogDAO : postLogDAOs) {
            ResponseMetaGetDTO responseMetaGetDTO = new ResponseMetaGetDTO();
            if (postLogDAO.getType() == 1) {
                QnAPostMeta qnAPostMeta = getQnAPostMetaDAOBean.exec(postLogDAO.getPostId());
                responseMetaGetDTO.setMetaId(qnAPostMeta.getQnaPostId());
                responseMetaGetDTO.setUserId(qnAPostMeta.getUserId());
                responseMetaGetDTO.setImageUrl(qnAPostMeta.getImageUrl());
                responseMetaGetDTO.setTitle(qnAPostMeta.getTitle());

                result.add(responseMetaGetDTO);
            }
            else {
                PostMeta postMeta = getPostMetaDAOBean.exec(postLogDAO.getPostId());

                PostContentDAO postContentDAO = getPostContentDAOBean.exec(Long.parseLong(postMeta.getContent()));

                responseMetaGetDTO.setMetaId(postMeta.getPostId());
                responseMetaGetDTO.setUserId(postMeta.getUserId());
                responseMetaGetDTO.setImageUrl(postContentDAO.getImageUrl());
                responseMetaGetDTO.setTitle(postMeta.getTitle());

                result.add(responseMetaGetDTO);
            }
        }

        return result;
    }
}
