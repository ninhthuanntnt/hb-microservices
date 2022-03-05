import React from "react";
import InfiniteScrollList from "../../../../components/InfiniteScrollList";
import {Col} from "antd";

const NewFeed: React.FC = () => {

    return (
        <Col md={15}>
            <InfiniteScrollList url={"/hb-dmm/api/v1/recommendation/newsfeed"}/>
        </Col>
    )
}

export default NewFeed;