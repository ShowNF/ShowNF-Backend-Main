package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.PostLogDAO;
import com.shownf.reptile.repository.PostLogRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Component
public class SavePostLogDAOBean {

    PostLogRepositoryJPA postLogRepositoryJPA;
    CreateUniqueIdBean createUniqueIdBean;

    @Autowired
    public SavePostLogDAOBean(PostLogRepositoryJPA postLogRepositoryJPA, CreateUniqueIdBean createUniqueIdBean) {
        this.postLogRepositoryJPA = postLogRepositoryJPA;
        this.createUniqueIdBean = createUniqueIdBean;
    }

    public void exec(PostLogDAO postLogDAO){
        postLogRepositoryJPA.save(postLogDAO);
    }

    // Save the post log
    public void exec(Long postId, Long userId){

        if (userId == null){
            return;
        }

        // userId로 Post Log 전부 가져오기
        List<PostLogDAO> postLogDAOs = postLogRepositoryJPA.findByUserId(userId);

        // 가져온 post log 에 이번에 조회한 post 가 있는 경우는 갱신
        for (PostLogDAO postLogDAO : postLogDAOs){
            if (postLogDAO.getPostId().equals(postId)) {
                postLogDAO.setViewTime(LocalDateTime.now());
                exec(postLogDAO);
                return;
            }
        }

        // 가져온 post log 10개 이상인 경우 가장 오래전 조회 post 삭제
        if (postLogDAOs.size() >= 10) {
            postLogDAOs.stream()
                    .min(Comparator.comparing(PostLogDAO::getViewTime)).ifPresent(oldestPostLog -> postLogRepositoryJPA.delete(oldestPostLog));
        }

        // post log 저장
        long postLogId = createUniqueIdBean.exec();
        exec(new PostLogDAO(postLogId, postId, userId, LocalDateTime.now()));
    }
}
