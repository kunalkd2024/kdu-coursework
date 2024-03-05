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

/**
 * Redux slice for managing stocks data.
 *
 * @constant StocksSlice
 * @property {string} name - The name of the slice.
 * @property {stocksState} initialState - The initial state of the slice.
 * @property {Object} reducers - Reducers to handle actions.
 * @property {Function} reducers.addToWatchlist - Reducer to add a stock to the watchlist.
 * @property {Function} reducers.deleteFromWatchlist - Reducer to delete a stock from the watchlist.
 * @property {Function} extraReducers - Additional reducers to handle actions dispatched by other slices or thunks.
 */
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
