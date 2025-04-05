package umc.week3.code.reply.service.query;

import umc.week3.code.reply.entity.Reply;
import java.util.List;

public interface ReplyQueryService {

    List<Reply> getReplies();
    Reply getReply(Long id);
}
