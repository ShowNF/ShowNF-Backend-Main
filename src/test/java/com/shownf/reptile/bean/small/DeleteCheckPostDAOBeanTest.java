package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.MetaDAO.PostMeta;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DeleteCheckPostDAOBeanTest {

    @Mock
    private Pageable pageable;

    @InjectMocks
    private DeleteCheckPostDAOBean deleteCheckPostDAOBean;

    @Test
    public void testDeleteCheck() {
        // Create a list of PostMeta objects for testing
        List<PostMeta> postMetas = new ArrayList<>();

        PostMeta postMeta = new PostMeta();
        postMeta.setDeleteCheck(false);

        PostMeta postMeta1 = new PostMeta();
        postMeta1.setDeleteCheck(false);

        PostMeta postMeta2 = new PostMeta();
        postMeta2.setDeleteCheck(true);

        postMetas.add(postMeta);
        postMetas.add(postMeta1);
        postMetas.add(postMeta2);

        // Mock Page
        Page<PostMeta> page = new PageImpl<>(postMetas, pageable, postMetas.size());

        // Execute the delete check
        Page<PostMeta> result = deleteCheckPostDAOBean.exec(page);

        // Validate the changes
        assertEquals(2, result.getTotalElements());
  }
}
