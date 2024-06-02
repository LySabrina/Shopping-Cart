import style from "./Card.module.scss";
import PropTypes from "prop-types";

function Card({ title, image, price }) {
  return (
    <div className={style.card}>
      <div className={style.card__img}>
        <img src={image} alt={title} />
      </div>
      <div className={style.card__info}>
        <h1>{title}</h1>
        <p>${price}</p>
      </div>
      <div>
        <button>+</button>
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
