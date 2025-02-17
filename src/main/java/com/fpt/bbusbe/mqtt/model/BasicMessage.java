package com.fpt.bbusbe.mqtt.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasicMessage {
    private String operator;
    private Info info;

    // Getters and setters

    @Getter
    @Setter
    public static class Info {
        private String facesluiceId;
        private String username;
        private String time;
        private String ip;
        private String facesname;

        // Getters and setters
    }
}

