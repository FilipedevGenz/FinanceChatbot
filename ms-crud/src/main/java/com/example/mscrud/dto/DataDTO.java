package com.example.mscrud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DataDTO {
    String text;
    String value;
    String purchaseMethod;
    String Time;
    String from;

    public DataDTO(String text, String value, String time, String from) {
        this.text = text;
        this.value = value;
        this.Time = time;
        this.from = from;
        purchaseMethod = null;
    }
}

