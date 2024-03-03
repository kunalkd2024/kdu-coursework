import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { StocksAPI } from "../../types/stocksTypes";
import { fetchStocks } from "../../thunks/getStocks";

interface stocksState {
  stocks: StocksAPI[];
  favorites: StocksAPI[];
  loading: "fulfilled" | "pending" | "rejected";
  error?: boolean;
}

const initialState: stocksState = {
  stocks: [],
  favorites: [],
  loading: "pending",
};

const StocksSlice = createSlice({
  name: "stockData",
  initialState,
  reducers: {
    addToWatchlist: (state, action: PayloadAction<StocksAPI>) => {
      state.favorites.push(action.payload);
    },
    deleteFromWatchlist: (state, action: PayloadAction<StocksAPI>) => {
      state.favorites = state.favorites.filter(
        (stock) => stock.stock_name !== action.payload.stock_name
      );
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(
        fetchStocks.fulfilled,
        (state, action: PayloadAction<StocksAPI[]>) => {
          state.loading = "fulfilled";
          state.error = false;
          state.stocks = action.payload;
          state.stocks.sort((a, b) => a.stock_name.localeCompare(b.stock_name));
        }
      )
      .addCase(fetchStocks.pending, (state) => {
        state.loading = "pending";
      })
      .addCase(fetchStocks.rejected, (state) => {
        state.loading = "rejected";
        state.error = true;
      });
  },
});

export const { addToWatchlist, deleteFromWatchlist } = StocksSlice.actions;

export default StocksSlice.reducer;
