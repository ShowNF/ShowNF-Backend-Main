package com.shownf.reptile.repository;

import com.shownf.reptile.Model.entity.PetDAO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepositoryJPA extends JpaRepository<PetDAO, Long> {

    List<PetDAO> findByUserId(Long userId);

    Page<PetDAO> findByUserId(Long userId, Pageable pageable);
}
