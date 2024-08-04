import App from "./App/App";
import CartPage from "./CartPage/CartPage.jsx";
import Catalogoue from "./Catalogue/Catalogue.jsx";
import Checkout from "./Checkout/";
import Error from "./Error/Error";
import Home from "./Home";
import Login from "./LoginRegister/Login/Login.jsx";
import Register from "./LoginRegister/Register/Register.jsx";

import Product from "./Product/";
import StripeCheckout from "./Stripe/StripeCheckout.jsx";
import UserProfile from "./UserProfile/UserProfile.jsx";

const routes = [
  {
    path: "/",
    element: <App />,
    errorElement: <Error />,
    children: [
      {
        path: "/",
        element: <Home />,
      },
      {
        path: "/catalogue/:category",
        loader: async ({ params }) => {
          
          const fetchCategory = await fetch(
            `http://localhost:8080/api/product/category/${params.category}`
          );

          const responseJson = await fetchCategory.json();

          return responseJson;
        },
        errorElement: <Error/>,
        element: <Catalogoue />,
      },

      {
        path: "/product/:id",

        element: <Product />,
      },
      {
        path: "/cart",
        element: <CartPage />,
      },
      {
        path: "/account",
        children: [
          {
            path: "login",
            element: <Login />,
          },

          {
            path: "register",
            element: <Register />,
          },

          {
            path: "user",
            element: <UserProfile />,
          },
        ],
      },
      {
        path: "/checkout",
        element: <Checkout/>
      }
      ,
      {
        path: "/test",
        element: <StripeCheckout />,
      },
    ],
  },
];
export default routes;
