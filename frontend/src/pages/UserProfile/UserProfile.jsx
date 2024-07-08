import user_icon from "../../assets/images/icon-user.svg";
import style from "./UserProfile.module.scss";

function UserProfile() {
  return (
    <div className={style["user--container"]}>
      <div className={style.user__info}>
        <div className={style.user__img}>
          <img src={user_icon} alt="User" />
        </div>
        <div>
          <h1>Fname Lname</h1>
          <h2>email@email.com</h2>
        </div>
      </div>

      <div className={style["user__selection-container"]}>
        <ul className={style.user__selection}>
          <li>
            <button>My Orders</button>
          </li>
          <li>
            <button>My Wishlist</button>
          </li>
        </ul>
      </div>
    </div>
  );
}

function SelectionScreen() {
  //do an useEffect here?
  //based on what's clicked, we can determine what component to render
}

function Orders() {
  return (
    <table>
      <tr>
        <th>Month</th>
        <th>Order Number</th>
        <th>Cost</th>
        <th>Date</th>
      </tr>
    </table>
  );
}

function Order({ orderNum, cost, date }) {
  //maybe create a page later to see more details about the orderNum?
  return (
    <tr>
      <td>{orderNum}</td>
      <td>{cost}</td>
      <td>{date}</td>
    </tr>
  );
}

export default UserProfile;
