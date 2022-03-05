import {UserRes} from "./UserRes";
import {TagRes} from "./TagRes";
import {VoteRes} from "./PostVoteRes";

export interface PostRes {
    id: number,
    coverImagePath: string,
    title: string,
    summary: string,
    content: string,
    nickName: string,
    createdDate: number,
    lastModifiedDate: number,
    numberOfVotes: number,
    numberOfComments: number,
    numberOfFavorites: number,
    addedToFavorite: boolean,
    user: UserRes
    tags: TagRes[],
    vote: VoteRes,
}