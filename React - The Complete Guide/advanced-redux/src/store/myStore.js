import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { cartSlice } from "./slices/cart-slice";
import { cartUiSlice } from "./slices/cart-ui-slice";

const store = configureStore({
  reducer: combineReducers({
    cartUi: cartUiSlice.reducer,
    cart: cartSlice.reducer,
  }),
});

export default store;
