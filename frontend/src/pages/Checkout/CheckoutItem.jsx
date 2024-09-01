import style from "./CheckoutItem.module.scss";
function CheckoutItem({ img, title, amount, price, id }) {
  return (
    <li className={style.checkout__img}>
      <div className={style.checkoutItem__img}>
        <img src={img} alt={title} />
        <p className={style.checkoutItem__amount}>{amount}</p>
      </div>
      <div>
        <h3>{title}</h3>
        <p>${price}</p>
      </div>
    </li>
  );
}
export default CheckoutItem;
