import App from "./App/App";
import Catalogoue from "./Catalogue/Catalogue.jsx";
import Checkout from "./Checkout/";
import Error from "./Error/Error";
import Home from "./Home";
import Product from "./Product/";
import { defer } from "react-router-dom";
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
    ],
  },
];
export default routes;
