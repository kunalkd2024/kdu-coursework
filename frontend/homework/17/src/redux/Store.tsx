import { configureStore } from "@reduxjs/toolkit";
import ProductSlice from "./ProductSlice";
import SnackbarSlice from "./SnackbarSlice";

export const store = configureStore({
    reducer: {
        products: ProductSlice,
        snackbar: SnackbarSlice
    },
})

export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch