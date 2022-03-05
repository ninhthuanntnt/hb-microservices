import React, {useState} from "react";
import {Menu, Space, Typography} from "antd";
import DefaultAvatarIcon from "../../images/icon/default-avatar.svg";
import FavoriteIcon from "../../images/icon/star.svg";
import TagIcon from "../../images/icon/tag.svg";
import WalletIcon from "../../images/icon/wallet.svg";
import "./index.less";
import {history} from "../../utils/history";
import {Link} from "react-router-dom";
import AuthenticatedComponent from "../AuthenticatedComponent";
import {useAppSelector} from "../../app/hooks";
import {ProfileRes} from "../../models/response/ProfileRes";

const SideMenu: React.FC = () => {
    const currentUser: ProfileRes | undefined = useAppSelector(state => state.auth.currentUser);
    return (
        <Menu className={"menu"}>
            {
                currentUser
                &&
                <Menu.Item className={"menu-item"} onClick={()=> history.push(`/users/${currentUser?.nickName}`)}>
                    <Space>
                        <img src={DefaultAvatarIcon} alt={"avatar-icon"} width={32}/>
                        <Typography.Text>
                            {currentUser?.firstName} {currentUser?.lastName}
                        </Typography.Text>
                    </Space>
                </Menu.Item>
            }
            {
                currentUser
                &&
                <Menu.Item className={"menu-item"} onClick={() => {
                    history.push("/wallet");
                }}>
                    <Space>
                        <img src={WalletIcon} alt={"wallet-icon"} width={32}/>
                        <Typography.Text>
                            Wallet
                        </Typography.Text>
                    </Space>
                </Menu.Item>
            }
            {
                currentUser
                &&
                <Menu.Item className={"menu-item"} onClick={() => history.push("/posts/favorites")}>
                    <Space>
                        <img src={FavoriteIcon} alt={"favorite-icon"} width={32}/>
                        <Typography.Text>
                            Favorites
                        </Typography.Text>
                    </Space>
                </Menu.Item>
            }
            <Menu.Item className={"menu-item"}  onClick={() => history.push("/tags")}>
                <Space>
                    <img src={TagIcon} alt={"tag-icon"} width={32}/>
                    <Typography.Text>
                        Tags
                    </Typography.Text>
                </Space>
            </Menu.Item>
        </Menu>
    )
}

export default SideMenu;