import { useContext, useState } from "react";
import style from "./Cart.module.scss";
import cart from "../../assets/images/icon-cart.svg";
import CartItem from "./CartItem.jsx";

import { ShopContext } from "../../pages/App/App.jsx";
import { Link } from "react-router-dom";
function Cart() {
  //use Context inside a state inside Cart because other components will need
  //to manipulate and add to or delete from cart. S
  //So we use context to pass down and it resides in the top component, App
  const { cartItems } = useContext(ShopContext);

  const [open, setOpen] = useState(false);

  function handleToggleCart() {
    setOpen((prev) => !prev);
  }

  return (
    <div className={style.cart}>
      <div>
        <button className={style.cart__btn} onClick={handleToggleCart}>
          <img src={cart} alt="Cart" />
          {cartItems.length > 0 && (
            <span className={style.cart__indicator}>{cartItems.length}</span>
          )}
        </button>
      </div>

      {open && (
        <div className={style.cart__expand}>
          <h2 className={style.cart__expand__title}>Cart</h2>
          {cartItems.length > 0 ? (
            <ul>
              {cartItems.map((elem) => (
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
              ))}
            </ul>
          ) : (
            <p>You cart is empty</p>
          )}

          <Link to={"/checkout"} onClick={() => setOpen(false)}>
            <button className={style.cart__expand__checkout}>Checkout</button>
          </Link>
        </div>
      )}
    </div>
  );
}
export default Cart;
