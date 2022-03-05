import React, {useCallback, useEffect, useState} from "react";
import {Card, Col, Divider, Empty, Pagination, Row, Typography} from "antd";
import {BasePaginationRes} from "../../../models/response/BasePaginationRes";
import {TagRes} from "../../../models/response/TagRes";
import tagsApi from "../../../api/tagsApi";
import {history} from "../../../utils/history";
import Search from "antd/es/input/Search";

const TagList: React.FC = () => {
    const [searchValue, setSearchValue] = useState<string>("");
    const [pageTag, setPageTag] = useState<BasePaginationRes<TagRes>>({
        items: [],
        totalItems: 0,
        totalPage: 1,
        page: 1,
        pageSize: 12,
    });

    useEffect(() => {
        tagsApi.searchTag("", pageTag.page, 12)
            .then(value => {
                setPageTag(value);
            });
    }, []);

    const onSearch = useCallback((tagName) => {
        tagsApi.searchTag(tagName, 1, 12)
            .then(value => {
                setPageTag(value);
            });
        history.push(`/tags?keyword=${tagName}`);
        setSearchValue(tagName);
    }, [pageTag, searchValue]);

    const onChangePage = (page) => {
        tagsApi.searchTag(searchValue, page, 12)
            .then(value => {
                setPageTag(value);
            });
    }

    return (
        <Col md={15}>
            <Row justify={"center"}>
                <Col offset={4} md={16}>
                    <Search placeholder="Input search text" onSearch={onSearch} enterButton/>
                </Col>
            </Row>
            <Divider/>
            <Row justify={"space-around"}>
                {pageTag.items.length > 0 ?
                    pageTag.items.map(tag => {
                        return (
                            <Col md={7} sm={11} key={tag.id} style={{padding: "1em 0"}}>
                                <Card onClick={() => history.push(`/tags/${tag.id}/${tag.name}/posts`)}
                                      style={{cursor: "pointer"}}>
                                    <Typography.Title level={3}
                                                      ellipsis={{
                                                          rows: 1,
                                                      }}
                                                      style={{textAlign: "center"}}
                                                      aria-label={tag.name}>
                                        #{tag.name}
                                    </Typography.Title>
                                </Card>
                            </Col>
                        )
                    }) : (
                        <Empty/>
                    )
                }
            </Row>
            <Row justify={"center"}>
                <Pagination onChange={onChangePage}
                            total={pageTag.totalItems}
                            current={pageTag.page}
                            showSizeChanger={false}/>
            </Row>
        </Col>
    )
}

export default TagList;