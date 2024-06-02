import trashcan from "../../assets/images/icon-delete.svg";
import style from "./Cart.module.scss";
import PropTypes from "prop-types";
function CartItem({ img, amount, price, title }) {
  return (
    <div className={style.cartItem}>
      <div className={style.cartItem__img}>
        <img src={img} alt={title} />
      </div>
      <div className={style.cartItem__info}>
        <h3>{title}</h3>
        <div>
          ${price} x {amount}
          <span> ${price * amount}</span>
        </div>
      </div>
      <button>
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
};
export default CartItem;
