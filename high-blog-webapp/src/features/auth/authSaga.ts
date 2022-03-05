import {call, fork, put, take} from "redux-saga/effects";
import {authActions} from "./authSlice";
import authApi from "../../api/authApi";
import {ProfileRes} from "../../models/response/ProfileRes";
import {isEmpty} from "lodash";
import {LogoutRes} from "../../models/response/LogoutRes";
import {select, TakeEffect} from "@redux-saga/core/effects";
import {history} from "../../utils/history";
import {LoginReq} from "../../models/request/LoginReq";

function* handleLogout() {
    let logoutRes: LogoutRes = yield call(authApi.logout);
    window.location.href = logoutRes.redirectUrl + "?redirectUrl=" + window.location.href;
}

function* handleLoadProfile() {
    const res: ProfileRes = yield call(authApi.getProfile);
    if (!isEmpty(res)) {
        yield put(authActions.loadProfile(res));
    }
}

function* watchAuthFlow() {
    while (true) {

        if(!window.location.href.includes("login?code")) {
            yield call(handleLoadProfile)
        } else {
            let isLoggedIn: boolean = yield select(state => state.auth.isLoggedIn);
            if (!isLoggedIn) {
                console.log("need login");
                let action: TakeEffect = yield take(authActions.startLogin.type);
                let loginReq: any = action.payload;
                yield call(authApi.login, loginReq);
                yield put(authActions.finishLogin)

                yield call(handleLoadProfile);

                history.push("/");
            }
        }

        yield take(authActions.logout.type);
        yield call(handleLogout);
    }
}

export default function* authSaga() {
    yield fork(watchAuthFlow);
}