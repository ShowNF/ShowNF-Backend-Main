package com.shownf.reptile.bean;

import com.shownf.reptile.Model.DTO.ResponseRecommendUserGetDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import com.shownf.reptile.bean.small.CreateRecommendUserDTOBean;
import com.shownf.reptile.bean.small.GetUserDAOBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetRecommendUserBean {

    GetUserDAOBean getUserDAOBean;
    CreateRecommendUserDTOBean createRecommendUserDTOBean;

    @Autowired
    public GetRecommendUserBean(GetUserDAOBean getUserDAOBean, CreateRecommendUserDTOBean createRecommendUserDTOBean) {
        this.getUserDAOBean = getUserDAOBean;
        this.createRecommendUserDTOBean = createRecommendUserDTOBean;
    }

    public List<ResponseRecommendUserGetDTO> exec() {

        // 유저 5명 찾기
        List<UserDAO> userDAOS = getUserDAOBean.exec();

        return createRecommendUserDTOBean.exec(userDAOS);
    }
}
