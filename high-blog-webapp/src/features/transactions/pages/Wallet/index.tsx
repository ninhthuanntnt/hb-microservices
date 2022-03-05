import React, {useCallback, useEffect, useState} from "react";
import {Button, Card, Col, Divider, Form, Input, InputNumber, List, Row, Space, Tag, Typography} from "antd";
import walletApi from "../../../../api/walletApi";
import {WalletRes} from "../../../../models/response/WalletRes";
import {UserTransactionRes} from "../../../../models/response/UserTransactionRes";
import transactionsApi from "../../../../api/transactionsApi";
import {BasePaginationRes} from "../../../../models/response/BasePaginationRes";
import {PayPalButton} from "react-paypal-button-v2";
import paymentApi from "../../../../api/paymentApi";
import "./index.less";

const Wallet: React.FC = () => {
    const [walletInfo, setWalletInfo] = useState<WalletRes>();
    const [depositAmount, setDepositAmount] = useState<number>(0);
    const [withdrawAmount, setWithdrawAmount] = useState<number>(0);
    const [emailToWithdraw, setEmailToWithdraw] = useState<string>();

    const [pageUserTransactions, setPageUserTransactions] = useState<BasePaginationRes<UserTransactionRes>>({
        page: 1,
        pageSize: 10,
        totalPage: 10,
        items: [],
        totalItems: 0
    });

    useEffect(() => {
        walletApi.getWalletInfo()
            .then(walletRes => setWalletInfo(walletRes));
    }, []);

    useEffect(() => {
        transactionsApi.fetchUserTransactions(pageUserTransactions.page)
            .then(basePaginationRes => setPageUserTransactions(basePaginationRes))
    }, []);

    const changePage = useCallback((page: number) => {
        transactionsApi.fetchUserTransactions(page)
            .then(basePaginationRes => {
                setPageUserTransactions(basePaginationRes);
            });
    }, [pageUserTransactions]);

    const createOrder = useCallback(async (data: any, action: any, amount: number) => {
        let res = await paymentApi.createPaypalPayment(depositAmount);
        return res.paymentId;
    }, [depositAmount])

    const onApproveCreateOrder = useCallback(async (data: any, actions: any) => {
        await paymentApi.executePaypalPaymentRes(data.orderID);
        window.location.reload();
    }, [depositAmount]);

    const onCancelCreateOrder = useCallback(async (data: any, action: any) => {
        console.log("CANCELED")
        await paymentApi.cancelPaypalPaymentRes(data.orderID);
        window.location.reload();
    }, [depositAmount]);

    const onFinishWithdraw = useCallback(async (values) => {
        const {email, amount} = values;
        await paymentApi.withdrawPaypalPayment(email, amount);
    }, [emailToWithdraw, withdrawAmount])

    const renderUserTransaction = (userTransactionRes: UserTransactionRes) => {
        return (
            <List.Item>
                <Row justify={"space-between"} align={"middle"}>
                    <Space align={"baseline"}>
                        <Typography.Paragraph>
                            {userTransactionRes.transactionNo}
                        </Typography.Paragraph>
                        <Tag>
                            {userTransactionRes.status}
                        </Tag>
                    </Space>
                    <Typography.Paragraph>
                        {userTransactionRes.balance.toLocaleString("en-US", {
                            style: "currency",
                            currency: "USD"
                        })}
                        <Typography.Text type={userTransactionRes.receiver ? "success" : "danger"}>
                            {userTransactionRes.receiver ? " +" : " -"}
                            {userTransactionRes.amount.toLocaleString("en-US", {
                                style: "currency",
                                currency: "USD"
                            })}
                        </Typography.Text>
                    </Typography.Paragraph>
                </Row>
            </List.Item>
        )
    }

    return (
        <Col md={20} sm={16} xs={24}>
            <Row>
                <Col offset={1} md={7}>
                    <Card title={"Balance"} className={"card-height-common"}>
                        <Row style={{width: "100%", height: "100%"}} justify={"center"} align={"middle"}>
                            <Typography.Title>
                                {walletInfo?.balance.toLocaleString("en-US", {
                                    style: "currency",
                                    currency: "USD"
                                })}
                            </Typography.Title>
                        </Row>
                    </Card>
                </Col>

                <Col offset={1} md={7}>
                    <Card title={"Deposit"} className={"card-height-common"}>
                        <Space direction={"vertical"} style={{width: "100%"}}>
                            <InputNumber prefix="$"
                                         style={{width: '100%'}}
                                         placeholder={"Amount"}
                                         onChange={(value: string) => setDepositAmount(parseInt(value))}/>
                            <Typography.Title>
                                <PayPalButton
                                    createOrder={(data: any, action: any) => createOrder(data, action, depositAmount)}
                                    onApprove={(data: any, action: any) => onApproveCreateOrder(data, action)}
                                    onError={async (data: any, action: any) => {
                                        await onCancelCreateOrder(data, action)
                                    }}
                                    onCancel={async (data: any, action: any) => {
                                        await onCancelCreateOrder(data, action)
                                    }}
                                    style={{
                                        layout: "horizontal",
                                        color: "blue",
                                        shape: "pill",
                                        label: "paypal"
                                    }}
                                    options={{
                                        clientId: "ASxmyLo7w_THZRCs21HDBeyWdqOM0syn7Lz8AHVGxInDEkN2RfZniKS6jQmFvJ_-V7mmZbwJIbtm7ds4",
                                        currency: "USD"
                                    }}/>
                            </Typography.Title>
                        </Space>
                    </Card>
                </Col>

                <Col offset={1} md={7}>
                    <Card title={"Withdraw"} className={"card-height-common"}>
                        <Form name={"withdraw-form"} onFinish={onFinishWithdraw}>
                            <Form.Item name={"email"} rules={[
                                {
                                    required: true,
                                },
                                {
                                    type: "email"
                                }
                            ]}>
                                <Input placeholder={"Email paypal"}
                                       onChange={event => setEmailToWithdraw(event.target.value)}/>
                            </Form.Item>
                            <Form.Item name={"amount"} rules={[
                                {
                                    required: true,
                                },
                                {
                                    type: "number",
                                    max: walletInfo?.balance
                                }
                            ]}>
                                <InputNumber prefix="$"
                                             style={{width: '100%'}}
                                             placeholder={"Amount"}
                                             onChange={(value: string) => setWithdrawAmount(parseInt(value))}/>
                            </Form.Item>
                            <Button htmlType={"submit"} style={{width: "100%"}}>
                                Withdraw
                            </Button>
                        </Form>
                    </Card>
                </Col>
            </Row>
            <Row>
                <Col offset={1} md={23}>
                    <Divider/>
                </Col>
            </Row>
            <Row>
                <Col offset={1} md={23}>
                    <List itemLayout={"vertical"}
                          pagination={{
                              onChange: changePage,
                              total: pageUserTransactions.totalItems,
                              current: pageUserTransactions.page,
                              showSizeChanger: false,
                          }}
                          dataSource={pageUserTransactions.items}
                          renderItem={renderUserTransaction}>
                    </List>
                </Col>
            </Row>
        </Col>
    )
}

export default Wallet;