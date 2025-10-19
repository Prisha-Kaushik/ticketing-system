package com.ticketing.system.repository;

import com.ticketing.system.entity.Comment;
import com.ticketing.system.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByTicketOrderByCreatedAtAsc(Ticket ticket);
    List<Comment> findByTicketOrderByCreatedAtDesc(Ticket ticket);
}
