package com.fpt.bbusbe.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tbl_camera")
public class Camera extends AbstractEntity<Integer> {

    @Column(name = "facesluice_id", nullable = false)
    private String facesluiceId; // Unique identifier for the device (common in both Heartbeat and Basic messages)

    @Column(name = "operator", nullable = false)
    private String operator; // "HeartBeat" or "Online"

    @Column(name = "time", nullable = false)
    private LocalDateTime time;
}
