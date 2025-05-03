package com.example.moyasar_payment.Controller;

import com.example.moyasar_payment.Model.Payment;
import com.example.moyasar_payment.Service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<?> processPayment(@RequestBody@Valid Payment payment) {
        return ResponseEntity.status(200).body(paymentService.processPayment(payment));
    }

    @GetMapping("/get-status/{id}")
    public ResponseEntity getPaymentStatus(@PathVariable String id) {
        return ResponseEntity.ok().body(paymentService.getPaymentStatus(id));
    }

    @GetMapping("/callback")
    public ResponseEntity callbackUrl(){
        return ResponseEntity.status(200).body("Paid !!");
    }

}
