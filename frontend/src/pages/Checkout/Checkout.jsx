import { Elements, useElements, useStripe } from "@stripe/react-stripe-js";
import { loadStripe } from "@stripe/stripe-js";
import { useLocation } from "react-router-dom";
import { ShopProvider, useShopContext } from "../../contexts/ShopProvider.jsx";

import { useState } from "react";
import CheckoutForm from "./CheckoutForm.jsx";
import logo from "../../assets/images/logo.svg";
import cart from "../../assets/images/icon-cart.svg";
import navbarStyle from "../../components/Navbar/Navbar.module.scss";
import style from "./Checkout.module.scss";
import CheckoutItem from "./CheckoutItem.jsx";
const stripePromise = loadStripe(import.meta.env.VITE_PUBLIC_KEY);

function Checkout() {
  const cartItems = useShopContext() ?? [];
  console.log("cart Item -", cartItems);

  const clientSecret = useLocation().state?.clientSecret;
  const options = {
    clientSecret: clientSecret,
  };

  console.log("Client Secret - " + clientSecret);

  async function cancelPayment() {
    const result = await fetch(
      `${import.meta.env.VITE_SERVER_BASE_URL}/cancel-order`,
      {
        method: "PATCH",
        body: JSON.stringify(clientSecret),
      }
    );
    const json = await result.json();
    console.log(json);
  }

  return (
    <>
      <ShopProvider>
        <header className={navbarStyle.header}>
          <div>
            <img src={logo} alt="Logo" />
          </div>
          <div className={navbarStyle.cart}>
            <img src={cart} alt="Cart" />
          </div>
        </header>

        <div className={style.checkout__body}>
          <div className={style["checkout__cart-items"]}>
            <ul>
              {cartItems.map((elem) => (
                <CheckoutItem key={elem.id} {...elem} />
              ))}
            </ul>
          </div>
          {clientSecret && (
            <Elements
              options={options}
              stripe={stripePromise}
              className={style.checkout__form}
            >
              <CheckoutForm />
            </Elements>
          )}
        </div>
      </ShopProvider>
    </>
  );
}
export default Checkout;
