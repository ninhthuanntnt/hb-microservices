import axiosClient from "./axiosClient";

const favoritePostsApi = {
    addToFavorite(postId: number): Promise<void> {
        return axiosClient.post(`/hb-dmm/api/v1/user/favorite-posts`, {postId});
    },
    deleteFromFavorite(postId: number): Promise<void> {
        return axiosClient.delete(`/hb-dmm/api/v1/user/favorite-posts?postId=${postId}`);
    }
}

export default favoritePostsApi;