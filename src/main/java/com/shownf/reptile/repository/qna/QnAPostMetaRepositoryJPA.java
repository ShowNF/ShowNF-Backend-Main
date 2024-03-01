package com.shownf.reptile.repository.qna;

import com.shownf.reptile.Model.MetaDAO.QnAPostMeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnAPostMetaRepositoryJPA extends JpaRepository<QnAPostMeta, Long> {
}
