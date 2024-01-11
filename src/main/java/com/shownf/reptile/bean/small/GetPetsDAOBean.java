package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.PetDAO;
import com.shownf.reptile.repository.PetRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetPetsDAOBean {

    PetRepositoryJPA petRepositoryJPA;

    @Autowired
    public GetPetsDAOBean(PetRepositoryJPA petRepositoryJPA) {
        this.petRepositoryJPA = petRepositoryJPA;
    }

    // 유저 아이디로 마이펫 전체 찾기
    public List<PetDAO> exec(Long userId){
        return petRepositoryJPA.findByUserId(userId);
    }

    // 유저 아이디로 마이펫 전체 찾기
    public Page<PetDAO> exec(Long userId, Pageable pageable){
        return petRepositoryJPA.findByUserId(userId, pageable);
    }
}
