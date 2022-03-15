import axiosClient from "./axiosClient";
import {PostRes} from "../models/response/PostRes";
import {BasePaginationRes} from "../models/response/BasePaginationRes";
import {UserRes} from "../models/response/UserRes";

const recommendApi = {
    fetchRecommendedPosts(page: number, pageSize: number = 10): Promise<BasePaginationRes<PostRes>> {
        return axiosClient.get(`/hb-dmm/api/v1/recommendation/posts?page=${page}&pageSize=${pageSize}`);
    },
    fetchRecommendedPostsByPostId(postId: number, page: number, pageSize: number = 10): Promise<BasePaginationRes<PostRes>> {
        return axiosClient.get(`/hb-dmm/api/v1/recommendation/posts/${postId}?page=${page}&pageSize=${pageSize}`)
    },
    fetchRelatedUserByNickname(nickname, page: number, pageSize: number = 10): Promise<BasePaginationRes<UserRes>> {
        return axiosClient.get(`/hb-dmm/api/v1/recommendation/users/${nickname}`)
    },
    markAsReadPost(postId: number) {
        return axiosClient.get(`/hb-dmm/api/v1/recommendation/posts/${postId}/mark-as-read`);
    }
}

export default recommendApi;