import { useState } from "react";
import PaymentForm from "./PaymentForm.jsx";
import { useElements, useStripe } from "@stripe/react-stripe-js";
import { useShopDispatch } from "../../contexts/ShopProvider.jsx";

function CheckoutForm() {
  const options = {
    layout: {
      type: "tabs",
      defaultCollapsed: false,
    },
  };
  const stripe = useStripe();
  const elements = useElements();

  const dispatch = useShopDispatch();
  console.log("dispatch", dispatch);
  const [message, setMessage] = useState(null);
  const [isLoading, setIsLoading] = useState(false);
  const [disableSubmit, setDisableSubmit] = useState(false);

  const handleOnSubmit = async () => {
    if (!stripe || !elements) {
      return;
    }
    setIsLoading(true);
    console.log("Attempting Processing Payment...");

    const { error: submitError } = await elements.submit();

    if (submitError?.message) {
      setIsLoading(false);
      setMessage(submitError.message);
      return;
    }
    dispatch({
      type: "clear",
      item: [],
    });
    setDisableSubmit(true);

    const { error } = await stripe.confirmPayment({
      elements,
      confirmParams: {
        return_url: "http://localhost:5173/success",
      },
    });

    if (error.type === "card__error" || error.type === "validation__error") {
      setMessage(error.message);
      console.log(error.message);
    } else {
      setMessage("Unexpected error occurred");
      console.log("unexpeted error");
    }
    setDisableSubmit(true);
    setIsLoading(false);
  };

  return isLoading ? (
    <p>Loading...</p>
  ) : (
    <>
      <PaymentForm
        options={options}
        submitFunc={handleOnSubmit}
        disableSubmit={disableSubmit}
      />

      <button type="button">Cancel Payment</button>
    </>
  );
}
export default CheckoutForm;