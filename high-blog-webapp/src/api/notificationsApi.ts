import {xor} from "lodash";
import axiosClient from "./axiosClient";
import {NotificationRes} from "../models/response/NotificationRes";
import {BasePaginationRes} from "../models/response/BasePaginationRes";

const notificationsApi = {
    getListNotification(page: number, pageSize: number = 10): Promise<BasePaginationRes<NotificationRes>> {
        return axiosClient.get("/hb-notification/api/v1/user/notifications", {
            params: {
                page,
                pageSize
            }
        });
    }
}

export default notificationsApi;