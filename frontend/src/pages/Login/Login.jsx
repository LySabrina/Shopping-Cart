import { useState } from "react";
import { Link } from "react-router-dom";

function Login() {
  const [login, setLogin] = useState({
    email: "",
    password: "",
  });

  function handleFormOnChange(e) {
    const target = e.target;
    const name = target.name;
    const value = target.value;

    setLogin((prevVal) => {
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
          placeholder="Email"
          value={login.email}
          onChange={(e) => handleFormOnChange(e)}
        />
        <input
          type="password"
          placeholder="Password"
          value={login.password}
          onChange={(e) => handleFormOnChange(e)}
        />
        <div>
          <button>Login</button>
          <Link to={"/account/register"}>
            Don't have an account? Register Here
          </Link>
        </div>
      </form>
    </div>
  );
}
export default Login;
