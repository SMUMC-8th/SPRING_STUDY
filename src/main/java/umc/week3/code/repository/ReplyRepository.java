package umc.week3.code.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.week3.code.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{}