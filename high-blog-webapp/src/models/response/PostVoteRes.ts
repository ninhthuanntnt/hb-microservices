export enum VoteType {
    UP = "UP",
    DOWN = "DOWN"
}

export interface VoteRes {
    id: number,
    voteType: VoteType,
}