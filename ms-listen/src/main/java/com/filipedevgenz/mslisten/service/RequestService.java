package com.filipedevgenz.mslisten.service;
import com.filipedevgenz.mslisten.dto.ResponseMessageDocumentDTO;
import com.filipedevgenz.mslisten.dto.ResponseMessageTextDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class RequestService {

    @Value("${token.acess}")
    private String ACCESS_TOKEN;

    @Value("${routes.whatsappPostTestRoute}")
    private String WHATSAPP_API_URL;

    final RestClient restClient = RestClient.create();

    private ResponseEntity<ResponseMessageTextDTO> sendMessageToWhatsapp(String text,String number) {
        ResponseMessageTextDTO responseDTO = new ResponseMessageTextDTO("Whatsapp"
                , number
                ,"text"
                , ResponseMessageTextDTO.fromText(text));

        ResponseEntity<String> request = restClient.post()
                .uri(WHATSAPP_API_URL)
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseDTO)
                .retrieve()
                .toEntity(String.class);

        return ResponseEntity.status(request.getStatusCode()).body(responseDTO);
    }

    private ResponseEntity<ResponseMessageDocumentDTO> sendDocumentToWhatsapp
            (String fileUrl, String filename,String number) {
        ResponseMessageDocumentDTO responseDTO = new ResponseMessageDocumentDTO(
                "whatsapp",
                number,
                "document",
                new ResponseMessageDocumentDTO.Document(fileUrl, filename)
        );

        ResponseEntity<String> request = restClient.post()
                .uri(WHATSAPP_API_URL)
                .header("Authorization", "Bearer " + ACCESS_TOKEN)
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseDTO)
                .retrieve()
                .toEntity(String.class);

        return ResponseEntity.status(request.getStatusCode()).body(responseDTO);
    }

    public void sendDocumentWithRetry(String number,String fileUrl, String filename) {
        HttpStatusCode status = sendDocumentToWhatsapp(fileUrl,filename,number)
                .getStatusCode();
        int counter = 0;
        while (!status.is2xxSuccessful() && counter < 3) {
            status = sendDocumentToWhatsapp(fileUrl,filename,number)
                    .getStatusCode();
            counter++;
        }
    }

    @Async("ioBound")
    public void sendMessageWithRetry(String text,String number) {
        HttpStatusCode status = sendMessageToWhatsapp(text,number)
                .getStatusCode();
        int counter = 0;
        while (!status.is2xxSuccessful()|| counter < 3) {
            status = sendMessageToWhatsapp(text,number)
                    .getStatusCode();
            counter++;
        }
    }

}
