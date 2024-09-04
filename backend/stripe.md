# Stripe

All about Stripe API integration

# [PaymentIntents & Payment Methods API](https://dev.to/stripe/fundamentals-of-the-paymentintents-and-paymentmethods-apis-3646)

You will need both PaymentIntent & PaymentMethod to work together

PaymentMethod = represent payment details (ex. credit card - Citibank, Wells Fargo)
PaymentIntent represent the transaction itself

Hence, we need both to work together

## PaymentMethods API

PaymentMethods API lets you accept a variety of payment method:

- credit card
- bank account
- vouchers

So PaymentMethod represents these different payment methods

Where is PaymentMethods API work?

1. Customer enters a payment detail on a website
2. Payment details sent to Stripe
3. Stripe API creats a PaymentMethod from the payment details. From the PaymentMethod object, create the PaymentIntent object.
4. Now PaymentIntent object can now be used to complete transaction

## PaymentIntents API

PaymentIntent represents the transaction

- amount
- currency
- payment flow

PaymentIntent can be used to authorize the payment with the card on bank networks

## One-Time Payment Process

Resources:

- [Create PaymentIntent](https://www.youtube.com/watch?v=-uyXCnPXegE)

1. Creating the PaymentIntent on the server side
2. Confirming the payment on the client using client secret

## Payment Life Cycle

Resource:

- [PaymentIntent LifeCycle](https://dev.to/stripe/the-paymentintents-lifecycle-4f5o)
  Lifecycle - list of states that a PaymentIntent can be and how it progresses through these states

Possible state of PaymentIntent:

- requires_payment_method = needs an attached PaymentMethod before progressing
- requires_confirmation = PaymentMethod attached but PaymentIntent needs to be confirmed
- requires_action = furter action (ex. 3DS)
- processing = payment is processing, will move to a next state w/o your input
- requires_capture =
- succeeded = payment successful
- canceled = payment cancelled

When a PaymentIntent is created, it has an initial state of requires_payment_method

- it indicates a PaymentMethod is needed before it can progress

## Stripe Checkout, Payment Link, Payment Element
How wil you present payments to your customer? 
- Use prebuilt payment form?
- build your own payment page? 
- Use API?
- use no-code solution? 

## Payment Links
- no code design + has an API
Payment Links creates a hyperlink to a Stripe hosted payment page

Designed to sell a specificed preconfig products or set of products 

If you want customers to choose and combine multiple products on hyour website in a cart, use Stripe Checkout.

Use Payment Links if:
- Businss w/o website or limited services

## Stripe Checkout
- prebuilt payment form that is embedded directly on your website or as a redirect to Stripe hosted page

Integrate Checkout using API
- dynamically pass datain the products you want to be shown in Checkout Session

- use of engineering effort and customizability

How to build a Stripe Checkout
- use Stripe Checkout API on server-side 
- Stripe will create short-lived links that redirect your user 
or 
- Config Checkout Sssion on server, mount payment form directly on website 

## Payment Element
Modular UI components for collecting payments

Payment Element is only used for payment method collection.

It can not do the following unlike Checkout and Payment links:
- order summary
- address and payment collection 

Can build Payment Element with other components (ex. Address Element)

How to use Payment Element
- npm install the Elements Library on the client-side 
