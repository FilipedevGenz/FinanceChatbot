package com.filipedevgenz.mslisten.service;
import com.filipedevgenz.mslisten.model.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
//TODO implementar requisicao para o ms-crud

@Service
public class MessageService {
    @Value("${token.acess}")
    private String ACCESS_TOKEN;

    final RestClient restClient = RestClient.create();

    public Data messageTreatment(String text , String timestamp) {
        String[] values = text.split(" ");

        return new Data(values[0],values[1],timestamp);
    }

    private void saveData(Data data) {
        restClient.p
    }

    private Double stringToDouble(String string) {
        return Double.parseDouble(string.replace(",","."));
    }
}
