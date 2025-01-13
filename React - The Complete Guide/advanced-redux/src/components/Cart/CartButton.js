import { useDispatch, useSelector } from "react-redux";
import { cartUiActions } from "../../store/slices/cart-ui-slice";
import classes from "./CartButton.module.css";

const CartButton = (props) => {
  const dispatch = useDispatch();
  const totalCartItems = useSelector((state) => state.cart.totalQuantity);

  const cartButtonHandler = (event) => {
    event.preventDefault();

    dispatch(cartUiActions.toggle());
  };
  return (
    <button className={classes.button} onClick={cartButtonHandler}>
      <span>My Cart</span>
      <span className={classes.badge}>{totalCartItems}</span>
    </button>
  );
};

export default CartButton;
