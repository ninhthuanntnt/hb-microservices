import React, {useCallback, useEffect, useState} from "react";
import {Link, useParams} from "react-router-dom";
import {
    Affix,
    Button,
    Card,
    Col,
    Divider,
    Form,
    Image,
    InputNumber,
    List,
    Modal,
    Row,
    Space,
    Tag,
    Typography
} from "antd";
import postsApi from "../../../../api/postsApi";
import {PostRes} from "../../../../models/response/PostRes";
import IconText from "../../../../components/IconText";
import {
    BellFilled,
    BellOutlined,
    DownCircleFilled,
    DownCircleOutlined,
    MessageOutlined,
    StarFilled,
    StarOutlined,
    UpCircleFilled,
    UpCircleOutlined,
} from "@ant-design/icons";
import moment from 'moment';
import {BASE_URL, YYYY_MM_DD_HH_MM_SS} from "../../../../const/constant";
import CardUserInfo from "../../../../components/CardUserInfo";
import {ColorMapChar} from "../../../../const/colorMapper";
import recommendApi from "../../../../api/recommendApi";
import favoritePostsApi from "../../../../api/favoritePostsApi";
import {VoteType} from "../../../../models/response/PostVoteRes";
import postVotesApi from "../../../../api/postVotesApi";
import CommentList from "../../../../components/CommentList";
import subscriptionsApi from "../../../../api/subscriptionsApi";
import donationApi from "../../../../api/donationApi";
import {useAppSelector} from "../../../../app/hooks";
import {UriUtils} from "../../../../utils/UriUtils";
import {history} from "../../../../utils/history";
import UserCard from "../../../../components/UserCard";
import {UserRes} from "../../../../models/response/UserRes";

type PostDetailParam = {
    id: string,
    title?: string,
}

const PostDetail: React.FC = () => {
    const pathVariable: PostDetailParam = useParams();
    const postId = parseInt(pathVariable.id);
    const [rerender, setRerender] = useState<boolean>(true);
    const [loading, setLoading] = useState<boolean>(true);
    const [post, setPost] = useState<PostRes>();
    const [rightActionContainer, setRightActionContainer] = useState<HTMLDivElement | null>(null);
    const currentUser = useAppSelector(state => state.auth.currentUser);

    const [relatedPosts, setRelatedPosts] = useState<PostRes[]>([]);
    const [relatedUsers, setRelatedUsers] = useState<UserRes[]>([]);

    const [addedToFavorite, setAddedToFavorite] = useState<boolean | undefined>(false);
    const [currentVoteType, setCurrentVoteType] = useState<VoteType | null>();
    const [numberOfVotes, setNumberOfVotes] = useState<number>(0);

    if(currentUser != null) {
        useEffect(()=> {
            setTimeout(function () {
                recommendApi.markAsReadPost(postId);
            }, 3000);
        }, [])
    }

    useEffect(() => {
        postsApi.getPostDetail(postId)
            .then(value => {
                setPost(value);
                setAddedToFavorite(value?.addedToFavorite);
                setNumberOfVotes(value?.numberOfVotes);
                setCurrentVoteType(value?.vote?.voteType);
                setLoading(false);

                recommendApi.fetchRelatedUserByNickname(value.user.nickName, 1, 5)
                    .then(pageUser => {
                        setRelatedUsers(pageUser.items);
                    })
                    .catch(reason => {
                        console.log(reason);
                    })
            })
            .catch(reason => {
                setLoading(false);
                console.error("ERROR: ", reason);
            });

        recommendApi.fetchRecommendedPostsByPostId(postId, 1, 6)
            .then(value => {
                setRelatedPosts(value.items);
            })
            .catch(reason => {
                console.error(reason);
            });
    }, [rerender]);

    const handleClickFavorite = useCallback(async () => {
        if (addedToFavorite) {
            await favoritePostsApi.deleteFromFavorite(postId);
            setAddedToFavorite(false);
        } else {
            await favoritePostsApi.addToFavorite(postId);
            setAddedToFavorite(true);
        }
    }, [addedToFavorite]);

    const handleClickVote = useCallback(async (voteType: VoteType) => {
        if (currentVoteType) {
            if (currentVoteType == voteType) {
                await postVotesApi.deleteVote(postId);
                setCurrentVoteType(null);
                if (voteType == VoteType.UP) {
                    setNumberOfVotes(numberOfVotes - 1);
                } else if (voteType == VoteType.DOWN) {
                    setNumberOfVotes(numberOfVotes + 1);
                }
            } else {
                await postVotesApi.updateVote(postId, voteType);
                setCurrentVoteType(voteType);
                if (voteType == VoteType.UP) {
                    setNumberOfVotes(numberOfVotes + 2);
                } else if (voteType == VoteType.DOWN) {
                    console.log("numberOfVotes", numberOfVotes);
                    setNumberOfVotes(numberOfVotes - 2);
                }
            }
        } else {
            await postVotesApi.createVote(postId, voteType);
            setCurrentVoteType(voteType);
            if (voteType == VoteType.UP) {
                setNumberOfVotes(numberOfVotes + 1);
            } else if (voteType == VoteType.DOWN) {
                setNumberOfVotes(numberOfVotes - 1);
            }
        }
    }, [currentVoteType, numberOfVotes]);

    return (
        <>
            <Col md={2}>
                <Row justify={"center"} ref={setRightActionContainer}>
                    <Affix offsetTop={30} target={() => rightActionContainer}>
                        <Space align={"center"} direction={"vertical"}>
                            {
                                currentVoteType && currentVoteType == VoteType.UP
                                    ? <UpCircleFilled style={{fontSize: 30}}
                                                      onClick={() => handleClickVote(VoteType.UP)}/>
                                    : <UpCircleOutlined style={{fontSize: 30}}
                                                        onClick={() => handleClickVote(VoteType.UP)}/>
                            }
                            <Typography.Text style={{fontSize: 30}}>{numberOfVotes ?? 0}</Typography.Text>
                            {
                                currentVoteType && currentVoteType == VoteType.DOWN
                                    ? <DownCircleFilled style={{fontSize: 30}}
                                                        onClick={() => handleClickVote(VoteType.DOWN)}/>
                                    : <DownCircleOutlined style={{fontSize: 30}}
                                                          onClick={() => handleClickVote(VoteType.DOWN)}/>
                            }
                            <Typography.Text>Votes</Typography.Text>
                            <Divider/>
                            {
                                addedToFavorite
                                    ? (<StarFilled style={{fontSize: 30, color: '#fadb14'}}
                                                   onClick={handleClickFavorite}/>)
                                    : (<StarOutlined style={{fontSize: 30}} onClick={handleClickFavorite}/>)
                            }
                            <Typography>Favorites</Typography>
                        </Space>
                    </Affix>
                </Row>
            </Col>
            <Col md={15}>
                <Space direction={"vertical"} style={{width: "100%"}}>
                    {
                        post?.coverImagePath
                        &&
                        (
                            <Image src={UriUtils.getImageUrlFromPath(post.coverImagePath)} width={"100%"}/>
                        )
                    }
                    <Card loading={loading} style={{width: "100%"}}
                          title={
                              <Typography.Title level={2}>
                                  {post?.title}
                              </Typography.Title>
                          }
                          extra={
                              currentUser?.nickName == post?.user.nickName ? (
                                  <Button>
                                      <Link to={`/posts/edit/${postId}/${post?.title}`}>
                                          Edit
                                      </Link>
                                  </Button>
                              ) : null
                          }>
                        <Typography.Paragraph type={"secondary"}>
                            {moment(post?.createdDate).format(YYYY_MM_DD_HH_MM_SS)}
                        </Typography.Paragraph>
                        <Space align={"start"} size={"large"}>
                            <IconText icon={<StarOutlined/>} text={post?.numberOfFavorites + " favorites"}
                                      key={"list-vertical-star-o"}/>
                            <IconText icon={<MessageOutlined/>} text={post?.numberOfComments + " comments"}
                                      key={"list-vertical-comment-o"}/>
                        </Space>
                        <div className={"ck-content"} style={{width: "100%", paddingTop: 30, fontSize: "1.3em"}}
                             dangerouslySetInnerHTML={{__html: post?.content || ""}}>
                        </div>
                        <div>
                            {
                                post?.tags?.map(tag => {
                                    return (
                                        <Tag key={tag.id} onClick={() => {
                                        }}>
                                            <Link to={`/tags/${tag.id}/${UriUtils.getEncodeUrl(tag.name)}/posts`}>
                                                {tag.name}
                                            </Link>
                                        </Tag>
                                    )
                                })
                            }
                        </div>
                    </Card>
                    <Card loading={loading}>
                        <Typography.Title level={4}>
                            Related post
                        </Typography.Title>
                        <Row justify={"space-between"}>
                            {
                                relatedPosts.map(post => {
                                    return (
                                        <Col xs={24} sm={12} md={8} key={post.id} style={{padding: 10}}>
                                            <Card>
                                                <Typography.Title level={5}
                                                                  ellipsis={{rows: 2}}
                                                                  style={{height: 50, cursor: "pointer"}}
                                                                  onClick={() => {
                                                                      history.push(`/posts/${post?.id}/${post?.title}`);
                                                                      setRerender(!rerender);
                                                                      setLoading(true);
                                                                  }}>
                                                    {post?.title}
                                                </Typography.Title>
                                                <Link to={`/users/${post?.user.nickName}`}>
                                                    <Typography.Paragraph strong ellipsis={{rows: 1}}>
                                                        Author: {post?.user?.firstName} {post?.user?.lastName}
                                                    </Typography.Paragraph>
                                                </Link>
                                                <Space align={"start"} size={"large"}>
                                                    <IconText icon={<StarOutlined/>}
                                                              text={post?.numberOfFavorites}
                                                              key={"list-vertical-star-o"}/>
                                                    <IconText icon={<MessageOutlined/>} text={post?.numberOfComments}
                                                              key={"list-vertical-comment-o"}/>
                                                </Space>
                                            </Card>
                                        </Col>
                                    );
                                })
                            }
                        </Row>
                    </Card>
                    <Card>
                        <Typography.Title level={4}>
                            Comments
                        </Typography.Title>
                        <CommentList postId={postId} parentId={null}/>
                    </Card>
                </Space>
            </Col>
            <Col md={7} style={{paddingLeft: "1em"}}>
                <Row>
                    <UserCard user={post?.user} loading={loading}/>
                </Row>
                <Row style={{marginTop: "1em"}}>
                    <Card title={"Related authors"} style={{width: "100%"}}>
                        <List dataSource={relatedUsers}
                              loading={loading}
                              renderItem={item => (
                                  <Row style={{padding: "1em 0"}}>
                                      <CardUserInfo user={item}/>
                                  </Row>
                              )}>
                        </List>
                    </Card>
                </Row>
            </Col>
        </>
    );
}

export default PostDetail;