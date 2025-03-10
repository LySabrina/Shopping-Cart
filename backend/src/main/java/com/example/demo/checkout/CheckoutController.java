package com.example.demo.checkout;

import com.example.demo.exceptions.ProductNotFoundException;
import com.example.demo.product.ProductDTO;
import com.example.demo.product.ProductService;
import com.example.demo.stripe.StripeService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCancelParams;
import com.stripe.param.PaymentIntentCreateParams;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/checkout/")
@CrossOrigin("http://localhost:5173")
public class CheckoutController {

    private final StripeService stripeService;
    private final ProductService productService;

    public CheckoutController(StripeService stripeService, ProductService productService){
        this.stripeService = stripeService;
        this.productService =productService;
    }

    @GetMapping("/testPay")
    public ResponseEntity<String> makeTestPayment() throws StripeException {
        String clientSecret = stripeService.makeTestPayment();
        return ResponseEntity.status(200).body("Your client secret: " + clientSecret);
    }

    /**
     * Create a payment checkout for user
     * Gets all the desired prodcuts and handle calculating the cost on the backend side
     * @param productDTOList List of products
     * @return client secret to be used for Stripe checkout
     */
    @PostMapping("/create-payment-intent")
    public ResponseEntity<String> makePaymentIntent(@RequestBody List<ProductDTO> productDTOList) throws StripeException, ProductNotFoundException {
        // Now that I got the produuct id and each amount, how should go about generating the Stripe checkout?
        // https://stackoverflow.com/questions/75710891/do-you-have-to-store-your-products-in-stripe-to-process-payment
        // don't have to fetch price from Stripe, no need to store products in  Stripe but would be good to sync up with Stripe for consistency
        long totalPrice = 0;
        for(ProductDTO productDTO : productDTOList){
            long price = productService.findById(productDTO.getId()).getPrice();
            long total = price * productDTO.getAmount();
            totalPrice += total;
        }

        PaymentIntentCreateParams createIntent = PaymentIntentCreateParams.builder().setAmount(totalPrice).setCurrency("usd").build();
        PaymentIntent intent = PaymentIntent.create(createIntent);
        Gson gson = new Gson();
        JsonObject json = new JsonObject();
        json.addProperty("clientSecret", intent.getClientSecret());
        json.addProperty("id", intent.getId());

        System.out.println(json.toString());
//        return ResponseEntity.status(200).body(intent.getClientSecret());

        return ResponseEntity.status(200).body(json.toString());
        /**
         * TODO:
         * - Loop through RequestBody list of ProductDTO and fetch the price of each product id.
         * - Calculate the price * amount and add it to long total (why long? b/c Stripe uses pennies as the lowest amount currency
         * - Then configure PaymentIntent to setup the price and build() to get the client secret to send back to the client
          */

        //RequestBody fetch the Product Items to calculate the price of cart
    }

    @PostMapping("/cancel-order")
    public ResponseEntity<String> cancelPaymentIntent(@RequestBody String id) throws StripeException {
        PaymentIntent resource = PaymentIntent.retrieve(id);

        PaymentIntentCancelParams params = PaymentIntentCancelParams.builder().build();

        PaymentIntent paymentIntent = resource.cancel(params);

        return ResponseEntity.status(200).body("Successfully Cancelled Checkout Session");
    }

}
