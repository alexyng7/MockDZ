package com.example.newMock2.Controller;

import com.example.newMock2.Model.RequestDTO;
import com.example.newMock2.Model.ResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController

public class MainController {
    private Logger log = LoggerFactory.getLogger(MainController.class);
    ObjectMapper mapper = new ObjectMapper();
    @PostMapping(
            value = "/info/postBalances",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Object postBalances(@RequestBody RequestDTO requestDTO) {
        try{
            String clintId = requestDTO.getClientId();
            char firstDigit = clintId.charAt(0);
            BigDecimal maxLimit;
            String currency = null;
            BigDecimal balance;

            if(firstDigit == '8'){
                maxLimit = new BigDecimal(2000);
                currency = "US";
                balance = new BigDecimal(Math.random()*Integer.parseInt(maxLimit.toString()));

            } else if (firstDigit == '9'){
                maxLimit = new BigDecimal(1000);
                currency = "EU";
                balance = new BigDecimal(Math.random()*Integer.parseInt(maxLimit.toString()));
            } else {
                maxLimit = new BigDecimal(10000);
                currency = "RU";
                balance = new BigDecimal(Math.random()*Integer.parseInt(maxLimit.toString()));
            }
            ResponseDTO responseDTO = new ResponseDTO();
            responseDTO.setRqUID(requestDTO.getRqUID());
            responseDTO.setClientId(requestDTO.getClientId());
            responseDTO.setAccount(requestDTO.getAccount());
            responseDTO.setCurrency(currency);
            responseDTO.setBalance(balance.setScale(2, BigDecimal.ROUND_UP));
            responseDTO.setMaxLimit((maxLimit.setScale(2, BigDecimal.ROUND_UP)).toString());

            log.error("******** RequestDTO ********" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO));
            log.error("******** ResponsDTO ********" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO));

            return responseDTO;

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
