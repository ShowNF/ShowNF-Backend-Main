package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.ResponseRecommendUserGetDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.CreateRecommendUserDTOBean;
import com.shownf.reptile.bean.small.GetUserDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetUserSearchBean {

    GetUserDAOBean getUserDAOBean;
    CreateRecommendUserDTOBean createRecommendUserDTOBean;

    @Autowired
    public GetUserSearchBean(GetUserDAOBean getUserDAOBean, CreateRecommendUserDTOBean createRecommendUserDTOBean) {
        this.getUserDAOBean = getUserDAOBean;
        this.createRecommendUserDTOBean = createRecommendUserDTOBean;
    }

    // 유저 검색 조회
    public List<ResponseRecommendUserGetDTO> exec(String search) {

        // 검색어를 기준으로 사용자를 조회하는 메서드
        List<UserDAO> userDAOS = getUserDAOBean.exec(search);

        // DTO 변환 후 반환
        return createRecommendUserDTOBean.exec(userDAOS);
    }
}
