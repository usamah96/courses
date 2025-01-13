import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  items: [],
  totalQuantity: 0,
};

export const cartSlice = createSlice({
  name: "cart",
  initialState,
  reducers: {
    addToCart: (state, actions) => {
      state.totalQuantity++;

      const newItem = actions.payload;
      const existingItem = state.items.find((item) => item.id === newItem.id);
      if (existingItem) {
        existingItem.quantity++;
        existingItem.totalPrice += existingItem.price;
      } else {
        state.items.push({
          id: newItem.id,
          title: newItem.title,
          price: newItem.price,
          totalPrice: newItem.price,
          quantity: 1,
        });
      }
    },
    removeFromCart: (state, actions) => {
      state.totalQuantity--;

      const itemId = actions.payload;
      const existingItem = state.items.find((item) => item.id === itemId);
      if (existingItem.quantity === 1)
        state.items = state.items.filter((item) => item.id !== existingItem.id);
      else {
        existingItem.quantity--;
        existingItem.totalPrice -= existingItem.price;
      }
    },
  },
});

export const cartActions = cartSlice.actions;
