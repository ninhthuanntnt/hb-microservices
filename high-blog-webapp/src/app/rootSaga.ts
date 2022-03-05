import {all} from 'redux-saga/effects';
import authSaga from "../features/auth/authSaga";

function* logSaga() {
    console.log("ROOT SAGA");
}

export default function* rootSaga() {
    yield all([logSaga(), authSaga()])
}