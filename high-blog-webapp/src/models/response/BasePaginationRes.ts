export interface BasePaginationRes<T> {
    totalItems: number,
    totalPage: number,
    pageSize: number,
    page: number
    items: T[]
}