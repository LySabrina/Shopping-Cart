import { Outlet } from "react-router-dom";
import Navbar from "../../components/Navbar";
import { createContext, useState } from "react";
import placeholder from "../../assets/images/image-product-1.jpg";

import { Elements } from "@stripe/react-stripe-js";
import { loadStripe } from "@stripe/stripe-js";
import { ShopProvider } from "../../contexts/ShopProvider.jsx";

function App() {
  const stripePromise = loadStripe(import.meta.env.VITE_PUBLIC_KEY);

  return (
    <>
      <Elements
        stripe={stripePromise}
        options={{ mode: "payment", currency: "usd", amount: 10000 }}
      >
        <ShopProvider>
          <Navbar />
          <main>
            <Outlet />
          </main>
        </ShopProvider>
      </Elements>
    </>
  );
}
export default App;
