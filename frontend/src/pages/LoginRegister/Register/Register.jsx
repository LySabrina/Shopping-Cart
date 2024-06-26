import { useState } from "react";
import style from "../LoginRegister.module.scss";

function Register() {
  const [register, setRegister] = useState({
    fname: "",
    lname: "",
    email: "",
    password: "",
    repassword: "",
  });

  function handleFormOnChange(e) {
    const target = e.target;
    const name = target.name;
    const value = target.value;

    setRegister((prevVal) => {
      return {
        ...prevVal,
        [name]: value,
      };
    });
  }

  return (
    <div className={style.container}>
      <form className={style.form}>
        <h1>Create Account</h1>
        <div className={style["form__input-group"]}>
          <label htmlFor="fname">First Name</label>
          <input
            type="text"
            name="fname"
            id="fname"
            placeholder="First Name"
            value={register.fname}
            onChange={(e) => handleFormOnChange(e)}
          />
        </div>

        <div className={style["form__input-group"]}>
          <label htmlFor="lname">Last Name</label>
          <input
            type="text"
            name="lname"
            id="lname"
            value={register.lname}
            placeholder="Last Name"
            onChange={(e) => handleFormOnChange(e)}
          />
        </div>

        <div className={style["form__input-group"]}>
          <label htmlFor="email">Email</label>
          <input
            type="email"
            name="email"
            id="email"
            placeholder="Email"
            value={register.email}
            onChange={(e) => handleFormOnChange(e)}
          />
        </div>

        <div className={style["form__input-group"]}>
          <label htmlFor="Password">Password</label>
          <input
            type="password"
            name="password"
            placeholder="Password"
            value={register.password}
            onChange={(e) => handleFormOnChange(e)}
          />
        </div>

        <div className={style["form__input-group"]}>
          <label htmlFor="repassword">Retype Password</label>
          <input
            type="password"
            name="repassword"
            id="repassword"
            value={register.repassword}
            placeholder="Re-type Password"
          />
        </div>

        <button className={style["form__submit"]}>Register</button>
      </form>
    </div>
  );
}
export default Register;
