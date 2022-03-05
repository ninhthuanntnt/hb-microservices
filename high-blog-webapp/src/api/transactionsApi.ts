import axiosClient from "./axiosClient";
import {UserTransactionRes} from "../models/response/UserTransactionRes";
import {BasePaginationRes} from "../models/response/BasePaginationRes";

const transactionsApi = {
    fetchUserTransactions(page: number, pageSize: number = 10): Promise<BasePaginationRes<UserTransactionRes>> {
        return axiosClient.get(`/hb-payment/api/v1/user/user-transactions?page=${page}&pageSize=${pageSize}`);
    }
}

export default transactionsApi;