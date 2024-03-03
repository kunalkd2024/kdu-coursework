import { createAsyncThunk } from "@reduxjs/toolkit";

export const fetchPortfolioTransactions = createAsyncThunk(
  "fetchPortfolioTransactions",
  async () => {
    try {
      const response = await fetch(
        "https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/portfolio-transactions.json"
      );
      if (!response.ok) {
        throw new Error("Failed to fetch portfolio transactions");
      }
      const data = await response.json();
      return data;
    } catch (error) {
      throw new Error("Failed to fetch portfolio transactions");
    }
  }
);
