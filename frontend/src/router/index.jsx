import {createBrowserRouter} from "react-router-dom";
import {Home} from "@/pages/Home/index.jsx";
import {SignUp} from "@/pages/SignUp/index.jsx";
import App from "@/App";
import {Component} from "react";
import {Activation} from "@/pages/Activation/index.jsx";
import {UserDetail} from "@/pages/UserDetail/index.jsx";
import {Login} from "@/pages/Login/index.jsx";

export default createBrowserRouter([
    {
        path: "/",
        Component: App,
        children: [
            {
                path: "/",
                Component: Home,
                index: true
                // errorElement: <div>Unexpected Error Occured</div>
            },
            {
                path: "/signup",
                Component: SignUp
            },
            {
                path: "/activation/:token",
                Component: Activation
            },
            {
                path: "/user/:id",
                Component: UserDetail
            },
            {
                path: "/login",
                Component: Login
            }
        ]
    },
])