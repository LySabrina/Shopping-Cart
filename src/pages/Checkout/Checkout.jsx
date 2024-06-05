import { useContext } from "react";
import style from "./Checkout.module.scss";
import { ShopContext } from "../App/App.jsx";
import CartItem from "../../components/Cart/CartItem.jsx";
function Checkout() {
  const { cartItems } = useContext(ShopContext);

  return (
    <div>
      <div>
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
      </div>
      <div className={style.checkout__payment}>
        <h2>Subtotal: </h2>
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
    </div>
  );
}
export default Checkout;
