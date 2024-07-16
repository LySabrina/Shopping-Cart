import { Elements } from "@stripe/react-stripe-js";
import { loadStripe } from "@stripe/stripe-js";
import PaymentForm from "./PaymentForm.jsx";

const stripePromise = loadStripe(import.meta.env.VITE_PUBLIC_KEY);

function StripeCheckout() {

  // need to get the client key from the backend to render the checkout
  return (
    <>
      <Elements
        stripe={stripePromise}
        options={{ mode: "payment", currency: "usd", amount: 10000 }}
      >
        <PaymentForm />
      </Elements>
    </>
  );
}

export default StripeCheckout;
