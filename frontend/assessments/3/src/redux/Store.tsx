import { configureStore } from "@reduxjs/toolkit";
import RoomSlice from "./RoomSlice";

export const store = configureStore({
    reducer: {
        rooms: RoomSlice,
    },
})

export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch