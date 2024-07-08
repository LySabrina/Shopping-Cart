package com.example.demo.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeService {

    @Value("${API_KEY}")
    private String API_KEY;

    public StripeService(){
        Stripe.apiKey = API_KEY;
    }

    /**
     * Creates a PaymentIntent - an intent to make a purchase (PaymentIntent != Payment)
     * We are thinking of making a payment, but we customer has not commited to it
     * @param amount - the purchased amount
     * @return clientSecret - the client secret that is used for their session checkout
     * @throws StripeException
     */
    private String makePayment(long amount) throws StripeException {
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amount)
                .setCurrency("usd")
                .setAutomaticPaymentMethods
                        (
                                PaymentIntentCreateParams.AutomaticPaymentMethods.builder().setEnabled
                                        (true).build()
                        )
                .build();
        PaymentIntent paymentIntent = PaymentIntent.create(params);
        return paymentIntent.getClientSecret();
    }

    private String makeTestPayment() throws StripeException {
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(1000L)
                .setCurrency("usd")
                .setAutomaticPaymentMethods
                        (
                                PaymentIntentCreateParams.AutomaticPaymentMethods.builder().setEnabled
                                        (true).build()
                        )
                .build();
        PaymentIntent paymentIntent = PaymentIntent.create(params);
        return paymentIntent.getClientSecret();
    }
}
