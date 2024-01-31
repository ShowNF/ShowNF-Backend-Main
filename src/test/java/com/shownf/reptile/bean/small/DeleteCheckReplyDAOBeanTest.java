package com.shownf.reptile.bean.small;

import com.shownf.reptile.Model.entity.ReplyDAO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DeleteCheckReplyDAOBeanTest {

    @Autowired
    DeleteCheckReplyDAOBean deleteCheckReplyDAOBean;

    @Test
    public void testDeleteCheck() {
        // Create a list of PostMeta objects for testing
        List<ReplyDAO> replyDAOS = new ArrayList<>();

        ReplyDAO replyDAO = new ReplyDAO();
        replyDAO.setDeleteCheck(false);

        ReplyDAO replyDAO1 = new ReplyDAO();
        replyDAO1.setDeleteCheck(false);

        ReplyDAO replyDAO2 = new ReplyDAO();
        replyDAO2.setDeleteCheck(true);

        replyDAOS.add(replyDAO);
        replyDAOS.add(replyDAO1);
        replyDAOS.add(replyDAO2);

        // Execute the delete check
        List<ReplyDAO> result = deleteCheckReplyDAOBean.exec(replyDAOS);

        // Validate the changes
        assertEquals(2, result.size());
    }
}