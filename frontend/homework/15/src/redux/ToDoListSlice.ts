import { PayloadAction, createSlice } from "@reduxjs/toolkit";

export interface TodoItem {
  id: number;
  name: string;
  completed: boolean;
}

interface ToDotype {
  items: TodoItem[];
  searchTerm: string;
  newItemName: string;
}

const initialValue: ToDotype = {
  items: [],
  searchTerm: "",
  newItemName: "",
};

const ToDoListSlice = createSlice({
  name: "toDoList",
  initialState: initialValue,
  reducers: {
    addItem: (state, action: PayloadAction<string>) => {
      const newItem: TodoItem = {
        id: Date.now(),
        name: action.payload,
        completed: false
      };
      state.items.push(newItem);
    },
    deleteItem: (state, action: PayloadAction<number>) => {
      const index = state.items.findIndex((item) => item.id === action.payload);
      if (index !== -1) {
        state.items.splice(index, 1);
      }
    },
    search: (state, action: PayloadAction<string>) => {
      state.searchTerm = action.payload;
    },
    input: (state, action: PayloadAction<string>) => {
      state.newItemName = action.payload;
    },
    toggleCompletion: (state, action: PayloadAction<number>) => {
      const item = state.items.find((item) => item.id === action.payload);
      if (item) {
        item.completed = !item.completed;
      }
    },
    clearCompleted: (state) => {
      state.items = state.items.filter(item => item.completed === false);
    }
  },
});

export default ToDoListSlice.reducer;
export const { addItem, deleteItem, search, input, toggleCompletion, clearCompleted } = ToDoListSlice.actions;
