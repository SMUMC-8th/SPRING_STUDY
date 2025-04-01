package umc.week3.code.service.query;

import umc.week3.code.entity.Reply;
import java.util.List;

public interface ReplyQueryService {

    List<Reply> getReplies();
    Reply getReply(Long id);
}
