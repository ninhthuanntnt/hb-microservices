import React from "react";
import {Card} from "antd";
import {UserRes} from "../../models/response/UserRes";
import AvatarColor from "../AvatarColor";
import {Link} from "react-router-dom";

interface Props {
    user?: UserRes
}

const CardUserInfo: React.FC<Props> = ({user}) => {
    return (
        <Link to={`/users/${user?.nickName}`}>
            <Card.Meta
                avatar={
                    <AvatarColor src={user?.imagePath} text={user?.firstName}/>

                }
                title={user?.firstName + " " + user?.lastName}
                description={user?.nickName}
            />
        </Link>
    )
}

export default CardUserInfo;