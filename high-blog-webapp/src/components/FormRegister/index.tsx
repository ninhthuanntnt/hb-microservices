import React, {useCallback, useState} from "react";
import {Button, Col, Form, Input, Modal, Radio, Row} from "antd";
import registerApi from "../../api/registerApi";
import {NotificationUtils} from "../../utils/NotificationUtils";

const FormRegister: React.FC = () => {

    const [successModalVisible, setSuccessModalVisible] = useState<boolean>(false);

    const onFinishRegistration = useCallback((value) => {
        console.log(value);
        registerApi.register({
            nickName: value["nickName"],
            firstName: value["firstName"],
            lastName: value["lastName"],
            email: value["email"],
            genderType: value["genderType"],
            username: value["username"],
            password: value["password"],
            returnUrl: `${location.protocol}//${location.host}/register/activation`,
        }).then(() => {
            setSuccessModalVisible(true);
        }).catch((error) => {
            NotificationUtils.showErrorFromResponse(error);
        });
    }, [successModalVisible]);

    return (
        <>
            <Form layout={"vertical"} onFinish={onFinishRegistration}>
                <Form.Item name={"nickName"}
                           label={"Nickname:"}
                           rules={[
                               {required: true}
                           ]}>
                    <Input placeholder={"Enter your nickname"}/>
                </Form.Item>
                <Form.Item label={"Name"}>
                    <Row>
                        <Col flex={1}>
                            <Form.Item name={"firstName"}
                                       rules={[
                                           {required: true}
                                       ]}
                                       noStyle>
                                <Input placeholder={"Enter your first name"}/>
                            </Form.Item>
                        </Col>
                        <Col flex={1}>
                            <Form.Item name={"lastName"}
                                       rules={[
                                           {required: true}
                                       ]}
                                       noStyle>
                                <Input placeholder={"Enter your last name"}/>
                            </Form.Item>
                        </Col>
                    </Row>
                </Form.Item>
                <Form.Item name={"email"}
                           label={"Email"}
                           rules={[
                               {required: true}
                           ]}>
                    <Input type={"email"} placeholder={"Enter your email"}/>
                </Form.Item>
                <Form.Item name={"genderType"}
                           label={"Gender"}
                           rules={[
                               {required: true}
                           ]}>
                    <Radio.Group options={[
                        {label: "Male", value: "MALE"},
                        {label: "Female", value: "FEMALE"}
                    ]} defaultValue={"MALE"}/>
                </Form.Item>
                <Form.Item name={"username"}
                           label={"Username"}
                           rules={[
                               {required: true}
                           ]}>
                    <Input placeholder={"Enter your username"}/>
                </Form.Item>
                <Form.Item name={"password"}
                           label={"Password"}
                           rules={[
                               {required: true, min: 8}
                           ]}
                           hasFeedback>
                    <Input.Password placeholder={"Enter your password"}/>
                </Form.Item>
                <Form.Item name={"re-password"}
                           label={"Re-password"}
                           rules={[
                               {required: true},
                               ({getFieldValue}) => ({
                                   validator(_, value) {
                                       if (value && getFieldValue("password") === value) {
                                           return Promise.resolve();
                                       }

                                       return Promise.reject(new Error("The two passwords that you entered do not match"));
                                   }
                               })
                           ]}
                           hasFeedback>
                    <Input.Password placeholder={"Enter your re-password"}/>
                </Form.Item>
                <Form.Item>
                    <Button htmlType={"submit"}>Submit</Button>
                </Form.Item>
            </Form>
            <Modal visible={successModalVisible} onCancel={() => setSuccessModalVisible(false)}>
                Email registration sent. Please check your email to confirm registration
            </Modal>
        </>
    );
}

export default FormRegister;