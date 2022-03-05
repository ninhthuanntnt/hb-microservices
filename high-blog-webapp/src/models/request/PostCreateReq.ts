import {TagCreateReq} from "./TagCreateReq";

export enum PostType {
    DRAFT = "DRAFT",
    NORMAL = "NORMAL",
}

export interface PostCreateReq {
    categoryId: number
    title: string,
    summary?: string,
    content: string,
    coverImagePath,
    postType: PostType,
    tags: TagCreateReq[],
}