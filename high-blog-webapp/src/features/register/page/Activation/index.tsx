import React, {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import useSearchParam from "../../../../app/useSearchParam";
import registerApi from "../../../../api/registerApi";
import {parseInt} from "lodash";
import {Simulate} from "react-dom/test-utils";
import {Col, Result, Row, Skeleton} from "antd";

interface Param {
    id: string
}

const Activation: React.FC = () => {
    let params: Param = useParams();
    let confirmationId = parseInt(params.id);
    let confirmationCode: string = useSearchParam("code");
    let [loading, setLoading] = useState<boolean>(true);
    let [activatedSuccess, setActivatedSuccess] = useState<boolean>(false);
    useEffect(() => {
        registerApi
            .activation(confirmationId, confirmationCode)
            .then(() => {
                setLoading(false);
                setActivatedSuccess(true);
            })
            .catch(reason => {
                setLoading(false);
                setActivatedSuccess(false);
            })
    }, [])
    return (
        <Row justify={"center"} align={"middle"} style={{width: "100%"}}>
            <Skeleton loading={loading}>
                <Result
                    status={activatedSuccess ? "success" : "error"}
                    title={activatedSuccess ? "Successfully activated your account" : "Cannot activate your account"}
                />
            </Skeleton>
        </Row>
    );
}

export default Activation;