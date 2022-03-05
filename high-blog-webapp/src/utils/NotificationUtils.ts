import {notification} from "antd";

export class NotificationUtils {
    static showErrorFromResponse(response: any) {
        notification.error({
            duration: 1.5,
            placement: "bottomRight",
            message: "Server Error",
            description: `${response.fieldName && `${response.fieldName}: `}${response.message ?? response.error ?? "Some thing error"}`
        });
    }

    static showUnAuthError() {
        notification.info({
            duration: 5,
            placement: "bottomRight",
            message: "Forbidden",
            description: "Please login"
        });
    }

    static showSuccess(message: string) {
        notification.success({
            duration: 2.5,
            placement: "bottomRight",
            message: `Success: ${message}`,
        })
    }
}