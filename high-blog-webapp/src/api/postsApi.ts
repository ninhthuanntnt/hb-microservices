import {PostRes} from "../models/response/PostRes";
import axiosClient from "./axiosClient";
import {PostCreateReq} from "../models/request/PostCreateReq";
import {BasePaginationRes} from "../models/response/BasePaginationRes";

const postsApi = {
    getPostDetail(id: number): Promise<PostRes> {
        return axiosClient.get(`/hb-dmm/api/v1/posts/${id}`);
    },
    createNewPost(postCreateReq: PostCreateReq) {
        return axiosClient.post(`/hb-dmm/api/v1/user/posts`, postCreateReq);
    },
    getByUrl(url: string, page: number, pageSize: number = 10): Promise<BasePaginationRes<PostRes>> {
        return axiosClient.get(`${url}`, {
            params: {
                page,
                pageSize,
            }
        })
    },
    getPostDetailForCurrentUser(id: number): Promise<PostRes> {
        return axiosClient.get(`/hb-dmm/api/v1/user/posts/${id}`);
    },
    updatePost(postId: number, postCreateReq: PostCreateReq): Promise<PostRes> {
        return axiosClient.put(`/hb-dmm/api/v1/user/posts/${postId}`, postCreateReq);
    },
}

export default postsApi;