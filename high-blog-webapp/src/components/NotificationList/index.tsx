import React, {useEffect, useState} from "react";
import {Button, Card, List, Menu, Row} from "antd";
import notificationsApi from "../../api/notificationsApi";
import {NotificationRes} from "../../models/response/NotificationRes";
import AvatarColor from "../AvatarColor";
import useLinkNotification from "../../app/useLinkNotification";
import {Link} from "react-router-dom";

const NotificationList: React.FC = () => {
    const [notifications, setNotifications] = useState<NotificationRes[]>();
    useEffect(() => {
        notificationsApi.getListNotification(1, 5)
            .then(value => {
                setNotifications(value.items);
            })
    }, []);

    return (
        <Card style={{width: 300, backgroundColor: "white"}}>
            <List>
                {
                    notifications?.map(notification => {
                        const linkNotification: string | null =
                            useLinkNotification(
                                notification.sourceId,
                                notification.type,
                                notification.content
                            );

                        return (
                            <List.Item>
                                <Link to={linkNotification ? linkNotification : ''}>
                                    <List.Item.Meta key={notification.id}
                                                    avatar={<AvatarColor src={notification.sender.imagePath}
                                                                         text={notification.sender.nickName}/>}
                                                    title={notification.sender.nickName}
                                                    description={notification.content}
                                                    style={{width: 200}}/>
                                </Link>
                            </List.Item>
                        )
                    })
                }

                <List.Item >
                    <Link to={"/notifications"} style={{width: "100%"}}>
                        <Button type={"default"} block>
                            All...
                        </Button>
                    </Link>
                </List.Item>
            </List>
        </Card>
    );
}

export default NotificationList;