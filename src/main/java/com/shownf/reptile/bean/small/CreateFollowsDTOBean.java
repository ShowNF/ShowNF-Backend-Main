package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.ResponseFollowerDTO;
import com.shownf.reptile.Model.DTO.ResponseFollowingDTO;
import com.shownf.reptile.Model.entity.FollowDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CreateFollowsDTOBean {

    // 팔로우 DTO 변환
    public List<ResponseFollowerDTO> exec(List<FollowDAO> followDAOs){

        List<ResponseFollowerDTO> responseFollowDTOs = new ArrayList<>();

        for (FollowDAO followDAO : followDAOs){
            ResponseFollowerDTO responseFollowDTO = new ResponseFollowerDTO();

            responseFollowDTO.setFollowId(followDAO.getFollowId());
            responseFollowDTO.setUserId(followDAO.getUserId());
            responseFollowDTOs.add(responseFollowDTO);
        }

        return responseFollowDTOs;
    }

    // 팔로잉 DTO 변환
    public List<ResponseFollowingDTO> exec(List<FollowDAO> followDAOs, String check){

        List<ResponseFollowingDTO> responseFollowingDTOS = new ArrayList<>();

        for (FollowDAO followDAO : followDAOs){
            ResponseFollowingDTO responseFollowingDTO = new ResponseFollowingDTO();

            responseFollowingDTO.setFollowId(followDAO.getFollowId());
            responseFollowingDTO.setFollowUserId(followDAO.getFollowUserId());
            responseFollowingDTOS.add(responseFollowingDTO);
        }

        return responseFollowingDTOS;
    }
}
