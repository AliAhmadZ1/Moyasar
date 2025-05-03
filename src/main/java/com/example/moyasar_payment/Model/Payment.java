package com.example.moyasar_payment.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private String name;
    private String number;
    private String cvc;
    private String month;
    private String year;
    private int amount;
    private String currency;
    private String description;
    private String callbackUrl;

}
