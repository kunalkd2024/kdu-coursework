import { configureStore } from "@reduxjs/toolkit";
import ToDoListReducer from "./ToDoListSlice";

export const Store = configureStore({
  reducer: {
    ToDoList: ToDoListReducer,
  },
});

export type RootState = ReturnType<typeof Store.getState>;
