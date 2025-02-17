import { useDispatch } from "react-redux";
import { cartActions } from "../../store/slices/cart-slice";
import { cartUiActions } from "../../store/slices/cart-ui-slice";
import Card from "../UI/Card";
import classes from "./ProductItem.module.css";

const ProductItem = (props) => {
  const dispatch = useDispatch();

  const { id, title, price, description } = props;

  const addToCartHandler = (event) => {
    event.preventDefault();

    dispatch(
      cartActions.addToCart({
        id,
        title,
        price,
      })
    );
  };

  const testHandler = (event) => {
    event.preventDefault();
    dispatch(cartUiActions.test());
  };

  return (
    <li className={classes.item}>
      <Card>
        <header>
          <h3>{title}</h3>
          <div className={classes.price}>${price.toFixed(2)}</div>
        </header>
        <p>{description}</p>
        <div className={classes.actions}>
          <button onClick={addToCartHandler}>Add to Cart</button>
          <button onClick={testHandler}>Test Cart</button>
        </div>
      </Card>
    </li>
  );
};

export default ProductItem;
