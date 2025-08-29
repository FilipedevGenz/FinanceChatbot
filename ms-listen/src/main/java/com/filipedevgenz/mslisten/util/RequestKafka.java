package com.filipedevgenz.mslisten.util;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;

@AllArgsConstructor
public abstract class RequestKafka<K,V> {
    protected KafkaTemplate<K, V> kafkaTemplate;

    public abstract void SendRequest(V toSend);
}
