import {NotificationType} from "../models/response/NotificationRes";
import {UriUtils} from "../utils/UriUtils";

function useLinkNotification(sourceId: number, type: NotificationType, content: string): string | null {
    switch (type) {
        case NotificationType.POST:
            return `/posts/${sourceId}/${UriUtils.getEncodeUrl(content)}`;
        default:
            return null;
    }
}

export default useLinkNotification;