import React, {ReactElement} from "react";
import {Row, Space} from "antd";
import {DeleteOutlined, EyeOutlined} from "@ant-design/icons";
import "./index.less";

interface Props {
    actionItems: () => React.ReactNode
}

const OverlapActions:React.FC<Props> = ({actionItems}) => {
    return (
        <div className={"overlap-actions"}>
            <Row justify={"center"} align={"middle"} style={{width: "100%", height: "100%"}}>
                <Space>
                    {actionItems()}
                </Space>
            </Row>
        </div>
    )
}

export default OverlapActions;