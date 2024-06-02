import { useLocation } from "react-router-dom";
import style from "./Product.module.scss";
import cart from "../../assets/images/icon-cart.svg";
import minus from "../../assets/images/icon-minus.svg";
import plus from "../../assets/images/icon-plus.svg";
import { useContext } from "react";
import { ShopContext } from "../App/App.jsx";

function Product() {
  const { state } = useLocation();
  const { addToCart } = useContext(ShopContext);

  return (
    <div className={style.product}>
      <div className={style.product__img}>
        <img src={state.image} alt={state.title} />
      </div>
      <div className={style.product__info}>
        <h1>{state.title}</h1>
        <p>{state.description}</p>
        <div>
          <p>${state.price}</p>
        </div>
        <div className={style.product__btns}>
          <button></button>
          <button
            className={style["product__btns--cart"]}
            onClick={() => {
              const item = {
                title: state.title,
                id: state.id,
                img: state.image,
                amount: 1,
                price: state.price,
              };

              addToCart(item);
            }}
          >
            <span>
              <img src={cart} alt="Add to cart" />
            </span>
            Add to Cart
          </button>
        </div>
      </div>
    </div>
  );
}
export default Product;
