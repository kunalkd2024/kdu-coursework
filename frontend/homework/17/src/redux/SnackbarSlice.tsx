import { createSlice, PayloadAction } from "@reduxjs/toolkit";
import { getProducts } from "../thunks/getProducts";

interface errorType {
  message: string | null;
  show: boolean;
}

const initialState: errorType = {
  message: null,
  show: false,
};

export const SnackbarSlice = createSlice({
  name: "error",
  initialState,
  reducers: {
    setError(state, action: PayloadAction<string>) {
      state.message = action.payload;
    },
    clearError(state) {
      state.message = null;
    },
    setShow(state, action) {
      state.show = action.payload as boolean;
    },
  },
  extraReducers(builder) {
    builder
      .addCase(getProducts.rejected, (state) => {
        state.message = "Error";
        state.show = true;
      })
      .addCase(getProducts.fulfilled, (state) => {
        state.message = "Result is fulfilled.";
        state.show = true;
      });
  },
});

export const { setError, clearError, setShow } = SnackbarSlice.actions;
export default SnackbarSlice.reducer;
