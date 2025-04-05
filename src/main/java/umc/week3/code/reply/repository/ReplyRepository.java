package umc.week3.code.reply.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.week3.code.reply.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{}