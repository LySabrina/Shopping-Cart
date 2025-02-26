import { Outlet } from "react-router-dom";
import Navbar from "../../components/Navbar";
import { ShopProvider } from "../../contexts/ShopProvider.jsx";

function App() {
  return (
    <>
      <Navbar />
      <main>
        <Outlet />
      </main>
    </>
  );
}
export default App;
