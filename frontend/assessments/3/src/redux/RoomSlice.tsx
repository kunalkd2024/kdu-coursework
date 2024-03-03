import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { Rooms } from "../types/type";
import { getRooms } from "../thunks/getRooms";

interface RoomType {
  rooms: Rooms[];
  loading: boolean;
  message: string;
  error?: string;
}

const initialState: RoomType = {
  rooms: [],
  message: "",
  loading: false,
};

export const roomSlice = createSlice({
  name: "rooms",
  initialState,
  reducers: {
    setLoading(state, action) {
      state.loading = action.payload as boolean;
    },
    setMessage(state, action) {
      state.message = action.payload as string;
    },
    setError(state, action) {
      state.error = action.payload as string;
    },
  },
  extraReducers(builder) {
    builder
      .addCase(getRooms.pending, (state) => {
        state.loading = true;
        state.message = "Result is pending.";
        state.error = "";
      })
      .addCase(getRooms.fulfilled, (state, action: PayloadAction<Rooms[]>) => {
        state.loading = false;
        state.error = "";
        state.rooms = action.payload;
        state.message = "Result is fulfilled.";
      });
  },
});

export default roomSlice.reducer;
export const { setLoading, setMessage, setError } = roomSlice.actions;

