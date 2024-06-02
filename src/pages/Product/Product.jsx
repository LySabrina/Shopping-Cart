import { useLocation } from "react-router-dom";

function Product() {
  const { state } = useLocation();

  return (
    <div>
      <div>
        <img src={state.image} alt={state.title} />
      </div>
      <div>
        <h1>{state.title}</h1>
        <p>{state.description}</p>
        <div>
          <p>Price</p>
        </div>
        <div>
          <button></button>
          <button>
            <span>
              <img src="" alt="" />
            </span>
            Add to Cart
          </button>
        </div>
      </div>
    </div>
  );
}
export default Product;
