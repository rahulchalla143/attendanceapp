import React, { Component } from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import axios from 'axios';
import MenuComponent from './MenuComponents';

const mql = window.matchMedia(`(min-width: 800px)`);

class LoginComponent extends Component {

    constructor() {
        super()
        this.state = {
            email: "",
            password: "",
            emailerror: "",
            passworderror: "",
            userId: 0,
            sidebarDocked:true,
            height: "100%"
        }
        this.handleEmailChange = this.handleEmailChange.bind(this)
        this.handlePasswordChange = this.handlePasswordChange.bind(this)
        this.submitHandler = this.submitHandler.bind(this)
        this.mediaQueryChanged=this.mediaQueryChanged.bind(this)
    }

    handleEmailChange(event) {
        if (event.target.value.length < 5) {
            this.setState({ userId: this.state.userId, email: event.target.value, password: this.state.password, emailerror: "* Email is required", passworderror: this.state.passworderror })
        }
        else {
            this.setState({ userId: this.state.userId, email: event.target.value, password: this.state.password, emailerror: "", passworderror: this.state.passworderror })
        }
    }

    // const mql = window.matchMedia(`(min-width: 800px)`);
componentWillMount() {
        mql.addListener(this.mediaQueryChanged);
    }

    mediaQueryChanged() {
        if(mql.matches){
            this.setState({ sidebarDocked: mql.matches , height:"100%" });       
        }
        else{
            this.setState({ sidebarDocked: mql.matches, height:"0px" });
        }
    }
    componentWillUnmount() {
        mql.removeListener(this.mediaQueryChanged);
    }

    handlePasswordChange(event) {
        if (event.target.value.length < 5) {
            this.setState({ userId: this.state.userId, email: this.state.email, password: event.target.value, emailerror: this.state.emailerror, passworderror: "* Password is required" })
        }
        else {
            this.setState({ userId: this.state.userId, email: this.state.email, password: event.target.value, emailerror: this.state.emailerror, passworderror: "" })
        }
    }

    submitHandler(event) {
        event.preventDefault();


        try {
            axios.post("http://localhost:8080/authapp/login", {
                uemail: this.state.email,
                upassword: this.state.password
            }).then((res) => {
                this.setState({ userId: res.data.id, email: this.state.email, password: this.state.password, emailerror: this.state.emailerror, passworderror: this.state.passworderror })
                alert("Form submitted successfully!!" + res.data.token)

                this.props.history.push({
                    pathname: '/userhome',
                    state: {
                        token: res.data.token,
                        userId: res.data.uid,
                        userName: res.data.firstname + " " + res.data.lastname,
                        role: res.data.role,
                        email: res.data.email,
                        age: res.data.age,
                        gender: res.data.gender,
                        contact: res.data.contact
                    }
                })
            })
                .catch((err) => {
                    alert("Login failed! Check credentials" + JSON.stringify(err))
                })
        }
        catch (e) {
            alert("Login failed! Check credentials!!!")
        }
    }

    render() {

        return (
            <div >
                <MenuComponent userId={0} />
                <Container fluid className="login1">
                    <Row>
                   
                        <Col lg={9} style= {{height :this.state.height }} >

                        </Col>

                            <Col lg={3} className="login2 d-flex flex-column " >
                                <form onSubmit={this.submitHandler}>
                                    <h2 style= {{ color: "white" }}  >Login</h2>
                                    <h6 style= {{ color: "red" }} >Fields marked with * are mandatory</h6>
                                    <h6 style={{ color: "red" }}>{this.state.emailerror}</h6>
                                    <h6 style={{ color: "red" }}>{this.state.passworderror}</h6>
                                    <label style={{ color: "white" }}>
                                        Email Address <span style={{ color: "red" }}>*</span>
                                        <br />
                                        <input type="email" value={this.state.email} onChange={this.handleEmailChange} />
                                    </label>
                                    <br />
                                    <label style={{ color: "white" }}>
                                        Password <span style={{ color: "red" }}>*</span>
                                        <br />
                                        <input type="password" value={this.state.password} onChange={this.handlePasswordChange} />
                                    </label>
                                    <br />
                                    <button type="submit" className="btn-primary">Submit</button>
                                </form>
                            </Col>
                            
                    </Row>
                </Container>

            </div>
        )
    }


}

export default LoginComponent