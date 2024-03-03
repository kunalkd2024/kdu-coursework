import { createAsyncThunk } from "@reduxjs/toolkit";

export const fetchStocks = createAsyncThunk("fetchStocks", async () => {
  try {
    const response = await fetch(
      "https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/stocks.json"
    );
    if (!response.ok) {
      throw new Error("Failed to fetch stocks data");
    }
    const data = await response.json();
    return data;
  } catch (error) {
    throw new Error("Failed to fetch stocks data");
  }
});
