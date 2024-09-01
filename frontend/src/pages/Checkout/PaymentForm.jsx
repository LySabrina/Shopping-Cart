import { AddressElement, PaymentElement } from "@stripe/react-stripe-js";
import { useState } from "react";
function PaymentForm({ submitFunc, disableSubmit }) {
  const handleOnSubmit = (e) => {
    e.preventDefault();
    submitFunc();
  };
  return (
    <form onSubmit={handleOnSubmit}>
      <AddressElement options={{ mode: "shipping" }} />
      <PaymentElement />
      <button disabled={disableSubmit}>Submit</button>
    </form>
  );
}
export default PaymentForm;
