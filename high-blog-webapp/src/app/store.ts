import {Action, combineReducers, configureStore, ThunkAction} from '@reduxjs/toolkit';
import createSagaMiddleware from 'redux-saga';
import rootSaga from "./rootSaga";
import authReducer from "../features/auth/authSlice";
import {connectRouter, routerMiddleware} from "connected-react-router";
import {history} from "../utils/history";

const sageMiddleware = createSagaMiddleware();

const rootReducer = combineReducers({
    router: connectRouter(history),
    auth: authReducer,
})

export const store = configureStore({
    reducer: rootReducer,
    middleware: function (getDefaultMiddleware) {
        return getDefaultMiddleware().concat(sageMiddleware, routerMiddleware(history));
    }
});

sageMiddleware.run(rootSaga);

// Infer the `RootState` and `AppDispatch` types from the store itself
export type RootState = ReturnType<typeof store.getState>;
// Inferred type: {posts: PostsState, comments: CommentsState, users: UsersState}
export type AppDispatch = typeof store.dispatch;

export type AppThunk<ReturnType = void> = ThunkAction<ReturnType, RootState, unknown, Action<String>>;