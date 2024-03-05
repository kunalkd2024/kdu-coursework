import { createAsyncThunk } from "@reduxjs/toolkit";

/**
 * Asynchronous thunk function to fetch portfolio transactions.
 *
 * @function fetchPortfolioTransactions
 * @returns {Promise<Array>} A promise that resolves with an array of portfolio transactions.
 * @throws {Error} If fetching portfolio transactions fails.
 */
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
