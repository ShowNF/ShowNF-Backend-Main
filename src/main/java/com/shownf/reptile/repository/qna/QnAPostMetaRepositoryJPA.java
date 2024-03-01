package com.shownf.reptile.repository.qna;

import com.shownf.reptile.Model.MetaDAO.QnAPostMeta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnAPostMetaRepositoryJPA extends JpaRepository<QnAPostMeta, Long> {
    Page<QnAPostMeta> findByUserId(Long userId, Pageable pageable);
}
