import React from "react";
import {render} from "react-dom";
import App from "./App";
import {Provider} from "react-redux";
import {store} from "./app/store";
import {ConnectedRouter} from "connected-react-router";
import {history} from "./utils/history";

render(
    <React.StrictMode>
        <Provider store={store}>
            <ConnectedRouter history={history}>
                <App/>
            </ConnectedRouter>
        </Provider>
    </React.StrictMode>,
    document.getElementById("root")
);