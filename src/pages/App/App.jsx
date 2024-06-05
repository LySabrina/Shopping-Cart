import { Outlet } from "react-router-dom";
import Navbar from "../../components/Navbar";
import { createContext, useState } from "react";
import placeholder from "../../assets/images/image-product-1.jpg";

export const ShopContext = createContext({
  cartItems: [],
  addToCart: () => {},
  deleteFromCart: () => {},
});

function App() {
  const [cartItems, setCartItems] = useState([
    {
      id: 1,
      img: placeholder,
      title: "Place holder title",
      amount: 1,
      price: 125.0,
    },
  ]);

  const addToCart = (item) => {
    //check if the id exist in the array, if it exist, update the amoutn
    //if it doesn't exist, just add it into the array

    const id = item.id;

    if (cartItems.findIndex((elem) => elem.id == id) >= 0) {
      const updateArr = cartItems.map((elem) => {
        if (elem.id == id) {
          elem.amount = item.amount;
        }
        return elem;
      });
      setCartItems(updateArr);
    } else {
      setCartItems((prevItems) => [...prevItems, item]);
    }
  };

  const deleteFromCart = (item) => {
    const filterItems = cartItems.filter((elem) => elem != item);
    setCartItems(filterItems);
  };

  return (
    <>
      <ShopContext.Provider value={{ cartItems, addToCart, deleteFromCart }}>
        <Navbar />
        <main>
          <Outlet />
        </main>
      </ShopContext.Provider>
    </>
  );
}
export default App;
