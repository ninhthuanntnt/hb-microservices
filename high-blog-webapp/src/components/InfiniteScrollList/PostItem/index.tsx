import React, {useCallback, useEffect} from "react";
import {Card, Tag, Typography} from "antd";
import {PostRes} from "../../../models/response/PostRes";
import {CaretUpOutlined, MessageOutlined, StarOutlined} from '@ant-design/icons';
import IconText from "../../IconText";
import {Link} from "react-router-dom";
import CardUserInfo from "../../CardUserInfo";
import moment from "moment";
import {YYYY_MM_DD_HH_MM_SS} from "../../../const/constant";
import {UriUtils} from "../../../utils/UriUtils";

export interface Props {
    post: PostRes
}

const PostItem: React.FC<Props> = ({post}) => {

    return (
        <Card key={post.id}
              actions={[
                  <IconText icon={<StarOutlined/>} text={post.numberOfFavorites + " favorites"}
                            key={"list-vertical-star-o"}/>,
                  <IconText icon={<CaretUpOutlined/>} text={post.numberOfVotes + " votes"}
                            key={"list-vertical-vote-o"}/>,
                  <IconText icon={<MessageOutlined/>} text={post.numberOfComments + " comments"}
                            key={"list-vertical-comment-o"}/>
              ]}
              title={
                  <CardUserInfo user={post.user}/>
              }>

            <Link to={`/posts/${post.id}/${UriUtils.getEncodeUrl(post.title)}`}>

                <Typography.Title level={4}>{post.title}</Typography.Title>
                <Typography.Paragraph ellipsis={{rows: 2, expandable: true, symbol: 'more'}}>
                    {post.summary}
                </Typography.Paragraph>
                <Typography.Paragraph type={"secondary"}>
                    {moment(post?.createdDate).format(YYYY_MM_DD_HH_MM_SS)}
                </Typography.Paragraph>
                {
                    post.tags?.map(tag => {
                        return (
                            <Tag key={tag.id}>
                                <Link to={`/tags/${tag.id}/${tag.name}/posts`}>
                                    {tag.name}
                                </Link>
                            </Tag>
                        )
                    })
                }
            </Link>
        </Card>
    )
}

export default PostItem;