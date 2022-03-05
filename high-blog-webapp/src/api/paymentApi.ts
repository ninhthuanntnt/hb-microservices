import axiosClient from "./axiosClient";
import {PaymentCreateRes} from "../models/response/PaymentCreateRes";

const paymentApi = {
    createPaypalPayment(amount: number): Promise<PaymentCreateRes> {
        return axiosClient.post("/hb-payment/api/v1/user/deposit/paypal/create", {amount});
    },
    executePaypalPaymentRes(paymentId: string): Promise<void> {
        return axiosClient.post("/hb-payment/api/v1/user/deposit/paypal/execute", {paymentId});
    },
    cancelPaypalPaymentRes(paymentId: string): Promise<void> {
        return axiosClient.post("/hb-payment/api/v1/user/deposit/paypal/cancel", {paymentId});
    },
    withdrawPaypalPayment(email: string, amount: number): Promise<void> {
        return axiosClient.post("/hb-payment/api/v1/user/withdrawal/paypal", {email, amount});
    }
}

export default paymentApi;