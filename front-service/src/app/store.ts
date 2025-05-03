import { configureStore } from '@reduxjs/toolkit';
import referenceReducer from '../slices/referenceValueSlice'

export const store = configureStore({
    reducer: {
        reference: referenceReducer,
    }
})

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;