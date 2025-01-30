import React from "react";
import ReactDOM from "react-dom/client";

import "./index.css";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import routes from "./pages/routes.jsx";
import { ShopProvider } from "./contexts/ShopProvider.jsx";

const router = createBrowserRouter(routes);

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <ShopProvider>
      <RouterProvider router={router}></RouterProvider>
    </ShopProvider>
  </React.StrictMode>
);
