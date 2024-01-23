package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.DTO.ResponseFollowerDTO;
import com.shownf.reptile.Model.DTO.ResponseFollowingDTO;
import com.shownf.reptile.Model.entity.FollowDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CreateFollowsDTOBeanTest {

    @Autowired
    CreateFollowsDTOBean createFollowsDTOBean;

    @Test
    void execFollowers() {
        // 테스트할 데이터 생성
        List<FollowDAO> followDAOs = new ArrayList<>();
        FollowDAO followDAO1 = new FollowDAO(1L, 100L, 50L, null);
        FollowDAO followDAO2 = new FollowDAO(2L, 100L, 60L, null);
        followDAOs.add(followDAO1);
        followDAOs.add(followDAO2);

        // 테스트 실행
        List<ResponseFollowerDTO> resultResponseFollowDTOs = createFollowsDTOBean.exec(followDAOs);

        // 결과 검증
        assertThat(resultResponseFollowDTOs).hasSize(2);

        ResponseFollowerDTO resultDTO1 = resultResponseFollowDTOs.get(0);
        assertThat(resultDTO1.getFollowId()).isEqualTo(followDAO1.getFollowId());
        assertThat(resultDTO1.getUserId()).isEqualTo(followDAO1.getUserId());

        ResponseFollowerDTO resultDTO2 = resultResponseFollowDTOs.get(1);
        assertThat(resultDTO2.getFollowId()).isEqualTo(followDAO2.getFollowId());
        assertThat(resultDTO2.getUserId()).isEqualTo(followDAO2.getUserId());
    }

    @Test
    void execFollowings() {
        // 테스트할 데이터 생성
        List<FollowDAO> followDAOs = new ArrayList<>();
        FollowDAO followDAO1 = new FollowDAO(1L, 100L, 50L, null);
        FollowDAO followDAO2 = new FollowDAO(2L, 110L, 60L, null);
        followDAOs.add(followDAO1);
        followDAOs.add(followDAO2);

        // 테스트 실행
        List<ResponseFollowingDTO> resultResponseFollowingDTOs = createFollowsDTOBean.exec(followDAOs, "check");

        // 결과 검증
        assertThat(resultResponseFollowingDTOs).hasSize(2);

        ResponseFollowingDTO resultDTO1 = resultResponseFollowingDTOs.get(0);
        assertThat(resultDTO1.getFollowId()).isEqualTo(followDAO1.getFollowId());
        assertThat(resultDTO1.getFollowUserId()).isEqualTo(followDAO1.getFollowUserId());

        ResponseFollowingDTO resultDTO2 = resultResponseFollowingDTOs.get(1);
        assertThat(resultDTO2.getFollowId()).isEqualTo(followDAO2.getFollowId());
        assertThat(resultDTO2.getFollowUserId()).isEqualTo(followDAO2.getFollowUserId());
    }
}