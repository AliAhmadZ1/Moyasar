package com.example.moyasar_payment.Service;

import com.example.moyasar_payment.Model.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {

    @Value("${moyasar.api.key}")
    private String apiKey;

    private static final String MOYASAR_API_URL = "https://api.moyasar.com/v1/payments/";

    public ResponseEntity<?> processPayment(Payment payment){

        int amount = payment.getAmount()*100;
        String stAmount = String.valueOf(amount);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth(apiKey, "");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        params.add("amount", stAmount); // in halalas = 100 SAR
        params.add("currency", payment.getCurrency());
        params.add("description", payment.getDescription());
        params.add("callback_url", payment.getCallbackUrl());
        params.add("source[type]", "card"); // or "sadad", "mada", "applepay" "creditcard"
        params.add("source[name]", payment.getName());
        params.add("source[number]", payment.getNumber());
        params.add("source[month]", payment.getMonth());
        params.add("source[year]", payment.getYear());
        params.add("source[cvc]", payment.getCvc());

        String url = "https://api.moyasar.com/v1/payments";
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);
        ResponseEntity response = restTemplate.postForEntity(url, request, String.class);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }


    public String getPaymentStatus(String payment_id){

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(apiKey,"");
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(MOYASAR_API_URL + payment_id,
                HttpMethod.GET,entity, String.class);

        return response.getBody();
    }

}
