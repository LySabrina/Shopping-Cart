# Todos

## Stripe Implementation:

**Cancel Payment Intent**

- Fix cancelling payment intent

  ~~- Test cancelling by onClick button to call the cancelOrder()~~

  - Cancel the PaymentIntent after a period of time (ex. User abandons the Checkout page)
    ~~- Redirect the user after clicking on the cancel payment button~~

**Loading Payment**

- Add a loader spinner as payment is processing

**Checkout Page**

- Add a new page for Checkout such that the navbar only contains the logo and link to go back to the cart page
  - When going back to the Checkout or Home Page
    - Ensure that it will cancel the PaymentIntent
- Find some way if PaymentIntent transaction need to be manually cancelled or timeout
  - most likely manually canceled since, we create a client secret which corresponds to their transaction

**Price**

- Fix the pricing of Stripe
  ~~- Clear the cart after successfull Stripe Payment~~

# Issues + Ressolution

#### ShopContext Disappears when navigation to "/checkout"

- Put <ShopContext> as a parent inside <Checkout>
- <Checkout> is on its own page, so it was missing the <ShopContext>
- <App> already had <ShopContext> so when we switched, the ShopContext disappears with it
- Hence, we needed to add the shopcontext to the Checkout, which is consider its own page now - separate from App

#### isLoading and PaymentElement

If isLoading is true, then the PaymentForm disappears from the HTML document and Stripe can not process the payment.

```jsx
return isLoading ? (
  <p>Loading...</p>
) : (
  <>
    <PaymentForm
      options={options}
      submitFunc={handleOnSubmit}
      disableSubmit={disableSubmit}
    />

    <button type="button" onClick={cancelPayment}>
      Cancel Payment
    </button>
  </>
);
```

# Current Known Issues

## Stripe implementation

### Confirm Payment

CheckoutForm.jsx had the following code;

```jsx
return;
<form onSubmit={handleOnSubmit}>
  <PaymentForm options={options} />;
</form>;
```

Inside PaymentForm.jsx:

```jsx
function PaymentForm() {
  return (
    <form>
      <AddressElement options={{ mode: "shipping" }} />
      <PaymentElement />
      <button>Submit</button>
    </form>
  );
}
export default PaymentForm;
```

So the issue is that I should get rid of the <form> inside the CheckoutForm.jsx and instead pass the handleOnSubmit() function as a prop to the <form> element inside the PaymentForm.jsx. If we don't, once we click on submit, the <form> inside PaymentForm.jsx will refresh and will not confirm the payment to Stripe.

Resolve:

- pass the handleOnSubmit() function as a prop and pass to the <form> inside PaymentForm.jsx
- remove the <form> parent element inside CheckoutForm.jsx and just leave <PaymentForm> component

# Resources

[Spring Security + React + Basic Auth](https://stackoverflow.com/questions/61441508/spring-security-with-rest-api-with-react)
