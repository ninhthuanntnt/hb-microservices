import {UserRes} from "./UserRes";

export interface CommentRes {
    id: number,
    content: string,
    numberOfChildComments: number,
    childComments: CommentRes[],
    user: UserRes
}