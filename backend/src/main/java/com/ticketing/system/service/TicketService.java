package com.ticketing.system.service;

import com.ticketing.system.dto.TicketRequest;
import com.ticketing.system.entity.*;
import com.ticketing.system.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    @Autowired
    TicketRepository ticketRepository;

    public Ticket createTicket(TicketRequest ticketRequest, User owner) {
        Ticket ticket = new Ticket(
                ticketRequest.getSubject(),
                ticketRequest.getDescription(),
                ticketRequest.getPriority(),
                owner
        );
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public List<Ticket> getTicketsByOwner(User owner) {
        return ticketRepository.findByOwner(owner);
    }

    public List<Ticket> getTicketsByAssignee(User assignee) {
        return ticketRepository.findByAssignee(assignee);
    }

    public List<Ticket> getTicketsByOwnerOrAssignee(User user) {
        return ticketRepository.findByOwnerOrAssignee(user, user);
    }

    public List<Ticket> getTicketsByStatus(TicketStatus status) {
        return ticketRepository.findByStatus(status);
    }

    public List<Ticket> getTicketsByPriority(Priority priority) {
        return ticketRepository.findByPriority(priority);
    }

    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id);
    }

    public Ticket updateTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }

    public Ticket updateTicketStatus(Long ticketId, TicketStatus status) {
        Optional<Ticket> ticketOpt = ticketRepository.findById(ticketId);
        if (ticketOpt.isPresent()) {
            Ticket ticket = ticketOpt.get();
            ticket.setStatus(status);
            return ticketRepository.save(ticket);
        }
        return null;
    }

    public Ticket assignTicket(Long ticketId, User assignee) {
        Optional<Ticket> ticketOpt = ticketRepository.findById(ticketId);
        if (ticketOpt.isPresent()) {
            Ticket ticket = ticketOpt.get();
            ticket.setAssignee(assignee);
            if (ticket.getStatus() == TicketStatus.OPEN) {
                ticket.setStatus(TicketStatus.IN_PROGRESS);
            }
            return ticketRepository.save(ticket);
        }
        return null;
    }
}
