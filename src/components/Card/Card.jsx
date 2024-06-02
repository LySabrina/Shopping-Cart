import { useContext } from "react";
import style from "./Card.module.scss";
import PropTypes from "prop-types";
import { ShopContext } from "../../pages/App/App.jsx";

function Card({ title, image, price }) {
  const {addToCart} = useContext(ShopContext);

  return (
    <div className={style.card}>
      <div className={style.card__img}>
        <img src={image} alt={title} />
      </div>
      <div className={style.card__info}>
        <h1>{title}</h1>
        <p>${price}</p>
      </div>
      <div className={style.card__btn}>
        <button onClick={(e)=>{
          e.stopPropagation();
          
        }}>+</button>
      </div>
    </div>
  );
}

Card.propTypes = {
  title: PropTypes.string,
  image: PropTypes.string,
  price: PropTypes.number,
};

export default Card;
