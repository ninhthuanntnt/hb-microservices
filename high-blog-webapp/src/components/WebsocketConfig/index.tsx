import React, {useCallback, useEffect} from "react";
import {notification, Typography} from "antd";
import SockJS from "sockjs-client";
import webstomp from 'webstomp-client';

interface Props {
    socketUrl: string,
    subscribedUrl: string,
    markSentUrl: string,
    onMessage?: Function,
}

const WebsocketConfig: React.FC<Props> = ({socketUrl, subscribedUrl, markSentUrl, onMessage}) => {
    console.log("RENDER WEBSOCKET CONFIG")
    useEffect(() => {

        const socket = new SockJS(socketUrl);
        const stompClient = webstomp.over(socket);

        stompClient.connect({'accessToken': localStorage.getItem("accessToken") ?? ""}, function (frame) {
            stompClient.subscribe(subscribedUrl, (message) => {

                if (message.command === "MESSAGE") {
                    let jsonBody = JSON.parse(message.body);

                    notification.open({
                        message: "New post",
                        description: buildNotification(jsonBody),
                        duration: 2,
                        placement: "bottomRight",
                    })

                    stompClient.send(`${markSentUrl}.${jsonBody.id}`)
                    if (onMessage) {
                        onMessage(jsonBody);
                    }
                }
            });
        });
    }, [])

    const buildNotification = useCallback((notification) => {
        console.log(notification);
        switch (notification.type) {
            case "POST": {
                return (
                    <Typography.Paragraph>
                        {notification.content}
                    </Typography.Paragraph>
                )
            }
        }
    }, []);

    return null;
}

export default WebsocketConfig;