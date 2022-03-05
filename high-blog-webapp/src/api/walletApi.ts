import {xor} from "lodash";
import axiosClient from "./axiosClient";
import {WalletRes} from "../models/response/WalletRes";

const walletApi = {
    getWalletInfo(): Promise<WalletRes> {
        return axiosClient.get("/hb-payment/api/v1/user/wallets");
    }
}

export default walletApi;