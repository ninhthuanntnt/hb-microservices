import React, {useState} from "react";
import {Button, Col, Drawer, Layout, Menu, Row, Space, Typography} from "antd";
import RightMenu from "./RightMenu";
import LeftMenu from "./LeftMenu";

interface Props {
    style?: React.CSSProperties
}

const Header: React.FC<Props> = (props) => {
    let [visibleDrawer, setVisibleDrawer] = useState(false);

    const showDrawer = () => {
        setVisibleDrawer(true);
    }

    const hideDrawer = () => {
        setVisibleDrawer(false);
    }

    return (
        <Row align={"middle"} className={"header"} style={props.style}>
            <Col md={4} sm={8} xs={12}>
                <Row justify={"center"} align={"middle"}>
                    <Col className={"logo"}>
                        High blog
                    </Col>
                </Row>
            </Col>
            <Col md={10} sm={0} xs={0}>
                <LeftMenu/>
            </Col>
            <Col md={9} sm={0} xs={0}>
                <Row justify={"end"}>
                    <RightMenu/>
                </Row>
            </Col>
        </Row>
    )
}

export default Header;