import React from "react";
import InfiniteScrollList from "../../../../components/InfiniteScrollList";
import {Col} from "antd";

const Trending: React.FC = () => {

    return (
        <Col md={15}>
            <InfiniteScrollList url={"/hb-dmm/api/v1/posts?categoryId=1"}/>
        </Col>
    )
}

export default Trending;