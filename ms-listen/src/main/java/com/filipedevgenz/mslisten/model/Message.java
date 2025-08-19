package com.filipedevgenz.mslisten.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    String from;
    String id;
    String timestamp;
    String text;
}
