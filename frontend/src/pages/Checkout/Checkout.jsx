import { Elements, useElements, useStripe } from "@stripe/react-stripe-js";
import { loadStripe } from "@stripe/stripe-js";
import { useLocation } from "react-router-dom";
import PaymentForm from "../Stripe/PaymentForm.jsx";
import { useShopContext } from "../../contexts/ShopProvider.jsx";
import CartItem from "../../components/Cart/CartItem.jsx";
import { useEffect, useState } from "react";
import CheckoutForm from "./CheckoutForm.jsx";

const stripePromise = loadStripe(import.meta.env.VITE_PUBLIC_KEY);

function Checkout() {
  const cartItems = useShopContext();
  // const stripe = useStripe();
  // const elements = useElements();

  const [message, setMessage] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  const clientSecret = useLocation().state.clientSecret;
  const options = {
    clientSecret: clientSecret,
  };

  useEffect(() => {
    if (!clientSecret) {
      return;
    }
  }, []);

  //create mult-step form
  // const handleOnSubmit = async (e) => {
  //   e.preventDefault();
  //   if(!stripe || !elements){
  //     return;
  //   }
  //   setIsLoading(true);
  //   const { error } = await stripe.confirmPayment({
  //     elements,
  //     confirmParams:{
  //       return_url: "http://localhost:5173"
  //     }
  //   });

  //   if(error.type === "card__error" || error.type==="validation__error"){
  //     setMessage(error.message);
  //   }
  //   else{
  //     setMessage("Unexpected error occurred");
  //   }
  //   setIsLoading(false);

  // };
  return (
    <>
      <div></div>
      {clientSecret && (
        <Elements options={options} stripe={stripePromise}>
          <CheckoutForm />
        </Elements>
      )}
      
    </>
  );
}
export default Checkout;
