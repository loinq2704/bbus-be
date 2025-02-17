package com.fpt.bbusbe.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_camera")
public class Camera{
    @Id
    @Column(name = "facesluice", nullable = false)
    private String facesluice;

    @Column(name = "time_basic")
    private LocalDateTime timeBasic;

    @Column(name = "time_heartbeat")
    private LocalDateTime timeHeartbeat;

    @Column(name = "created_at", length = 255)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createdAt;
}
