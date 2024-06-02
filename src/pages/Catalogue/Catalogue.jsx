import { Link, useLoaderData } from "react-router-dom";
import Card from "../../components/Card";
import style from "./Catalogue.module.scss";
function Catalogoue() {
  const items = useLoaderData();

  return (
    <>
      <h1>Catalgoue</h1>
      <div className={style.catalogue}>
        {items &&
          items.map((elem) => (
            <Link key={elem.id} to={`/product/${elem.id}`} state={{ ...elem }}>
              <Card
                title={elem.title}
                image={elem.image}
                price={elem.price}
              ></Card>
            </Link>
          ))}
      </div>
    </>
  );
}
export default Catalogoue;
