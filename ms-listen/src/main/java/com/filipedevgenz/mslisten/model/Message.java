package com.filipedevgenz.mslisten.model;

import com.filipedevgenz.mslisten.dto.RecivedMessageDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message {
    String from;
    String id;
    String timestamp;
    String text;
}
