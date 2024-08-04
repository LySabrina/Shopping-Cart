import { Link, useLoaderData, useNavigation } from "react-router-dom";
import Card from "../../components/Card";
import style from "./Catalogue.module.scss";

function Catalogoue() {
  const items = useLoaderData();
  const loading = useNavigation();

  
  if (loading.state == "loading") {
    return <h1 className={style.catalogue__loading}>Loading...</h1>;
  }
  return (
    <>
      <h1>Catalgoue</h1>
      <div className={style.catalogue}>
        {items &&
          items.map((elem) => (
            <Link key={elem.id} to={`/product/${elem.id}`} state={{ ...elem }}>
              <Card
                title={elem.title}
                img={elem.image}
                price={elem.price}
                id={elem.id}
                
              ></Card>
            </Link>
          ))}
      </div>
    </>
  );
}
export default Catalogoue;
