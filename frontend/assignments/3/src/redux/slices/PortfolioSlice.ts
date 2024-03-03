import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { PortfolioTransactionAPI } from "../../types/portfolioTransactionTypes";
import { fetchPortfolioTransactions } from "../../thunks/getPortfolioTransactions";

interface portfolioState {
  transactions: PortfolioTransactionAPI[];
  loading: "fulfilled" | "pending" | "rejected";
  error?: boolean;
}

const initialState: portfolioState = {
  transactions: [],
  loading: "pending",
};

const PortfolioSlice = createSlice({
  name: "PortfolioData",
  initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(
        fetchPortfolioTransactions.fulfilled,
        (state, action: PayloadAction<PortfolioTransactionAPI[]>) => {
          state.loading = "fulfilled";
          state.error = false;
          state.transactions = action.payload;
        }
      )
      .addCase(fetchPortfolioTransactions.pending, (state) => {
        state.loading = "pending";
      })
      .addCase(fetchPortfolioTransactions.rejected, (state) => {
        state.loading = "rejected";
        state.error = true;
      });
  },
});

export const {} = PortfolioSlice.actions;

export default PortfolioSlice.reducer;
