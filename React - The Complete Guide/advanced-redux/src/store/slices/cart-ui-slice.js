const { createSlice } = require("@reduxjs/toolkit");

export const cartUiSlice = createSlice({
  name: "cart-ui",
  initialState: { show: false, test: false },
  reducers: {
    toggle: (state) => void (state.show = !state.show),
    test: (state) => {
      state.test = !state.test;
    },
  },
});

export const cartUiActions = cartUiSlice.actions;
