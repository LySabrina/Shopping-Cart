package com.example.demo.stripe;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeService {

    //Failing to make bean - possible due to the @Value
    @Value("${API_KEY}")
    private String API_KEY;


    /**
     * We can not do this because API_KEY will be null
     * The reason is that we have a field dependency injection that needs to be complete
     * So before we can get out API_KEY, StripeSerice is made and the construcotr is called where the API_KEY will be null
     * PREV CODE:
     * public StripeService(){
     *     Stripe.apiKey = API_KEY
     * }
     * API_Key = Null, field dependency injection will be injected after instance of class (compared to constructor injection which
     * will have all the necessary dependency made before creating an instance of the class)
     * Thus, we need to use @PostConstruct - which runs the function it is annotated at and ensures all dependency are injected ( useful for field injection)
     */

    @PostConstruct
    public void init(){
        Stripe.apiKey = API_KEY;
    }

    /**
     * Creates a PaymentIntent - an intent to make a purchase (PaymentIntent != Payment)
     * We are thinking of making a payment, but we customer has not commited to it
     * @param amount - the purchased amount
     * @return clientSecret - the client secret that is used for their session checkout
     * @throws StripeException
     */
    public String makePayment(long amount) throws StripeException {
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

    // WORK ON TESTING IT W/POSTMAN
    // WORK ON PASSING CLIENT SECRING CLIENT SECRET TO FRONTEND TO USE TO GENERATE THE STRIPE CHECKOUT PAGE
    // FIGURE OUT HOW TO LOG THE TRANSACTION INTO DB

    public String makeTestPayment() throws StripeException {
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
