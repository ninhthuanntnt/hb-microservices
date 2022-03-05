import React, {useMemo} from "react";
import InfiniteScrollList from "../../../../components/InfiniteScrollList";
import {Card, Col, Typography} from "antd";
import {useParams} from "react-router-dom";
import {parseInt} from "lodash";

interface TagParam {
    id: string,
    name: string,
}

const PostsOfTag: React.FC = () => {
    const pathVariable: TagParam = useParams();
    const tagId: number = useMemo(() => parseInt(pathVariable.id), []);

    return (
        <Col md={15}>
            <Card>
                <Typography.Title>
                    #{pathVariable.name}
                </Typography.Title>
            </Card>
            <InfiniteScrollList url={`/hb-dmm/api/v1/posts?tagId=${tagId}&categoryId=1`}/>
        </Col>
    )
}

export default PostsOfTag;