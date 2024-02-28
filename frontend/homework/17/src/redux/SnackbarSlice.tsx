import { createSlice,PayloadAction } from "@reduxjs/toolkit";

interface errorType{
    message: string | null;
}

const initialState: errorType = {
    message: null
}

export const SnackbarSlice = createSlice({
    name: 'error',
    initialState,
    reducers: {
        setError(state, action: PayloadAction<string>){
            state.message = action.payload;
        },
        clearError(state){
            state.message = null;
        }
    }
})

export const {setError, clearError} = SnackbarSlice.actions
export default SnackbarSlice.reducer