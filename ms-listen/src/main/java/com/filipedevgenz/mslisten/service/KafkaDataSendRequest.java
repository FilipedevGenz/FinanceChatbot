package com.filipedevgenz.mslisten.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filipedevgenz.mslisten.util.RequestKafka;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaDataSendRequest extends RequestKafka<String, Object> {

    @Value("${spring.kafka.template.default-topic}")
    private String topic;

    public KafkaDataSendRequest(KafkaTemplate<String, Object> kafkaTemplate) {
        super(kafkaTemplate);
    }

    @Override
    public void SendRequest(Object toSend) {
            kafkaTemplate.send(topic, toSend);
    }
}

