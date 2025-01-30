import { Elements, useElements, useStripe } from "@stripe/react-stripe-js";
import { loadStripe } from "@stripe/stripe-js";
import { useLocation } from "react-router-dom";
import { ShopProvider, useShopContext } from "../../contexts/ShopProvider.jsx";
import { useNavigate } from "react-router-dom";
import CheckoutForm from "./CheckoutForm.jsx";
import logo from "../../assets/images/logo.svg";
import cart from "../../assets/images/icon-cart.svg";
import navbarStyle from "../../components/Navbar/Navbar.module.scss";
import style from "./Checkout.module.scss";
import CheckoutItem from "./CheckoutItem.jsx";
const stripePromise = loadStripe(import.meta.env.VITE_PUBLIC_KEY);

function Checkout() {
  const cartItems = useShopContext();
  const navigate = useNavigate();
  console.log("Checkout cart items -", cartItems);

  const clientSecret = useLocation().state?.clientSecret;
  const id = useLocation().state?.id;
  const options = {
    clientSecret: clientSecret,
  };

  console.log("Client Secret - " + clientSecret);
  console.log("ID" + id);

  async function cancelPayment() {
    const result = await fetch(
      `http://localhost:8080/api/checkout/cancel-order`,
      {
        method: "post",
        body: id,
      }
    );

    console.log(result);

    const response = await result.text();
    console.log(response);

    navigate("/cart", { replace: true });

    // return
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
              <CheckoutForm cancelPayment={cancelPayment} />
            </Elements>
          )}
        </div>
      </ShopProvider>
    </>
  );
}
export default Checkout;
