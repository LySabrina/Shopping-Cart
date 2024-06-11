import { useContext, useEffect, useState } from "react";
import style from "./Checkout.module.scss";
import { ShopContext } from "../App/App.jsx";
import CartItem from "../../components/Cart/CartItem.jsx";
import { PaymentElement } from "@stripe/react-stripe-js";

function Checkout() {
  const { cartItems } = useContext(ShopContext);
  const [total, setTotal] = useState(0);

  useEffect(() => {
    let cost = 0;
    for (let item of cartItems) {
      let costxAmount = item.price * item.amount;
      cost += costxAmount;
    }
    setTotal(cost);
  }, [cartItems]);

  return (
    <div>
      <div>
        {cartItems.length == 0 ? (
          <h2>Nothing is in your cart! Lets do some shopping!</h2>
        ) : (
          <>
            <h1>Checkout</h1>
            <ul>
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
                Subtotal: <span>{total}</span>
              </h2>
              <p>
                Please read our Shipping/Return Policy. We follow these policies
                regarding your purchases
              </p>
              <div>
                <button>Proceed to Checkout</button>
                <p>- or -</p>
                <div>
                  <button>Paylal</button>
                  <button>Gpay</button>
                </div>
              </div>
            </div>
          </>
        )}

        
      </div>
    </div>
  );
}
export default Checkout;
