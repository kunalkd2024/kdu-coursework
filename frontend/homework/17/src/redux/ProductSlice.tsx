import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { ProductAPI } from "../types/types";
import { getProducts } from "../thunks/getProducts";

interface ProductType {
  products: ProductAPI[];
  loading: boolean;
  message: string;
  error?: string;
}

const initialState: ProductType = {
  products: [],
  message: "",
  loading: false
};

export const productSlice = createSlice({
  name: "products",
  initialState,
  reducers: {},
  extraReducers(builder) {
    builder.addCase(getProducts.pending, (state) => {
      state.loading = true;
      state.message = "Result is pending."
      state.error = "";
    })
    .addCase(getProducts.fulfilled, (state, action: PayloadAction<ProductAPI[]>) => {
        state.loading = false;
        state.error = "";
        state.products = action.payload;
        state.message = "Result is fulfilled."
    })
  },
});

export default productSlice.reducer;