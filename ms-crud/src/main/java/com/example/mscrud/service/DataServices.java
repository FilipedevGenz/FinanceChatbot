package com.example.mscrud.service;

import com.example.mscrud.dto.DataDTO;
import com.example.mscrud.model.Data;
import com.example.mscrud.repository.DataRepository;
import com.example.mscrud.util.Mapper;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableKafka
public class DataServices {

    private DataRepository dataRepository;

    @KafkaListener(topics = "${spring.kafka.template.default-topic}"
            ,groupId ="${spring.kafka.template.group-id}",concurrency = "2")
    protected void saveDataByKafka(DataDTO dataDTO){
        dataRepository.save(Mapper.MapToData(dataDTO));
    }


    //deve ir para o DataService
    protected List<Data> findByFrom(String from){
        List<Data> listOfData = dataRepository
                .findByFrom(from);

        listOfData.sort((d1, d2) -> {
            Double v1 = d1.getValue() != null ? Double.parseDouble(d1.getValue()) : 0.0;
            Double v2 = d2.getValue() != null ? Double.parseDouble(d2.getValue()) : 0.0;
            return v2.compareTo(v1); // DESC
        });
        return listOfData;
    }
}
