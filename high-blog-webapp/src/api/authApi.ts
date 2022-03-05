import {ProfileRes} from "../models/response/ProfileRes";
import axiosClient from "./axiosClient";
import {LogoutRes} from "../models/response/LogoutRes";
import {LoginRequestRes} from "../models/response/LoginRequestRes";
import {LoginReq} from "../models/request/LoginReq";

const authApi = {
    getProfile(): Promise<ProfileRes> {
        return axiosClient.get("/hb-uaa/api/v1/profiles", {withCredentials: true});
    },
    refreshToken(): Promise<void> {
        return axiosClient.get("/api/v1/auth/refresh", {withCredentials: true});
    },
    logout(): Promise<LogoutRes> {
        return axiosClient.get("/api/v1/auth/logout", {withCredentials: true});
    },
    getLoginUrl(): Promise<LoginRequestRes> {
        return axiosClient.get("/api/v1/auth/login?redirectUri=http://127.0.0.1:3000/login", {withCredentials: true});
    },
    login(loginReq: LoginReq): Promise<void> {
        return axiosClient.post("/api/v1/auth/login", loginReq, {withCredentials: true});
    }
}

export default authApi;