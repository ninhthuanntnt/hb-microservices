import React from "react";
import {useAppSelector} from "../../app/hooks";

interface Props {
    component: () => React.ReactElement
}

const AuthenticatedComponent: React.FC<Props> = ({component}) => {
    const isAuthenticated: boolean = useAppSelector(state => state.auth.isLoggedIn);

    return (
        <>
            {
                isAuthenticated ?
                    component()
                    : null
            }
        </>
    )
}

export default AuthenticatedComponent;