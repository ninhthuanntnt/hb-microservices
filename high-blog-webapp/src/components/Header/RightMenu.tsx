import React, {useCallback, useState} from "react";
import {Avatar, Dropdown, Menu, Modal, Row, Typography} from "antd";
import {useAppDispatch, useAppSelector} from "../../app/hooks";

import {BellOutlined, UserOutlined} from '@ant-design/icons';
import {authActions} from "../../features/auth/authSlice";
import "./index.less";
import authApi from "../../api/authApi";
import NotificationList from "../NotificationList";
import FormRegister from "../FormRegister";

const RightMenu: React.FC = () => {
    const dispatch = useAppDispatch();
    const isLoggedIn = useAppSelector(state => state.auth.isLoggedIn);
    const currentUser = useAppSelector(state => state.auth.currentUser);
    const [registerVisible, setRegisterVisible] = useState(false);
    const onClickLogoutBtn = () => {
        dispatch(authActions.logout());
    }

    const requestLogin = useCallback(() => {
        authApi
            .getLoginUrl()
            .then(value => {
                window.location.href = value.url;
            })
    }, []);

    const dropdownMenu = () => {
        return (
            <Menu>
                <Menu.Item onClick={onClickLogoutBtn}>
                    Logout
                </Menu.Item>
            </Menu>
        )
    }

    const showRegisterForm = useCallback(() => {
        setRegisterVisible(true);
    }, [registerVisible]);

    const hideRegisterForm = useCallback(() => {
        setRegisterVisible(false);
    }, [registerVisible]);

    return (
        <>
            <Menu mode={"horizontal"}>
                {isLoggedIn || (
                    <Menu.Item key={"login"}>
                        <a onClick={requestLogin}>
                            LOGIN
                        </a>
                    </Menu.Item>
                )}
                {isLoggedIn || (
                    <Menu.Item key={"register"} onClick={showRegisterForm}>
                        REGISTER
                    </Menu.Item>
                )}
                {!isLoggedIn || (
                    <Menu.Item key={"logout"}>
                        <a onClick={() => {
                            dispatch(authActions.logout())
                        }}>
                            LOGOUT
                        </a>
                    </Menu.Item>
                )}

                {
                    isLoggedIn && (
                        <Dropdown overlay={<NotificationList/>} placement={"bottomRight"}>
                            <Menu.Item key={"notification"}>
                                <BellOutlined style={{fontSize: "1.2em"}}/>
                            </Menu.Item>
                        </Dropdown>
                    )
                }
                {
                    isLoggedIn && (
                        <Row align={"middle"} justify={"end"}>
                            <div className={"header-user-right"}>
                                <Avatar size={32} icon={<UserOutlined/>}/>
                                <Typography.Text className={"nickname"}>
                                    {currentUser?.nickName}
                                </Typography.Text>
                            </div>
                        </Row>
                    )
                }
            </Menu>

            <Modal title={"Register"}
                   visible={registerVisible}
                   onCancel={hideRegisterForm}
                   footer={null}>
                <FormRegister/>
            </Modal>
        </>
    )
}

export default RightMenu;