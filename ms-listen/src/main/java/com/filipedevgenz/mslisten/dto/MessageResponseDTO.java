package com.filipedevgenz.mslisten.dto;

public record MessageResponseDTO (
        String messaging_product,
        String to,
        String type,
        Text text
) {
    public record Text(String body) {}
    public static Text fromText(String text) {
        return new Text(text);
    }
}

