import { useContext } from "react";
import style from "./Card.module.scss";
import PropTypes from "prop-types";

import { useShopDispatch } from "../../contexts/ShopProvider.jsx";

function Card({ title, img, price, id }) {
  const dispatch = useShopDispatch();

  return (
    <div className={style.card}>
      <div className={style.card__img}>
        <img src={img} alt={title} />
      </div>
      <div className={style.card__info}>
        <h1>{title}</h1>
        <p>${price}</p>
      </div>
      <div className={style.card__btn}>
        <button
          onClick={(e) => {
            e.preventDefault();

            const item = {
              title: title,
              img: img,
              price: price,
              amount: 1,
              id: id,
            };
            dispatch({
              type: "add",
              item: item,
            });
          }}
        >
          +
        </button>
      </div>
    </div>
  );
}

Card.propTypes = {
  title: PropTypes.string,
  img: PropTypes.string,
  price: PropTypes.number,
  id: PropTypes.number,
};

export default Card;
