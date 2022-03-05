import React, {useState} from "react";
import {Comment} from "antd";
import AvatarColor from "../../AvatarColor";
import CommentList from "../index";
import {CommentRes} from "../../../models/response/CommentRes";

interface Props {
    postId: number
    comment: CommentRes
}

const CommentItem: React.FC<Props> = ({postId, comment}) => {
    const [shouldShowChildComment, setShouldShowChildComment] = useState<boolean>(false);

    let name = comment.user.firstName + " " + comment.user.lastName;
    return (
        <Comment
            key={comment.id}
            actions={[
                (<span key="comment-nested-replies" onClick={()=>setShouldShowChildComment(true)}>
                    {comment.numberOfChildComments > 0 ? `${comment.numberOfChildComments} Replies...` : null}
                </span>),
                (<span key="comment-nested-reply-to" onClick={()=>setShouldShowChildComment(true)}>
                    Reply to
                </span>)
            ]}
            author={<a>{name}</a>}
            avatar={<AvatarColor src={comment.user.imagePath} text={name}/>}
            content={
                <p>
                    {comment.content}
                </p>
            }
        >
            {
                shouldShowChildComment
                &&
                <CommentList postId={postId} parentId={comment.id}/>
            }
        </Comment>
    )
}

export default CommentItem;