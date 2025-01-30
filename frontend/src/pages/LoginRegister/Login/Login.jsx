import { useState } from "react";
import { Link } from "react-router-dom";
import style from "../LoginRegister.module.scss";

function Login() {
  const [login, setLogin] = useState({
    email: "",
    password: "",
  });

  function handleFormOnChange(e) {
    const target = e.target;
    const name = target.name;
    const value = target.value;
    console.log("value", value);
    setLogin((prevVal) => {
      return {
        ...prevVal,
        [name]: value,
      };
    });
  }

  async function handleOnSubmit(e) {
    e.preventDefault();
    const response = await fetch("http://localhost:8080/api/account/login", {
      method: "POST",
    });
    
    console.log(await response.text());
  }

  return (
    <div className={style.container}>
      <form className={style.form} onSubmit={handleOnSubmit}>
        <h1>Login</h1>

        <div className={style["form__input-group"]}>
          <label htmlFor="email">Email</label>
          <input
            type="text"
            id="email"
            name="email"
            placeholder="Email"
            value={login.email}
            onChange={(e) => handleFormOnChange(e)}
          />
        </div>

        <div className={style["form__input-group"]}>
          <label htmlFor="Password">Password</label>
          <input
            type="password"
            id="password"
            placeholder="Password"
            name="password"
            value={login.password}
            onChange={(e) => handleFormOnChange(e)}
          />
        </div>
        <button className={style["form__submit"]}>Login</button>
        <p className={style["form__divider"]}>- or -</p>

        <div className={style["form__links"]}>
          <Link to={"/account/register"}>
            Don&lsquo;t have an account? Register Here
          </Link>
          <Link to={""}>Forgot your password?</Link>
        </div>
      </form>
    </div>
  );
}
export default Login;
