import { useState } from "react";
import PaymentForm from "../Stripe/PaymentForm.jsx";
import { AddressElement, useElements, useStripe } from "@stripe/react-stripe-js";

function CheckoutForm() {
  const step = useState(1);
  const options = {
    layout: {
      type: "tabs",
      defaultCollapsed: false,
    },
  };
  const stripe = useStripe();
  const elements = useElements();
const [message, setMessage] = useState(null);
const [isLoading, setIsLoading] = useState(false);
  
  const handleOnSubmit = async (e) => {
    e.preventDefault();
    if (!stripe || !elements) {
      return;
    }
    setIsLoading(true);
    const { error } = await stripe.confirmPayment({
      elements,
      confirmParams: {
        return_url: "http://localhost:5173",
      },
    });

    if (error.type === "card__error" || error.type === "validation__error") {
      setMessage(error.message);
    } else {
      setMessage("Unexpected error occurred");
    }
    setIsLoading(false);
  };

  return (
    <form >
      <PaymentForm options={options} />
    </form>
  );
}
export default CheckoutForm;
