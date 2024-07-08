import { AddressElement, PaymentElement } from "@stripe/react-stripe-js";
function PaymentForm() {
  return (
    <form>
      <AddressElement options={{mode:'shipping'}}/>
      <PaymentElement />
      <button>Submit</button>
    </form>
  );
}
export default PaymentForm;
