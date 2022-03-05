export interface UserTransactionRes {
    id: number,
    userId: number,
    transactionNo: string,
    amount: number,
    status: string,
    paymentMethod: string,
    paymentType: string,
    receiver: boolean,
    balance: number,
    createdDate: string,
}