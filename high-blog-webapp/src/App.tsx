import React, {useState} from "react";
import "./global.less";
import Header from "./components/Header";
import {Col, Row} from "antd";
import SideMenu from "./components/SideMenu";
import {Route, Switch} from "react-router-dom";
import LoginTemp from "./features/auth/pages/LoginTemp";
import NewFeed from "./features/posts/pages/NewFeed";
import PostDetail from "./features/posts/pages/PostDetail";
import Wallet from "./features/transactions/pages/Wallet";
import WebsocketConfig from "./components/WebsocketConfig";
import NotificationList from "./features/notifications/pages/NotificationList";
import PostCreation from "./features/posts/pages/PostCreation";
import FavoritePosts from "./features/posts/pages/FavoritePosts";
import {useAppSelector} from "./app/hooks";
import PostsOfTag from "./features/posts/pages/PostsOfTag";
import TagList from "./features/tags/TagList";
import Trending from "./features/posts/pages/Trending";
import FollowingPosts from "./features/posts/pages/FollowingPosts";
import Activation from "./features/register/page/Activation";
import PersonalPage from "./features/users/PersonalPage";
import {random} from "lodash";
import {NOTIFICATION_SERVER_URL} from "./const/constant";

const App: React.FC = () => {
    const isLoggedIn = useAppSelector(state => state.auth.isLoggedIn);

    return (
        <>
            <Header/>
            {
                isLoggedIn &&
                <WebsocketConfig socketUrl={NOTIFICATION_SERVER_URL + "/notification"}
                                 subscribedUrl={`/user/exchange/amq.direct/notification`}
                                 markSentUrl={'/app/notification.setSent'}/>
            }
            <Row style={{padding: 20, position: "sticky"}}>
                <Route exact path={
                    [
                        "/",
                        "/trending",
                        "/wallet",
                        "/notifications",
                        "/posts/create",
                        "/posts/edit/:id/:title",
                        "/posts/following",
                        "/posts/favorites",
                        "/tags/:id/:title/posts",
                        "/tags"
                    ]
                }>
                    <Col md={4} sm={8} xs={0}>
                        <SideMenu/>
                    </Col>
                </Route>
                <Switch>
                    <Route exact path={"/login"} render={() => <LoginTemp/>}/>
                    <Route exact path={"/trending"}>
                        <Trending/>
                    </Route>
                    <Route exact path={"/posts/favorites"} render={() => <FavoritePosts/>}/>
                    <Route exact path={"/posts/:id/:title"} render={() => <PostDetail key={new Date().getTime()}/>}/>
                    <Route exact path={"/tags/:id/:name/posts"}>
                        <PostsOfTag/>
                    </Route>
                    <Route exact path={"/tags"}>
                        <TagList/>
                    </Route>
                    <Route exact path={"/wallet"} render={() => <Wallet/>}/>
                    <Route exact path={"/notifications"} render={() => <NotificationList/>}/>
                    <Route exact path={"/posts/create"} render={() => <PostCreation/>}/>
                    <Route exact path={"/posts/edit/:id/:title"} render={() => <PostCreation/>}/>
                    <Route exact path={"/posts/following"} render={() => <FollowingPosts/>}/>
                    <Route exact path={"/"} render={() => <NewFeed/>}/>
                    <Route exact path={"/register/activation/:id"}>
                        <Activation/>
                    </Route>
                    <Route exact path={"/users/:nickName"}>
                        <PersonalPage/>
                    </Route>
                </Switch>
            </Row>
        </>
    );
};

export default App;