import {UserRes} from "./UserRes";
export enum NotificationType {
    POST = "POST",
    COMMENT = "COMMENT",
    SUBSCRIPTION = "SUBSCRIPTION",
    ADMIN = "ADMIN",
}

export interface NotificationRes{
    id: number,
    senderId: number,
    sourceId: number,
    content: string,
    type: NotificationType,
    sender: UserRes,
};
