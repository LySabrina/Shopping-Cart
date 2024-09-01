/* eslint-disable react/prop-types */
import { createContext, useContext, useReducer, useState } from "react";
import placeholder from "../assets/images/image-product-1.jpg";

/**
 * ShopProvider combines useContext and useReducer
 *
 */

export const ShopContext = createContext([]);

export const ShopDispatch = createContext(null);

export const useShopContext = () => useContext(ShopContext);

export const useShopDispatch = () => useContext(ShopDispatch);

export const APP_NAME = "SHOPPING";

export function ShopProvider({ children }) {
  const localStorageCartItems = JSON.parse(
    localStorage.getItem(`${APP_NAME}.cartItems`)
  );

  const [cartItems, dispatch] = useReducer(
    shopReducer,
    localStorageCartItems ?? []
  ); // localStorageCartItems ?? [] means if localStorageCartItems is null return [] else return localStorageCartItems

  return (
    <ShopContext.Provider value={cartItems}>
      <ShopDispatch.Provider value={dispatch}>{children}</ShopDispatch.Provider>
    </ShopContext.Provider>
  );
}

function shopReducer(cartItems, action) {
  switch (action.type) {
    case "add": {
      let updateArr;
      if (cartItems.findIndex((elem) => elem.id === action.item.id) >= 0) {
        updateArr = cartItems.map((elem) => {
          if (elem.id == action.item.id) {
            elem.amount = action.item.amount;
          }
          return elem;
        });
        // return updateArr;
      } else {
        updateArr = [...cartItems, action.item];
        // return [...cartItems, action.item];
      }

      // save to localstorage
      localStorage.setItem(`${APP_NAME}.cartItems`, JSON.stringify(updateArr));
      return updateArr;
    }

    case "delete": {
      const filterItems = cartItems.filter((elem) => elem != action.item);
      localStorage.setItem(
        `${APP_NAME}.cartItems`,
        JSON.stringify(filterItems)
      );

      return filterItems;
    }

    case "clear": {
      console.log("TEST");
      localStorage.setItem(`${APP_NAME}.cartItems`, JSON.stringify([]));
      return [];
    }
  }
}
