import { useContext, useEffect, useState } from "react";
import style from "./Checkout.module.scss";

import CartItem from "../../components/Cart/CartItem.jsx";
import { PaymentElement } from "@stripe/react-stripe-js";
import { useShopContext } from "../../contexts/ShopProvider.jsx";

function Checkout() {
  const cartItems = useShopContext();
  const [total, setTotal] = useState(0);

  useEffect(() => {
    let cost = 0;
    for (let item of cartItems) {
      let costxAmount = item.price * item.amount;
      cost += costxAmount;
    }
    setTotal(cost);
  }, [cartItems]);

  function proceedToCheckout(){
    const ids = [];
    for(let i =0 ;i < cartItems.length; i++){
      const cartItem = cartItems[i];
      ids.push(cartItem.id);
    }
    console.log(ids);
  }
  /**
   * On "Proceed to Checkout"
   * Send in a list of product id, so that the server can find the price of each product and calculate the total for the user
   * Do not send in the total amount from the client-side because they can be malicious and send in the wrong price to pay
   */

  return (
    <div>
      <div>
        {cartItems.length == 0 ? (
          <h2>Nothing is in your cart! Lets do some shopping!</h2>
        ) : (
          <>
            <h1>Checkout</h1>
            <ul className={style["cart__expand-list"]}>
              {cartItems.map((elem) => {
                return (
                  <li key={elem.id}>
                    <CartItem
                      img={elem.img}
                      title={elem.title}
                      amount={elem.amount}
                      price={elem.price}
                      id={elem.id}
                      elem={elem}
                    ></CartItem>
                  </li>
                );
              })}
            </ul>

            <div className={style.checkout__payment}>
              <h2>
                Subtotal: $<span>{total}</span>
              </h2>
              <p>
                Please read our Shipping/Return Policy. We follow these policies
                regarding your purchases
              </p>
              <div>
                <button
                  onClick={proceedToCheckout}
                >Proceed to Checkout</button>
              </div>
            </div>
          </>
        )}
      </div>
    </div>
  );
}
export default Checkout;
