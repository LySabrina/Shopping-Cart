import App from "./App/App";
import Catalogoue from "./Catalogue/Catalogue.jsx";
import Checkout from "./Checkout/";
import Error from "./Error/Error";
import Home from "./Home";
import Login from "./LoginRegister/Login/Login.jsx";
import Register from "./LoginRegister/Register/Register.jsx";

import Product from "./Product/";


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
            `https://fakestoreapi.com/products/category/${params.category}`
          );

          const responseJson = await fetchCategory.json();

          return responseJson;
        },
        element: <Catalogoue />,
      },

      {
        path: "/product/:id",

        element: <Product />,
      },
      {
        path: "/checkout",
        element: <Checkout />,
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
        ],
      },
    ],
  },
];
export default routes;
