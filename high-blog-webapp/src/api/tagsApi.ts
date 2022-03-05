import axiosClient from "./axiosClient";
import {TagRes} from "../models/response/TagRes";
import {BasePaginationRes} from "../models/response/BasePaginationRes";

const tagsApi = {
    searchTag(tagName: string, page: number = 1, pageSize: number = 5): Promise<BasePaginationRes<TagRes>> {
        return axiosClient.get("/hb-dmm/api/v1/tags/search", {
            params: {
                tagName,
                page,
                pageSize
            }
        });
    }
}

export default tagsApi;