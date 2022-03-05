import axiosClient from "./axiosClient";

const subscriptionsApi = {
    createSubscription(nickname: string): Promise<void> {
        return axiosClient.post(`/hb-dmm/api/v1/user/subscriptions/users/${nickname}`);
    },
    deleteSubscription(nickname: string): Promise<void> {
        return axiosClient.delete(`/hb-dmm/api/v1/user/subscriptions/users/${nickname}`);
    },
    switchNotified(nickname: string): Promise<void> {
        return axiosClient.put(`/hb-dmm/api/v1/user/subscriptions/users/${nickname}/notified/switch`);
    }
}

export default subscriptionsApi;