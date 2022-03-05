import React from "react";
import {ColorMapChar} from "../../const/colorMapper";
import {Avatar} from "antd";

interface Props{
    src: string | undefined,
    text: string | undefined,
}

const AvatarColor: React.FC<Props> = ({src, text}) => {
    return (
        <Avatar src={src}
                style={{backgroundColor: text ? ColorMapChar[text?.charAt(0)] : '#ccc'}}>
            {text?.charAt(0)}
        </Avatar>
    )
}

export default AvatarColor;