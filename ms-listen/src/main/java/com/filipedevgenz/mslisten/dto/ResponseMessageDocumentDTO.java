package com.filipedevgenz.mslisten.dto;

public record ResponseMessageDocumentDTO(
        String messaging_product,//whatsapp
        String to,
        String type,//document
        Document document
) {
        public record Document(String link, String filename) {}
    }


