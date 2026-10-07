package com.example.LoanMatrix.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NotificationId")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustomerId", nullable = false)
    private Customer customer;

    @Column(name = "Message", nullable = false)
    private String message;

    @Column(name = "IsRead")
    private Boolean read;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;
}
