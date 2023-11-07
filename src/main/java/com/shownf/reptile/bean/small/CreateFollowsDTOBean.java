package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.ResponseFollowDTO;
import com.shownf.reptile.Model.entity.FollowDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateFollowsDTOBean {

    // 팔로우 DTO 변환
    public List<ResponseFollowDTO> exec(List<FollowDAO> followDAOs){

        List<ResponseFollowDTO> responseFollowDTOs = new ArrayList<>();

        for (FollowDAO followDAO : followDAOs){
            ResponseFollowDTO responseFollowDTO = new ResponseFollowDTO();

            responseFollowDTO.setFollowId(followDAO.getFollowId());
            responseFollowDTO.setFollowUserId(followDAO.getFollowUserId());
            responseFollowDTOs.add(responseFollowDTO);
        }

        return responseFollowDTOs;
    }
}
