import logo from "../../assets/images/logo.svg";
import style from "./Navbar.module.scss";
import menuOpen from "../../assets/images/icon-menu.svg";
import menuClose from "../../assets/images/icon-close.svg";
import { Link } from "react-router-dom";
import { useEffect, useState } from "react";
import Cart from "../Cart";

function Navbar() {
  const [open, setOpen] = useState(false);

  useEffect(() => {
    if (open) {
      document.body.style.overflowY = "hidden";
    } else {
      document.body.style.overflowY = "auto";
    }
  }, [open]);

  function handleMenuButton() {
    setOpen((prevVal) => !prevVal);
  }

  function handleClickLink() {
    setOpen(false);
  }

  return (
    <header className={style.header}>
      <button onClick={handleMenuButton} className={style.hamburger}>
        <img src={menuOpen} alt="Menu Button" />
      </button>
      <div>
        <img src={logo} alt="Logo" />
      </div>
      <nav className={`${style.navbar} ${open && style.navbar_expand}`}>
        <button onClick={handleMenuButton} className={style.hamburger}>
          <img src={menuClose} alt="Menu Button" />
        </button>
        <ul className={style.navbar__list}>
          <li>
            <Link to="/catalogue/electronics" onClick={handleClickLink}>
              Electronics
            </Link>
          </li>
          <li>
            <Link to="/catalogue/jewelery" onClick={handleClickLink}>
              Jewelery
            </Link>
          </li>
          <li>
            <Link to="/catalogue/men's clothing" onClick={handleClickLink}>
              Men
            </Link>
          </li>
          <li>
            <Link to="/catalogue/women's clothing" onClick={handleClickLink}>
              Women
            </Link>
          </li>
        </ul>
      </nav>
      <Cart />
      <div className={open && style.overlay} onClick={handleMenuButton}></div>
    </header>
  );
}
export default Navbar;
