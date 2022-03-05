import axiosClient from "./axiosClient";

const donationApi = {
    donateByNickName(nickName: string, amount: number): Promise<void> {
        return axiosClient.post("/hb-payment/api/v1/user/donations", {nickName, amount});
    }
}

export default donationApi;