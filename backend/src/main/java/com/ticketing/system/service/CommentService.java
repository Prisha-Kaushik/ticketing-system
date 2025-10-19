package com.ticketing.system.service;

import com.ticketing.system.dto.CommentRequest;
import com.ticketing.system.entity.Comment;
import com.ticketing.system.entity.Ticket;
import com.ticketing.system.entity.User;
import com.ticketing.system.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public Comment createComment(CommentRequest commentRequest, Ticket ticket, User author) {
        Comment comment = new Comment(commentRequest.getContent(), ticket, author);
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByTicket(Ticket ticket) {
        return commentRepository.findByTicketOrderByCreatedAtAsc(ticket);
    }

    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
