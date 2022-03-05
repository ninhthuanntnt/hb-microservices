import {createSlice, PayloadAction} from "@reduxjs/toolkit";
import {ProfileRes} from "../../models/response/ProfileRes";
import {LoginReq} from "../../models/request/LoginReq";

export interface AuthState {
    isLoggedIn: boolean,
    logging: boolean,
    currentUser?: ProfileRes,
}

const initialState: AuthState = {
    isLoggedIn: false,
    logging: false,
    currentUser: undefined,
}

const authSlice = createSlice({
    name: "auth",
    initialState,
    reducers: {
        startLogin(state: AuthState, action: PayloadAction<LoginReq>) {
            state.logging = true;
        },
        finishLogin(state: AuthState) {
            state.logging = false;
            state.isLoggedIn = true;
        },
        getProfile(state: AuthState) {
            state.logging = false;
        },
        loadProfile(state: AuthState, action: PayloadAction<ProfileRes>) {
            console.log(action);
            state.currentUser = action.payload;
            state.isLoggedIn = true;
            state.logging = false;
        },
        logout(state: AuthState) {
            state.currentUser = undefined;
            state.isLoggedIn = false;
        }
    }
});
export const authActions = authSlice.actions;

const authReducer = authSlice.reducer;

export default authReducer;