import React from "react";
import InfiniteScrollList from "../../../../components/InfiniteScrollList";
import {Col} from "antd";

const FavoritePosts: React.FC = () => {

    return (
        <Col md={15}>
            <InfiniteScrollList url={"/hb-dmm/api/v1/user/favorite-posts"}/>
        </Col>
    )
}

export default FavoritePosts;