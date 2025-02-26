import { Outlet } from "react-router-dom";
import Navbar from "../../components/Navbar";
import { ShopProvider } from "../../contexts/ShopProvider.jsx";

function App() {
  return (
    <>
      <ShopProvider>
        <Navbar />
        <main>
          <Outlet />
        </main>
      </ShopProvider>
    </>
  );
}
export default App;
