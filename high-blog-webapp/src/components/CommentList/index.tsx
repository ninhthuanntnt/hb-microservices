import React, {useCallback, useEffect, useState} from "react";
import {Button, Input, List, Skeleton, Space} from "antd";
import {CommentRes} from "../../models/response/CommentRes";
import commentsApi from "../../api/commentsApi";
import {ProfileRes} from "../../models/response/ProfileRes";
import {useAppSelector} from "../../app/hooks";
import CommentItem from "./Item/CommentItem";

interface Props {
    postId: number,
    parentId?: number | null
}

const CommentList: React.FC<Props> = ({postId, parentId}) => {
    const [commentLoading, setCommentLoading] = useState<boolean>(false);
    const [initLoadingComment, setInitLoadingComment] = useState<boolean>(true);
    const [loadingMoreComment, setLoadingMoreComment] = useState<boolean>(false);
    const [comments, setComments] = useState<CommentRes[]>([]);
    const [commentContent, setCommentContent] = useState<string>("");
    const [page, setPage] = useState<number>(1);
    const currentUser: ProfileRes | undefined = useAppSelector<ProfileRes | undefined>(state => state.auth.currentUser);

    const handleLoadMoreComment = useCallback(() => {
        setLoadingMoreComment(true);
        commentsApi.fetchComments(postId, parentId, page)
            .then(commentsRes => {
                setPage(page + 1);
                setComments([...comments, ...commentsRes.items]);
                setCommentLoading(false);

                if (commentsRes.page == commentsRes.totalPage) {
                    setLoadingMoreComment(false);
                }
            })
            .catch(reason => {
                setCommentLoading(false);
                console.log(reason);
            });
    }, [comments])

    const loadMore =
        !loadingMoreComment ? (
            (comments.length > 0) ? (
                <div
                    style={{
                        textAlign: 'center',
                        marginTop: 12,
                        height: 32,
                        lineHeight: '32px',
                    }}
                >
                    <Button onClick={handleLoadMoreComment}>More...</Button>
                </div>
            ) : null
        ) : (

            <Skeleton loading={loadingMoreComment}
                      avatar
                      title={false}
                      active>

            </Skeleton>
        );

    const handleAddComment = useCallback(async () => {
        if (commentContent) {
            setCommentLoading(true);
            try {
                let commentId = await commentsApi.createComment(postId, commentContent, parentId);
                setCommentLoading(false);

                setCommentLoading(false);
                setCommentContent("");

                let comment: CommentRes = {
                    id: commentId,
                    content: commentContent,
                    numberOfChildComments: 0,
                    user: {
                        id: currentUser?.id,
                        imagePath: currentUser?.imagePath,
                        firstName: currentUser?.firstName ?? "",
                        lastName: currentUser?.lastName ?? "",
                        nickName: currentUser?.nickName ?? "",
                    },
                    childComments: []
                }
                setComments([comment, ...comments])
            } catch (error) {
                console.error(error);
                setCommentLoading(false);
            }
        }
    }, [comments, commentContent])

    useEffect(() => {
        commentsApi.fetchComments(postId, parentId, page)
            .then(comments => {
                setPage(page + 1);
                setComments(comments.items);
                setCommentLoading(false);
                setInitLoadingComment(false);
            })
            .catch(reason => {
                console.log(reason);
                setInitLoadingComment(false);
            });
    }, [])

    return (
        <Space direction={"vertical"} style={{width: "100%"}}>
            <Input.TextArea rows={2}
                            value={commentContent}
                            onChange={(event => setCommentContent(event.target.value))}/>
            <Button onClick={() => handleAddComment()} loading={commentLoading}>
                Add comment
            </Button>
            <List itemLayout={"horizontal"}
                  loading={initLoadingComment}
                  dataSource={comments}
                  loadMore={loadMore}
                  locale={{emptyText: (<></>)}}
                  renderItem={comment => <CommentItem postId={postId} comment={comment}/>}>
            </List>

        </Space>
    )
}

export default CommentList;