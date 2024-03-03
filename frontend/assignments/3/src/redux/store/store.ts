import { configureStore } from "@reduxjs/toolkit";
import stocksReducer from "../slices/StocksSlice";
import userReducer from "../slices/UserSlice";
import portfolioReducer from "../slices/PortfolioSlice";

export const store = configureStore({
  reducer: {
    stocksData: stocksReducer,
    userData: userReducer,
    portfolioData: portfolioReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
