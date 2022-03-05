import axiosClient from "./axiosClient";
import {UserRes} from "../models/response/UserRes";

const tagsApi = {
    getDetail(nickName: string): Promise<UserRes> {
        return axiosClient.get(`/hb-dmm/api/v1/users/${nickName}`);
    }
}

export default tagsApi;