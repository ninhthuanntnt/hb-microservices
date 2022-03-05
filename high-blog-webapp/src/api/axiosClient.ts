import axios, {AxiosError, AxiosResponse} from "axios";
import {BASE_URL} from "../const/constant";
import authApi from "./authApi";
import {useAppDispatch} from "../app/hooks";
import {authActions} from "../features/auth/authSlice";
import {NotificationUtils} from "../utils/NotificationUtils";

axios.defaults.withCredentials = true;
const axiosClient = axios.create({
    baseURL: BASE_URL,
    headers: {
        "Content-Type": "application/json"
    },
    withCredentials: true
});

axiosClient.interceptors.response.use(function (response: AxiosResponse) {
    if(response?.status === 302) {
        return null;
    }
    return response.data;

}, function (error: AxiosError) {
    const config: any = error.response?.config;
    let statusCode = error.response?.status;
    if (config.url.indexOf("refresh") > 0) {
        return Promise.reject(error);
    } else if(statusCode == 401){
        console.log("Start refresh");

        authApi
            .refreshToken()
            .then((response) => {
                console.log("recall", response);
                axiosClient(config);
            })
            .catch(error => {
                useAppDispatch()(authActions.logout());
                return;
            });
    }
    if([401, 403].indexOf(error.response?.status ?? 0) >= 0) {
        NotificationUtils.showUnAuthError();
    } else {
        NotificationUtils.showErrorFromResponse(error.response?.data);
    }
    return Promise.reject(error);
});
export default axiosClient;