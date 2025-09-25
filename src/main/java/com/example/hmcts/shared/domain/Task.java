package com.example.hmcts.shared.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @Column(name = "due_date", nullable = false)
    private LocalDateTime dueDate;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updatedAt;

    /**
     * A check if the task is overdue
     */
    public boolean isOverdue(){
        return dueDate != null && LocalDateTime.now().isAfter(dueDate) && status != TaskStatus.COMPLETED;
    }

    public boolean isDueSoon(){
        if (dueDate == null || status == TaskStatus.COMPLETED){
            return false;
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime tomorrow = now.plusDays(1);
        return dueDate.isAfter(now) && dueDate.isBefore(tomorrow);
    }



}
