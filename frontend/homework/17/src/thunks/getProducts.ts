import { createAsyncThunk } from "@reduxjs/toolkit";
import { ProductAPI } from "../types/types";

export const getProducts = createAsyncThunk<ProductAPI[]>(
  "getProducts",
  async () => {
    try {
      const response = await fetch("https://fakestoreapi.com/products");
      const data = await response.json();
      return data;
    } catch {
      return "Error while making API call.";
    }
  }
);
