package com.fpt.bbusbe.model.entity.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class CameraKey implements Serializable {

    private String facesluiceId; // Unique identifier for the device (common in both Heartbeat and Basic messages)

    private String operator; // "HeartBeat" or "Online"

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CameraKey that = (CameraKey) o;
        return facesluiceId.equals(that.facesluiceId) && operator.equals(that.operator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facesluiceId, operator);
    }
}
