import { useState } from "react";

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
    <div>
      <form>
        <input
          type="text"
          name="fname"
          placeholder="First Name"
          value={register.fname}
          onChange={(e) => handleFormOnChange(e)}
        />
        <input
          type="text"
          name="lname"
          value={register.lname}
          placeholder="Last Name"
          onChange={(e) => handleFormOnChange(e)}
        />
        <input
          type="email"
          name="email"
          placeholder="Email"
          value={register.email}
          onChange={(e) => handleFormOnChange(e)}
        />
        <input
          type="password"
          name="password"
          placeholder="Password"
          value={register.password}
          onChange={(e) => handleFormOnChange(e)}
        />
        <input
          type="password"
          name="repassword"
          value={register.repassword}
          placeholder="Re-type Password"
        />
      </form>
    </div>
  );
}
export default Register;
