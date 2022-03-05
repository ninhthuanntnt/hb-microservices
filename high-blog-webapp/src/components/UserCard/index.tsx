import React, {useCallback, useEffect, useState} from "react";
import {Button, Card, Col, Divider, Form, Image, InputNumber, Modal, Row} from "antd";
import CardUserInfo from "../CardUserInfo";
import {BellFilled, BellOutlined} from "@ant-design/icons";
import {useAppSelector} from "../../app/hooks";
import {UserRes} from "../../models/response/UserRes";
import subscriptionsApi from "../../api/subscriptionsApi";
import donationApi from "../../api/donationApi";
import {ColorMapChar} from "../../const/colorMapper";
import {BASE_URL} from "../../const/constant";
import {NotificationUtils} from "../../utils/NotificationUtils";

interface Props {
    user?: UserRes,
    loading: boolean,
}

const UserCard: React.FC<Props> = ({user, loading}) => {
    const currentUser = useAppSelector(state => state.auth.currentUser);
    const [followedUser, setFollowedUser] = useState<boolean | undefined>(user?.followed);
    const [notifiedUser, setNotifiedUser] = useState<boolean | undefined>(user?.notified);
    const [errorUserCoverImage, setErrorCoverImage] = useState<boolean>(true);
    const [modalVisible, setModalVisible] = useState<boolean>(false);

    useEffect(()=> {
        setFollowedUser(user?.followed);
        setNotifiedUser(user?.notified);
    }, [user]);

    const handleClickNotify = useCallback(() => {
        if (user) {
            subscriptionsApi.switchNotified(user?.nickName)
                .then(() => {
                    setNotifiedUser(!notifiedUser);
                });
        }
    }, [notifiedUser, user]);

    const handleClickFollow = useCallback(() => {
        if (user) {
            if (followedUser)
                subscriptionsApi.deleteSubscription(user?.nickName)
                    .then(() => {
                        setFollowedUser(false);
                    });
            else
                subscriptionsApi.createSubscription(user?.nickName)
                    .then(() => {
                        setFollowedUser(true);
                    });
        }
    }, [followedUser, user]);

    const onFinishDonation = useCallback(async (values) => {
        if (user) {
            await donationApi.donateByNickName(user.nickName, values.amount);
            NotificationUtils.showSuccess("donated");
            setModalVisible(false);
        }
    }, [user]);

    const coverImage = () => {
        if (errorUserCoverImage) {
            return (
                <div style={{
                    backgroundColor: user ? ColorMapChar[user?.firstName?.charAt(0)] : '#ccc',
                    height: 20
                }}>
                </div>
            )
        } else {
            return (
                <Image src={`${BASE_URL}/${user?.backgroundPath}`} onError={() => setErrorCoverImage(true)}/>
            )
        }
    }

    return (
        <Card style={{width: "100%"}}
              loading={loading}
              cover={coverImage()}>
            <CardUserInfo user={user}/>

            <Divider plain>
            </Divider>
            {
                currentUser?.nickName != user?.nickName
                && (
                    <>
                        <Row align={"middle"}>
                            <Col md={followedUser ? 20 : 24}>
                                <Button block color={"#ccc"} onClick={handleClickFollow}>
                                    {followedUser ? "Unfollow" : "Follow"}
                                </Button>
                            </Col>
                            {
                                followedUser && (
                                    <Col offset={1} md={3}>
                                        {
                                            notifiedUser ? (
                                                <BellFilled style={{fontSize: 20}}
                                                            onClick={handleClickNotify}/>
                                            ) : (
                                                <BellOutlined style={{fontSize: 20}}
                                                              onClick={handleClickNotify}/>
                                            )
                                        }
                                    </Col>
                                )
                            }
                        </Row>
                        <Divider/>
                        <Row>
                            <Col md={24}>
                                <Button type={"primary"} block onClick={() => setModalVisible(true)}>
                                    Donate
                                </Button>
                                <Modal title={"Donation"}
                                       visible={modalVisible}
                                       footer={null}
                                       onCancel={() => setModalVisible(false)}>
                                    <Form name={"donation"} layout={"inline"}
                                          onFinish={onFinishDonation}
                                          style={{width: "100%"}}>
                                        <Row style={{width: "100%"}}>
                                            <Col md={18}>
                                                <Form.Item name={"amount"}
                                                           rules={[
                                                               {
                                                                   required: true
                                                               }
                                                           ]}>
                                                    <InputNumber placeholder={"Enter amount to donate"}
                                                                 style={{width: "100%"}}/>
                                                </Form.Item>
                                            </Col>

                                            <Col md={5} offset={1}>
                                                <Button htmlType={"submit"} style={{width: "100%"}}>
                                                    Donate
                                                </Button>
                                            </Col>
                                        </Row>
                                    </Form>
                                </Modal>
                            </Col>
                        </Row>
                    </>
                )
            }
        </Card>
    )
}

export default UserCard;