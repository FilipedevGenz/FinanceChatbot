package com.filipedevgenz.mslisten.dto;

import java.util.List;

public record RecivedMessageDTO(
        List<Entry> entry
) {
    public record Entry(
            List<Change> changes
    ) {}

    public record Change(
            Value value
    ) {}

    public record Value(
            List<Message> messages
    ) {}

    public record Message(
            String from,
            String id,
            String timestamp,
            Text text
    ) {}

    public record Text(
            String body
    ) {}
}
