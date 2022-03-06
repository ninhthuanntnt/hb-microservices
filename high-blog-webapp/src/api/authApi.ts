import {ProfileRes} from "../models/response/ProfileRes";
import axiosClient from "./axiosClient";
import {LogoutRes} from "../models/response/LogoutRes";
import {LoginRequestRes} from "../models/response/LoginRequestRes";
import {LoginReq} from "../models/request/LoginReq";
import {UriUtils} from "../utils/UriUtils";

const authApi = {
    getProfile(): Promise<ProfileRes> {
        return axiosClient.get("/hb-uaa/api/v1/profiles", {withCredentials: true});
    },
    refreshToken(): Promise<void> {
        return axiosClient.get("/api/v1/auth/refresh", {
            withCredentials: true,
            headers: {
                accessToken: localStorage.getItem("accessToken") ?? "",
                refreshToken: localStorage.getItem("refreshToken") ?? ""
            }
        });
    },
    logout(): Promise<LogoutRes> {
        return axiosClient.get("/api/v1/auth/logout", {
            withCredentials: true,
            headers: {
                accessToken: localStorage.getItem("accessToken") ?? "",
                refreshToken: localStorage.getItem("refreshToken") ?? ""
            }
        });
    },
    getLoginUrl(): Promise<LoginRequestRes> {
        return axiosClient.get(`/api/v1/auth/login?redirectUri=${UriUtils.getCurrentUrl()}/login`, {withCredentials: true});
    },
    login(loginReq: LoginReq): Promise<any> {
        return axiosClient.post("/api/v1/auth/login", loginReq, {withCredentials: true});
    }
}

export default authApi;