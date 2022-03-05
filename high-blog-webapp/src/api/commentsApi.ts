import axiosClient from "./axiosClient";
import {CommentRes} from "../models/response/CommentRes";
import {BasePaginationRes} from "../models/response/BasePaginationRes";

const postsApi = {
    fetchComments(postId: number, parentId: number | null | undefined, page: number, pageSize: number = 5): Promise<BasePaginationRes<CommentRes>> {
        return axiosClient.get(`/hb-dmm/api/v1/comments?postId=${postId}&page=${page}&pageSize=${pageSize}&parentId=${parentId ? parentId : ''}`);
    },
    createComment(postId: number, content: string, parentId: number | null | undefined): Promise<number> {
        return axiosClient.post(`/hb-dmm/api/v1/user/comments`, {
            postId,
            content,
            parentId,
        })
    }
}

export default postsApi;