package com.fpt.bbusbe.mqtt.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpt.bbusbe.mqtt.model.BasicMessage;
import com.fpt.bbusbe.mqtt.model.HeartbeatMessage;
import org.springframework.stereotype.Service;

@Service
public class MqttMessageService {

    private final ObjectMapper objectMapper;

    public MqttMessageService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    // Method to process message based on topic
    public void processMessage(String topic, String payload) {
        try {
            if (topic.startsWith("mqtt/face/basic")) {
                // Process basic message
                BasicMessage basicMessage = objectMapper.readValue(payload, BasicMessage.class);
                handleBasicMessage(basicMessage);
            } else if (topic.startsWith("mqtt/face/heartbeat")) {
                // Process heartbeat message
                HeartbeatMessage heartbeatMessage = objectMapper.readValue(payload, HeartbeatMessage.class);
                handleHeartbeatMessage(heartbeatMessage);
            } else {
                System.out.println("Unknown topic: " + topic);
            }
        } catch (Exception e) {
            System.out.println("Error processing message: " + e.getMessage());
        }
    }

    // Handle basic message
    private void handleBasicMessage(BasicMessage message) {
        System.out.println("Processing Basic Message...");
        System.out.println("Operator: " + message.getOperator());
        System.out.println("Face ID: " + message.getInfo().getFacesluiceId());
        System.out.println("Username: " + message.getInfo().getUsername());
        System.out.println("Time: " + message.getInfo().getTime());
        System.out.println("IP: " + message.getInfo().getIp());
        System.out.println("Face Name: " + message.getInfo().getFacesname());

        // Add your custom logic here (e.g., save to a database, send notifications, etc.)
    }

    // Handle heartbeat message
    private void handleHeartbeatMessage(HeartbeatMessage message) {
        System.out.println("Processing Heartbeat Message...");
        System.out.println("Operator: " + message.getOperator());
        System.out.println("Face ID: " + message.getInfo().getFacesluiceId());
        System.out.println("Time: " + message.getInfo().getTime());

        // Add your custom logic here (e.g., update device status, log heartbeat, etc.)
    }
}
