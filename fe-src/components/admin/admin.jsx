import React, {useEffect} from "react";
import "./admin.css";
import Navbar from "../navbar/NavBar.jsx";
import {useNavigate} from "react-router-dom";

export const Admin = ({isAuthenticated, roles}) => {
    const navigate = useNavigate();

    useEffect(() => {
        if (isAuthenticated && roles.includes("ROLE_ADMIN")) {
            navigate('/admin');
        }
    }, [isAuthenticated, roles, navigate]);
    const tabs = [
        {ref: "/", text: "Home"},
        {ref: "/addUser", text: "Add User"},
        {ref: "/deleteUser", text: "Delete User"},
        {ref: "/updateUser", text: "Update User"},
        {ref: "/addDevice", text: "Add Device"},
        {ref: "/updateDevice", text: "Update Device"},
        {ref: "/deleteDevice", text: "Delete Device"},
        {ref: "/chat", text: "Chat"}
    ]
    return (
        <Navbar buttons={tabs}/>
    );
}
