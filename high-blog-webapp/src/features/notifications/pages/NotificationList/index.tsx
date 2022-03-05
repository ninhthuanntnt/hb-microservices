import React, {useCallback, useEffect, useState} from "react";
import {Col, Divider, List, Typography} from "antd";
import {NotificationRes} from "../../../../models/response/NotificationRes";
import {BasePaginationRes} from "../../../../models/response/BasePaginationRes";
import notificationsApi from "../../../../api/notificationsApi";
import {Link} from "react-router-dom";
import AvatarColor from "../../../../components/AvatarColor";
import useLinkNotification from "../../../../app/useLinkNotification";

const NotificationList: React.FC = () => {
    const [pageNotifications, setPageNotifications] = useState<BasePaginationRes<NotificationRes>>({
        page: 1,
        pageSize: 0,
        items: [],
        totalItems: 0,
        totalPage: 1
    });
    const changePage = useCallback((page: number) => {
        notificationsApi.getListNotification(page).then(value => setPageNotifications(value));
    }, [pageNotifications]);

    useEffect(() => {
        notificationsApi.getListNotification(pageNotifications.page).then(value => setPageNotifications(value));
    }, []);

    const renderNotification = useCallback((notification: NotificationRes) => {
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
    }, [])

    return (
        <Col md={19}>
            <Typography.Title level={4}>
                Notifications
            </Typography.Title>
            <Divider/>
            <List itemLayout={"vertical"}
                  pagination={{
                      onChange: changePage,
                      total: pageNotifications?.totalItems,
                      current: pageNotifications?.page,
                      showSizeChanger: false
                  }}
                  dataSource={pageNotifications.items}
                  renderItem={renderNotification}/>
        </Col>
    )
}

export default NotificationList;