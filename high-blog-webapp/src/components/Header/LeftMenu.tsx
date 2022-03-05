import React from "react";
import {Button, Menu} from "antd";
import "./index.less";
import {Link} from "react-router-dom";
import {EditOutlined} from "@ant-design/icons";
import AuthenticatedComponent from "../AuthenticatedComponent";
import {useAppSelector} from "../../app/hooks";

const LeftMenu: React.FC = () => {
    const isAuthenticated = useAppSelector(state => state.auth.isLoggedIn);
    return (
        <Menu mode={"horizontal"} className={"menu"}>
            <Menu.Item key={"Feed"}>
                <Link to={"/"}>
                    Newsfeed
                </Link>
            </Menu.Item>
            <Menu.Item key={"Trending"}>
                <Link to={"/trending"}>
                    Trending
                </Link>
            </Menu.Item>
            {
                isAuthenticated
                &&
                <Menu.Item>
                    <Link to={"/posts/following"}>
                        Following
                    </Link>
                </Menu.Item>
            }
            {
                isAuthenticated
                &&
                <Menu.Item>
                    <Link to={"/posts/create"}>
                        <Button shape={"circle"} icon={<EditOutlined/>}/>
                    </Link>
                </Menu.Item>
            }
        </Menu>
    )
}

export default LeftMenu;