import React, {useState} from "react";
import {useAppDispatch} from "../../../../app/hooks";
import {authActions} from "../../authSlice";
import {LoginReq} from "../../../../models/request/LoginReq";

const LoginTemp: React.FC = () => {
    const dispatch = useAppDispatch();
    let redirectUri: string = window.location.protocol + "//" + window.location.host + window.location.pathname;
    let searchParam: URLSearchParams = new URLSearchParams(window.location.search);
    let code: String = searchParam.get("code") || "";

    let loginReq: LoginReq = {
        code,
        redirectUri
    }

    console.log("dispatch start login");
    dispatch(authActions.startLogin(loginReq));

    return (
        <></>
    )
}

export default LoginTemp;