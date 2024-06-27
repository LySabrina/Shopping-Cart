/* eslint-disable react/prop-types */
import { createContext, useContext, useReducer, useState } from "react";
import placeholder from "../assets/images/image-product-1.jpg";

/**
 * ShopProvider combines useContext and useReducer
 *
 */

export const ShopContext = createContext({
  id: 1,
  img: placeholder,
  title: "Place holder title",
  amount: 1,
  price: 125.0,
});

export const ShopDispatch = createContext(null);

export const useShopContext = () => useContext(ShopContext);
export const useShopDispatch = () => useContext(ShopDispatch);

export function ShopProvider({ children }) {
  const [cartItems, dispatch] = useReducer(shopReducer, [
    {
      id: 1,
      img: placeholder,
      title: "Place holder title",
      amount: 1,
      price: 125.0,
    },
  ]);

  return (
    <ShopContext.Provider value={cartItems}>
      <ShopDispatch.Provider value={dispatch}>{children}</ShopDispatch.Provider>
    </ShopContext.Provider>
  );
}

function shopReducer(cartItems, action) {
  switch (action.type) {
    case "add":
      if (cartItems.findIndex((elem) => elem.id === action.item.id) >= 0) {
        const updateArr = cartItems.map((elem) => {
          if (elem.id == action.item.id) {
            elem.amount = action.item.amount;
          }
          return elem;
        });
        return updateArr;
      } else {
        return [...cartItems, action.item];
      }

    case "delete": {
      const filterItems = cartItems.filter((elem) => elem != action.item);
      return filterItems;
    }
  }
}
