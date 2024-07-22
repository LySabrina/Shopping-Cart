import trashcan from "../../assets/images/icon-delete.svg";
import style from "./Cart.module.scss";
import PropTypes from "prop-types";

import { useShopDispatch } from "../../contexts/ShopProvider.jsx";

function CartItem({ img, amount, price, title, elem }) {
  const dispatch = useShopDispatch();

  function handleChangeAmount(num) {
    if (amount + num < 1) {
      console.log("dispatch?");
      dispatch({
        type: "delete",
        item: elem,
      });
      console.log("dispatch!");
    } else {
      const item = { ...elem, amount: amount + num };
      console.log("dispatch?");
      dispatch({
        type: "add",
        item: item,
      });
      console.log("dispatch!");
    }
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
            <b> ${Math.round(price * amount * 100) / 100}</b>
          </span>
          <div>
            <button 
            
            onClick={() => handleChangeAmount(-1)}>-</button>
            <span> {amount} </span>
            <button onClick={() => handleChangeAmount(1)}>+</button>
          </div>
        </div>
      </div>

      <button
        onClick={() =>
          dispatch({
            type: "delete",
            item: elem,
          })
        }
      >
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
