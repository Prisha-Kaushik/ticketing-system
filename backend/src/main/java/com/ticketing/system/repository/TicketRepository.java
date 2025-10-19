package com.ticketing.system.repository;

import com.ticketing.system.entity.Priority;
import com.ticketing.system.entity.Ticket;
import com.ticketing.system.entity.TicketStatus;
import com.ticketing.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findByOwner(User owner);
    List<Ticket> findByAssignee(User assignee);
    List<Ticket> findByStatus(TicketStatus status);
    List<Ticket> findByPriority(Priority priority);
    
    @Query("SELECT t FROM Ticket t WHERE t.owner = :owner OR t.assignee = :assignee")
    List<Ticket> findByOwnerOrAssignee(@Param("owner") User owner, @Param("assignee") User assignee);
    
    @Query("SELECT t FROM Ticket t WHERE t.status = :status AND (t.owner = :user OR t.assignee = :user)")
    List<Ticket> findByStatusAndUser(@Param("status") TicketStatus status, @Param("user") User user);
    
    @Query("SELECT t FROM Ticket t WHERE t.priority = :priority AND (t.owner = :user OR t.assignee = :user)")
    List<Ticket> findByPriorityAndUser(@Param("priority") Priority priority, @Param("user") User user);
}
