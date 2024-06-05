import { useContext, useState } from "react";
import trashcan from "../../assets/images/icon-delete.svg";
import style from "./Cart.module.scss";
import PropTypes from "prop-types";
import { ShopContext } from "../../pages/App/App.jsx";

function CartItem({ img, amount, price, title, elem }) {
  const { deleteFromCart, addToCart } = useContext(ShopContext);

  function handleChangeAmount(num) {
    if (amount + num < 1) {
      return;
    }

    const item = { ...elem, amount: amount + num };
    addToCart(item);
  }
  return (
    <div className={style.cartItem}>
      <div className={style.cartItem__img}>
        <img src={img} alt={title} />
      </div>
      <div className={style.cartItem__info}>
        <h3>{title}</h3>
        <div>
          ${price} x {amount}
          <span>
            <b> ${price * amount}</b>
          </span>
        </div>
      </div>
      <div>
        <button onClick={() => handleChangeAmount(-1)}>-</button>
        <span>{amount}</span>
        <button onClick={() => handleChangeAmount(1)}>+</button>
      </div>
      <button onClick={() => deleteFromCart(elem)}>
        <img src={trashcan} alt="Delete" />
      </button>
    </div>
  );
}

CartItem.propTypes = {
  img: PropTypes.string,
  title: PropTypes.string,
  amount: PropTypes.number,
  price: PropTypes.number,
  elem: PropTypes.object,
};
export default CartItem;
