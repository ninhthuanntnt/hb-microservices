import axiosClient from "./axiosClient";
import {RegisterReq} from "../models/request/RegisterReq";

const registerApi = {
    register(registerReq: RegisterReq): Promise<void> {
        return axiosClient.post("/hb-uaa/api/v1/register", registerReq);
    },
    activation(id: number, code: string): Promise<void> {
        return axiosClient.post(`/hb-uaa/api/v1/register/activation/${id}`, null, {
            params: {code}
        });
    }
}

export default registerApi;