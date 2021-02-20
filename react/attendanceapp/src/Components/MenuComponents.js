import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import * as RB from "react-bootstrap";
import { Link } from "react-router-dom";

function MenuComponent(props) {

    if (!(props.userId == 0)) {
        return (
            <RB.Navbar bg="dark" variant="dark">

                <RB.Navbar.Brand href="/">Attendanceapp</RB.Navbar.Brand>
                <Link className="text-white p-3" to={{
                    pathname: '/',
                    state: {
                        token: props.token,
                        userId: props.userId,
                        userName: props.userName,
                        role: props.role,
                        email: props.email,
                        age: props.age,
                        gender: props.gender,
                        contact: props.contact
                    }
                }}>Home</Link>
                <RB.Nav.Link className="text-white" href="/login">Logout</RB.Nav.Link>
            </RB.Navbar>
        )
    }
    else {
        return (
            <RB.Navbar bg="dark" variant="dark">

                <RB.Navbar.Brand href="/">Attendanceapp</RB.Navbar.Brand>
                <RB.Nav.Link className="text-white" href="/login">Login</RB.Nav.Link>
                <Link className="text-white p-3" to={{
                    pathname: '/userregistration',
                    
                }}>Registration</Link>
            </RB.Navbar>
        )
    }

}

export default MenuComponent