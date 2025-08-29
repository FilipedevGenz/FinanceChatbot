package com.example.mscrud.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collation = "data_entity")
public class Data {
    @Id
    String id;
    String text;
    String value;
    String purchaseMethod;
    String Time;
    String from;

    public Data(String text, String value, String time, String from) {
        this.text = text;
        this.value = value;
        this.Time = time;
        this.from = from;
        purchaseMethod = null;
    }

    public Data(String text, String value, String time, String purchaseMethod, String from) {
        this.text = text;
        this.value = value;
        this.Time = time;
        this.from = from;
        this.purchaseMethod =  purchaseMethod;
    }
}

