package com.example.mscrud.util;

import com.example.mscrud.dto.DataDTO;
import com.example.mscrud.model.Data;

public class Mapper {

    public static Data MapToData(DataDTO dataDTO){
        Data data;
        if(dataDTO.getPurchaseMethod() == null){
            return data = new Data(dataDTO.getText(), dataDTO.getValue(), dataDTO.getTime(), dataDTO.getFrom());
        }
        return data = new Data(dataDTO.getText(), dataDTO.getValue(), dataDTO.getTime()
                , dataDTO.getPurchaseMethod(), dataDTO.getFrom());
    }
}
