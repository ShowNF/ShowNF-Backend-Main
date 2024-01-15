package com.shownf.reptile.service;

import com.shownf.reptile.Model.DTO.ResponsePostMetaDTO;
import com.shownf.reptile.bean.GetPostLogsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostLogService {

    GetPostLogsBean getPostLogsBean;

    @Autowired
    public PostLogService(GetPostLogsBean getPostLogsBean) {
        this.getPostLogsBean = getPostLogsBean;
    }

    // Get posts log
    public List<ResponsePostMetaDTO> getPostLogs(Long userId){
        return getPostLogsBean.exec(userId);
    }
}
