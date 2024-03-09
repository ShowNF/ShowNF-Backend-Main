package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.ResponseRecommendUserGetDTO;
import com.shownf.reptile.Model.entity.UserDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateRecommendUserDTOBean {

    public ResponseRecommendUserGetDTO exec(UserDAO userDAO) {
        ResponseRecommendUserGetDTO responseRecommendUserGetDTO = new ResponseRecommendUserGetDTO();

        responseRecommendUserGetDTO.setUserId(userDAO.getUserId());
        responseRecommendUserGetDTO.setUserName(userDAO.getSiteName());
        responseRecommendUserGetDTO.setImageUrl(userDAO.getImage());

        return responseRecommendUserGetDTO;
    }

    public List<ResponseRecommendUserGetDTO> exec(List<UserDAO> userDAOs) {

        List<ResponseRecommendUserGetDTO> responseRecommendUserGetDTOs = new ArrayList<>();

        for (UserDAO userDAO : userDAOs) {
            responseRecommendUserGetDTOs.add(exec(userDAO));
        }

        return responseRecommendUserGetDTOs;
    }
}
