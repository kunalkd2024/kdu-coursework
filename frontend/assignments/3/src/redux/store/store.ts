import { configureStore } from "@reduxjs/toolkit";
import stocksReducer from "../slices/StocksSlice";
import userReducer from "../slices/UserSlice";
import portfolioReducer from "../slices/PortfolioSlice";

/**
 * Redux store configuration.
 *
 * @constant store
 * @type Redux store instance.
 * @property stocksData - Redux slice for managing stocks data.
 * @property userData - Redux slice for managing user data.
 * @property portfolioData - Redux slice for managing portfolio data.
 */
export const store = configureStore({
  reducer: {
    stocksData: stocksReducer,
    userData: userReducer,
    portfolioData: portfolioReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
