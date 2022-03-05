import React, {useCallback, useEffect, useState} from "react";
import {Avatar, Card, Empty, Skeleton} from "antd";
import {PostRes} from "../../models/response/PostRes";
import InfiniteScroll from "react-infinite-scroll-component";
import PostItem from "./PostItem";
import {BasePaginationRes} from "../../models/response/BasePaginationRes";
import postsApi from "../../api/postsApi";

export interface Props {
    url: string
}

const InfiniteScrollList: React.FC<Props> = ({url}) => {
    const [firstLoading, setFirstLoading] = useState<boolean>(true);
    const [data, setData] = useState<PostRes[]>([]);
    const [page, setPage] = useState<number>(1);
    const [totalPage, setTotalPage] = useState<number>(0);


    const appendData = useCallback(async () => {
        let posts: BasePaginationRes<PostRes> = await postsApi.getByUrl(url, page);

        setData([...data, ...posts.items]);
        setPage(page + 1);
        setTotalPage(posts.totalPage)
    }, [data]);

    useEffect(() => {
        appendData()
            .then(value => {
                setFirstLoading(false);
            })
            .catch(reason => {
                setFirstLoading(false);
            });
    }, []);

    return (
        <>
            {
                firstLoading
                    ? (
                        <>
                            <Skeleton avatar paragraph={{rows: 4}}/>
                            <Skeleton avatar paragraph={{rows: 4}}/>
                            <Skeleton avatar paragraph={{rows: 4}}/>
                            <Skeleton avatar paragraph={{rows: 4}}/>
                        </>
                    )
                    : (
                        data.length > 0
                            ? (
                                <InfiniteScroll
                                    dataLength={data.length}
                                    next={appendData}
                                    hasMore={page <= totalPage}
                                    loader={<Skeleton loading={true} avatar active>
                                        <Card.Meta
                                            avatar={<Avatar src="https://joeschmoe.io/api/v1/random"/>}
                                            title="Card title"
                                            description="This is the description"
                                        />
                                    </Skeleton>}
                                >
                                    {data.map((post, index) => {
                                        return (
                                            <PostItem post={post}/>
                                        )
                                    })}
                                </InfiniteScroll>
                            )
                            : (
                                <Empty />
                            )
                    )
            }
        </>
    )
}

export default InfiniteScrollList;