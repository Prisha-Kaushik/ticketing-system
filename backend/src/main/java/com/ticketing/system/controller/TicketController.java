package com.ticketing.system.controller;

import com.ticketing.system.dto.CommentRequest;
import com.ticketing.system.dto.TicketRequest;
import com.ticketing.system.entity.*;
import com.ticketing.system.service.CommentService;
import com.ticketing.system.service.TicketService;
import com.ticketing.system.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> createTicket(@Valid @RequestBody TicketRequest ticketRequest, Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        Ticket ticket = ticketService.createTicket(ticketRequest, currentUser);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        List<Ticket> tickets;
        
        if (currentUser.getRole() == Role.ADMIN) {
            tickets = ticketService.getAllTickets();
        } else {
            tickets = ticketService.getTicketsByOwnerOrAssignee(currentUser);
        }
        
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/my-tickets")
    public ResponseEntity<List<Ticket>> getMyTickets(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        List<Ticket> tickets = ticketService.getTicketsByOwner(currentUser);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/assigned-to-me")
    public ResponseEntity<List<Ticket>> getAssignedTickets(Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        List<Ticket> tickets = ticketService.getTicketsByAssignee(currentUser);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicket(@PathVariable Long id, Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        Optional<Ticket> ticketOpt = ticketService.findById(id);
        
        if (ticketOpt.isPresent()) {
            Ticket ticket = ticketOpt.get();
            
            // Check if user has access to this ticket
            if (currentUser.getRole() == Role.ADMIN || 
                ticket.getOwner().getId().equals(currentUser.getId()) || 
                (ticket.getAssignee() != null && ticket.getAssignee().getId().equals(currentUser.getId()))) {
                return ResponseEntity.ok(ticket);
            } else {
                return ResponseEntity.status(403).body(null);
            }
        }
        
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Ticket> updateTicketStatus(@PathVariable Long id, @RequestBody TicketStatus status, Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        Optional<Ticket> ticketOpt = ticketService.findById(id);
        
        if (ticketOpt.isPresent()) {
            Ticket ticket = ticketOpt.get();
            
            // Check permissions
            if (currentUser.getRole() == Role.ADMIN || 
                (ticket.getAssignee() != null && ticket.getAssignee().getId().equals(currentUser.getId()))) {
                
                ticket.setStatus(status);
                Ticket updatedTicket = ticketService.updateTicket(ticket);
                return ResponseEntity.ok(updatedTicket);
            } else {
                return ResponseEntity.status(403).body(null);
            }
        }
        
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/assign")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPPORT_AGENT')")
    public ResponseEntity<Ticket> assignTicket(@PathVariable Long id, @RequestBody Long assigneeId, Authentication authentication) {
        Optional<Ticket> ticketOpt = ticketService.findById(id);
        Optional<User> assigneeOpt = userService.findById(assigneeId);
        
        if (ticketOpt.isPresent() && assigneeOpt.isPresent()) {
            Ticket ticket = ticketOpt.get();
            User assignee = assigneeOpt.get();
            
            if (assignee.getRole() == Role.SUPPORT_AGENT || assignee.getRole() == Role.ADMIN) {
                ticket.setAssignee(assignee);
                if (ticket.getStatus() == TicketStatus.OPEN) {
                    ticket.setStatus(TicketStatus.IN_PROGRESS);
                }
                Ticket updatedTicket = ticketService.updateTicket(ticket);
                return ResponseEntity.ok(updatedTicket);
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        }
        
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable Long id, @Valid @RequestBody CommentRequest commentRequest, Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        Optional<Ticket> ticketOpt = ticketService.findById(id);
        
        if (ticketOpt.isPresent()) {
            Ticket ticket = ticketOpt.get();
            
            // Check if user has access to this ticket
            if (currentUser.getRole() == Role.ADMIN || 
                ticket.getOwner().getId().equals(currentUser.getId()) || 
                (ticket.getAssignee() != null && ticket.getAssignee().getId().equals(currentUser.getId()))) {
                
                Comment comment = commentService.createComment(commentRequest, ticket, currentUser);
                return ResponseEntity.ok(comment);
            } else {
                return ResponseEntity.status(403).body(null);
            }
        }
        
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<Comment>> getTicketComments(@PathVariable Long id, Authentication authentication) {
        User currentUser = (User) authentication.getPrincipal();
        Optional<Ticket> ticketOpt = ticketService.findById(id);
        
        if (ticketOpt.isPresent()) {
            Ticket ticket = ticketOpt.get();
            
            // Check if user has access to this ticket
            if (currentUser.getRole() == Role.ADMIN || 
                ticket.getOwner().getId().equals(currentUser.getId()) || 
                (ticket.getAssignee() != null && ticket.getAssignee().getId().equals(currentUser.getId()))) {
                
                List<Comment> comments = commentService.getCommentsByTicket(ticket);
                return ResponseEntity.ok(comments);
            } else {
                return ResponseEntity.status(403).body(null);
            }
        }
        
        return ResponseEntity.notFound().build();
    }
}
