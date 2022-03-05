import React from "react";
import InfiniteScrollList from "../../../../components/InfiniteScrollList";
import {Col} from "antd";

const FollowingPosts: React.FC = () => {

    return (
        <Col md={15}>
            <InfiniteScrollList url={"/hb-dmm/api/v1/user/posts/subscriptions?categoryId=1"}/>
        </Col>
    )
}

export default FollowingPosts;