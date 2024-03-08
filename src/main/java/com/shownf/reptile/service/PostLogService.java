package com.shownf.reptile.service;

import com.shownf.reptile.Model.DTO.ResponseMetaGetDTO;
import com.shownf.reptile.bean.GetPostLogsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PostLogService {

    GetPostLogsBean getPostLogsBean;

    @Autowired
    public PostLogService(GetPostLogsBean getPostLogsBean) {
        this.getPostLogsBean = getPostLogsBean;
    }

    // Get posts log
    public List<ResponseMetaGetDTO> getPostLogs(Long userId){
        return getPostLogsBean.exec(userId);
    }
}
