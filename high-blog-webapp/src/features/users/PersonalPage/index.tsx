import React, {useEffect, useState} from "react";
import {Col, Row} from "antd";
import UserCard from "../../../components/UserCard";
import {UserRes} from "../../../models/response/UserRes";
import usersApi from "../../../api/usersApi";
import {useParams} from "react-router-dom";
import InfiniteScrollList from "../../../components/InfiniteScrollList";

interface Params {
    nickName: string
}

const PersonalPage: React.FC = () => {
    const {nickName}: Params = useParams();
    const [loading, setLoading] = useState(true);
    const [user, setUser] = useState<UserRes | undefined>();
    console.log(nickName)
    useEffect(() => {
        usersApi.getDetail(nickName)
            .then(value => {
                setUser(value);
                setLoading(false);
            })
            .catch(reason => {
                setLoading(false);
            });
    }, [])

    return (
        <Row style={{width: "100%"}}>
            <Col md={2} sm={0}>

            </Col>
            <Col md={5} sm={24}>
                <UserCard loading={loading} user={user}/>
            </Col>
            <Col md={14} sm={24}>
                <InfiniteScrollList url={`/hb-dmm/api/v1/posts?nickName=${nickName}&categoryId=1`}/>
            </Col>
        </Row>
    )
}

export default PersonalPage;