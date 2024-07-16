import { Outlet } from "react-router-dom";
import Navbar from "../../components/Navbar";
import { createContext, useState } from "react";
import placeholder from "../../assets/images/image-product-1.jpg";

import { Elements } from "@stripe/react-stripe-js";
import { loadStripe } from "@stripe/stripe-js";
import { ShopProvider } from "../../contexts/ShopProvider.jsx";

function App() {
  return (
    <>
      <ShopProvider>
        <Navbar />
        <main>
          <Outlet />
        </main>
      </ShopProvider>
    </>
  );
}
export default App;
