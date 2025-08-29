package com.filipedevgenz.mslisten.dto;

import com.filipedevgenz.mslisten.model.Data;

public record SecureDataDTO(Data data, String tokenJwt, String hashedNumber) {}
