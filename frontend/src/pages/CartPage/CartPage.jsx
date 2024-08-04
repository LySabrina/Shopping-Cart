import style from "./CartPage.module.scss";
import CartItem from "../../components/Cart/CartItem.jsx";
import { useShopContext } from "../../contexts/ShopProvider.jsx";
import { redirect, useNavigate } from "react-router-dom";

function CartPage() {
  const cartItems = useShopContext();
  const navigate = useNavigate();

  const totalCost = () => {
    let cost = 0;
    for (let item of cartItems) {
      let costxAmount = item.price * item.amount;
      cost += costxAmount;
    }

    return cost;
  };

  function getCheckoutItems() {
    const items = [];
    for (let i = 0; i < cartItems.length; i++) {
      const cartItem = cartItems[i];

      const item = {};
      item.id = cartItem.id;
      item.amount = cartItem.amount;

      items.push(item);
    }
    return items;
  }

  async function proceedToCheckout() {
    try {
      const items = getCheckoutItems(); //get the id of items and their amount, is an array
      console.log("items", items);
      const paymentIntent = await fetch(
        "http://localhost:8080/api/checkout/create-payment-intent",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },

          body: JSON.stringify(items),
        }
      );

      const clientSecret = await paymentIntent.text();
      navigate("/checkout", { state: { clientSecret: clientSecret } });
      return clientSecret;
    } catch (error) {
      console.error(error.message);
    }
  }

  /**
   * On "Proceed to Checkout"
   * Send in a list of product id, so that the server can find the price of each product and calculate the total for the user
   * Do not send in the total amount from the client-side because they can be malicious and send in the wrong price to pay
   */

  return (
    <div>
      <div>
        {cartItems.length == 0 ? (
          <h2>Nothing is in your cart! Lets do some shopping!</h2>
        ) : (
          <>
            <h1>Checkout</h1>
            <ul className={style["cart__expand-list"]}>
              {cartItems.map((elem) => {
                return (
                  <li key={elem.id}>
                    <CartItem
                      img={elem.img}
                      title={elem.title}
                      amount={elem.amount}
                      price={elem.price}
                      id={elem.id}
                      elem={elem}
                    ></CartItem>
                  </li>
                );
              })}
            </ul>

            <div className={style.checkout__payment}>
              <h2>
                Subtotal: $<span>{totalCost()}</span>
              </h2>
              <p>
                Please read our Shipping/Return Policy. We follow these policies
                regarding your purchases
              </p>
              <div>
                <button onClick={proceedToCheckout}>Proceed to Checkout</button>
              </div>
            </div>
          </>
        )}
      </div>
    </div>
  );
}
export default CartPage;
