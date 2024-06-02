import { useState } from "react";
import style from "./Cart.module.scss";
import cart from "../../assets/images/icon-cart.svg";
import CartItem from "./CartItem.jsx";
import placeholder from "../../assets/images/image-product-1.jpg";
function Cart() {
  const [items, setItems] = useState([
    {
      id: 1,
      img: placeholder,
      title: "Place holder title",
      amount: 1,
      price: 125.0,
    },
  ]);

  const [open, setOpen] = useState(false);

  function handleToggleCart() {
    setOpen((prev) => !prev);
  }

  return (
    <div className={style.cart}>
      <div>
        <button className={style.cart__btn} onClick={handleToggleCart}>
          <img src={cart} alt="Cart" />
          {items.length > 0 && (
            <span className={style.cart__indicator}>{items.length}</span>
          )}
        </button>
      </div>

      {open && (
        <div className={style.cart__expand}>
          <h2 className={style.cart__expand__title}>Cart</h2>
          {items.length > 0 ? (
            <ul>
              {items.map((elem) => (
                <li key={elem.id}>
                  <CartItem
                    img={elem.img}
                    title={elem.title}
                    amount={elem.amount}
                    price={elem.price}
                  ></CartItem>
                </li>
              ))}
            </ul>
          ) : (
            <p>You cart is empty</p>
          )}

          <button className={style.cart__expand__checkout}>Checkout</button>
        </div>
      )}
    </div>
  );
}
export default Cart;
