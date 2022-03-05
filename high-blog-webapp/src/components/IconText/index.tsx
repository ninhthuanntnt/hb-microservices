import React, {ReactComponentElement} from "react";
import {Space} from "antd";

export interface Props {
    icon: ReactComponentElement<any>,
    text: string | number | null
}

const IconText: React.FC<Props> = ({icon, text}) => {
    return (<Space>
        {icon}
        {text}
    </Space>);
}

export default IconText;