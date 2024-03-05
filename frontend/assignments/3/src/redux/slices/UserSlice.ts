import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { StocksAPI } from "../../types/stocksTypes";

interface Transaction {
  stock: StocksAPI;
  quantity: number;
  type: "buy" | "sell";
  status: "pass" | "fail";
}

interface OwnedStock {
  stock: StocksAPI;
  quantity: number;
}

interface UserState {
  name: string;
  walletBalance: number;
  transactions: Transaction[];
  owned: OwnedStock[];
}

const initialState: UserState = {
  name: "Kunal",
  walletBalance: 100000,
  transactions: [],
  owned: [],
};

/**
 * Redux slice for managing user data.
 *
 * @constant userSlice
 * @property {string} name - The name of the slice.
 * @property {UserState} initialState - The initial state of the slice.
 * @property {Object} reducers - Reducers to handle actions.
 * @property {Function} reducers.buyStock - Reducer to handle buying a stock.
 * @property {Function} reducers.sellStock - Reducer to handle selling a stock.
 */
const userSlice = createSlice({
  name: "userData",
  initialState,
  reducers: {
    buyStock(state, action: PayloadAction<Transaction>) {
      const { stock, quantity, type, status } = action.payload;
      if (status === "pass") {
        state.walletBalance -= stock.base_price * quantity;
        state.transactions.push({ stock, quantity, type, status });
        const existingStockIndex = state.owned.findIndex(
          (s) => s.stock.stock_name === stock.stock_name
        );
        if (existingStockIndex !== -1) {
          state.owned[existingStockIndex].quantity += quantity;
        } else {
          state.owned.push({ stock, quantity });
        }
      }
    },
    sellStock(state, action: PayloadAction<Transaction>) {
      const { stock, quantity, type, status } = action.payload;
      if (status === "pass") {
        state.walletBalance += stock.base_price * quantity;
        state.transactions.push({ stock, quantity, type, status });
        const existingStockIndex = state.owned.findIndex(
          (s) => s.stock.stock_name === stock.stock_name
        );
        if (existingStockIndex !== -1) {
          state.owned[existingStockIndex].quantity -= quantity;
          if (state.owned[existingStockIndex].quantity <= 0) {
            state.owned.splice(existingStockIndex, 1);
          }
        }
      }
    },
  },
});

export const { buyStock, sellStock } = userSlice.actions;

export default userSlice.reducer;
