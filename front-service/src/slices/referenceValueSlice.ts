import { createSlice } from '@reduxjs/toolkit';
import { Journal } from '../components/Graph/Graph';

interface ReferenceValueState {
    data: ReferenceValueInterface[],
    messages: Journal | null,
    isAlert: boolean,
    alertMessage: string,
}

interface ReferenceValueInterface {
    id: string,
    reference: { value: string, id: string },
    name: string,
}

const initialState: ReferenceValueState = {
    data: [],
    messages: null,
    isAlert: false,
    alertMessage: '',
}

const referenceValueSlice = createSlice({
    name: 'referenceValue',
    initialState,
    reducers: {
        setReferenceValues: (state, action) => {
            state.data = action.payload;
        },
        setMessages: (state, action) => {
            state.messages = action.payload;
        },
        setAlert: (state, action) => {
            state.isAlert = action.payload.isOn;
            state.alertMessage = action.payload.sensor;
        }
    }
})

export const {
    setReferenceValues,
    setMessages,
    setAlert,
} = referenceValueSlice.actions;
export default referenceValueSlice.reducer;