import axiosClient from "./axiosClient";
import {VoteType} from "../models/response/PostVoteRes";

const postVotesApi = {
    createVote(postId: number, voteType: VoteType): Promise<void> {
        return axiosClient.post(`/hb-dmm/api/v1/user/posts-votes`, {postId, voteType});
    },
    deleteVote(postId: number): Promise<void> {
        return axiosClient.delete(`/hb-dmm/api/v1/user/posts-votes`, {
            data: {
                postId,
            }
        })
    },
    updateVote(postId: number, voteType: VoteType): Promise<void> {
        return axiosClient.put(`/hb-dmm/api/v1/user/posts-votes`, {
            postId,
            voteType,
        })
    }
}

export default postVotesApi;