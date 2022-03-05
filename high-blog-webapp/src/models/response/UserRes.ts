export interface UserRes {
    id?: number,
    imagePath?: string,
    backgroundPath?: string,
    firstName: string,
    lastName: string,
    genderType?: string,
    nickName: string,
    followed?: boolean,
    notified?: boolean,
}