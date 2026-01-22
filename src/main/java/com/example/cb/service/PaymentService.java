package com.example.cb.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private int counter = 0;

    @CircuitBreaker(
            name = "paymentServiceCB",
            fallbackMethod = "fallback"
    )
    public String processPayment() {

        counter++;
        System.out.println("Payment attempt: " + counter);

        // Simulate failures
        if (counter <= 4) {
            throw new RuntimeException("Payment gateway DOWN");
        }

        return "Payment processed successfully";
    }

    // Fallback method
    public String fallback(Throwable ex) {
        return "Fallback response: Payment service unavailable";
    }
}
