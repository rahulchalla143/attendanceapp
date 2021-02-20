import React, { Component } from 'react'
import { Col, Row, Button, Form, FormGroup, Label, Input } from 'reactstrap';
import '../StyleSheets/MyStyle.css';
import axios from 'axios'
import MenuComponent from './MenuComponents';


const initialState = {
    FirstName: "",
    LastName: "",
    Email: "",
    password: "",
    age: null,
    ContactNumber: null,
    gender: null,
    EmployeeId: "",
    FirstNameError: "",
    LastNameError: "",
    EmailError: "",
    passwordError: "",
    selectuser: true,
    Role: "",
    Approved: "",


}

class UserRegistration extends Component {

    constructor(props) {
        super(props)

        this.state = initialState;

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);




    }

    GenerateRandomnumber = () => {
        var RandomNumber = Math.floor(Math.random() * 100) + 1;

        return RandomNumber;


    }

    handleChange = (event) => {
        this.setState({ [event.target.name]: event.target.value })
    };

    validate = () => {
        let FirstNameError = "";
        let LastNameError = "";
        let EmailError = "";
        let passwordError = "";


        if (!this.state.FirstName) {
            FirstNameError = "FirstName is Required"
        }

        if (!this.state.LastName) {
            LastNameError = "LastName is Required"
        }

        if (!this.state.Email) {
            EmailError = "Email is missing"

        } else if (this.state.Email !== "undefined") {

            var pattern = new RegExp(/^(("[\w-\s]+")|([\w-]+(?:\.[\w-]+)*)|("[\w-\s]+")([\w-]+(?:\.[\w-]+)*))(@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$)|(@\[?((25[0-5]\.|2[0-4][0-9]\.|1[0-9]{2}\.|[0-9]{1,2}\.))((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\.){2}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[0-9]{1,2})\]?$)/i);
            if (!pattern.test(this.state.Email)) {

                alert(" @ is required in email")
            }
        }

        if (!this.state.password) {
            passwordError = "password is required"
        }

        if (FirstNameError || LastNameError || passwordError || EmailError) {
            this.setState({ FirstNameError, LastNameError, passwordError, EmailError })
            return false;
        }
        return true;
    }

    componentDidMount() {
        let number = this.GenerateRandomnumber();
        this.setState({ EmployeeId: number })

    }

    handleSubmit = (event) => {
        event.preventDefault();
        let isValid = this.validate();
        if (isValid) {
            console.log(this.state);
            this.setState(initialState);

            axios.post("http://localhost:8080/authapp/register", {
                userid: this.state.EmployeeId,
                upassword: this.state.password,
                ufirstname: this.state.FirstName,
                urole: this.state.Role,
                ulastname: this.state.LastName,
                uemail: this.state.Email,
                uage: this.state.age,
                ugender: this.state.gender,
                ucontact: this.state.ContactNumber,
                uapproved: this.state.Approved,

            }).then(res => {
                console.log(JSON.stringify(res) + "$345")
                if (res.data.role == "User") {
                    alert("your details are submitted successfully")
                    this.props.history.push({
                        pathname: '/',
                        state: { 
                            token: res.data.token,
                            userId: res.data.uid,
                            userName: res.data.firstname + " " + res.data.lastname,
                            role:res.data.role,
                            email:res.data.email,
                            age:res.data.age,
                            gender:res.data.gender,
                            contact:res.data.contact
                            }
                    })

                } else {
                    alert("your request has to be approved and wait till for login")
                     this.props.history.push("/login")
                }


            })


        }
    }





    handleevent = (event) => {
        if (event.target.value == "admin") {

            this.setState({ selectuser: false, Role: "Admin", Approved: "No" })
        } else {
            this.setState({ selectuser: true, Role: "User", Approved: "Yes" })
        }

    }



    render() {

        if (this.state.selectuser) {
            return (
                <div>
                    <MenuComponent userId={0} />
                    <Form onSubmit={this.handleSubmit}>

                        <Row form>
                            <Col md={6}>
                                <FormGroup>
                                    <Label for="FirstName"></Label>
                                    <Input type="text" name="FirstName" id="FirstName" value={this.state.FirstName} onChange={this.handleChange} placeholder="FirstName" />
                                    <div style={{ fontSize: 12, color: "red" }}>{this.state.FirstNameError}</div>

                                </FormGroup>
                            </Col>
                            <Col md={6}>
                                <FormGroup>
                                    <Label for="LastName"></Label>
                                    <Input type="text" name="LastName" id="LastName" value={this.state.LastName} onChange={this.handleChange} placeholder="LastName" />
                                    <div style={{ fontSize: 12, color: "red" }}>{this.state.LastNameError}</div>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row form>
                            <Col md={6}>
                                <FormGroup>
                                    <Label for="Email"></Label>
                                    <Input type="email" name="Email" id="Email" value={this.state.Email} onChange={this.handleChange} placeholder="Enter Email" />
                                    <div style={{ fontSize: 12, color: "red" }}>{this.state.EmailError}</div>
                                </FormGroup>
                            </Col>
                            <Col md={6}>
                                <FormGroup>
                                    <Label for="password"></Label>
                                    <Input type="password" name="password" id="password" value={this.state.password} onChange={this.handleChange} placeholder="Enter password" />
                                    <div style={{ fontSize: 12, color: "red" }}>{this.state.passwordError}</div>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Col md={4}>
                            <FormGroup>

                                <Label for="select"></Label>
                                <select onChange={this.handleevent}>
                                    <option>Choose</option>
                                    <option value="user">User</option>
                                    <option value="admin" >Admin</option>

                                </select>
                            </FormGroup>

                        </Col>

                        <Button className="btn bg-primary" style={{ color: 'white' }} type="submit" value="submit" onClick={this.GenerateRandomnumber}>Registration</Button>
                    </Form>

                </div>
            )
        } else {

            return (
                <div>
                    <MenuComponent userId={0} />
                    <Form onSubmit={this.handleSubmit}>

                        <Row form>
                            <Col md={6}>
                                <FormGroup>
                                    <Label for="FirstName"></Label>
                                    <Input type="text" name="FirstName" id="FirstName" value={this.state.FirstName} onChange={this.handleChange} placeholder="FirstName" />
                                    <div style={{ fontSize: 12, color: "red" }}>{this.state.FirstNameError}</div>

                                </FormGroup>
                            </Col>
                            <Col md={6}>
                                <FormGroup>
                                    <Label for="LastName"></Label>
                                    <Input type="text" name="LastName" id="LastName" value={this.state.LastName} onChange={this.handleChange} placeholder="LastName" />
                                    <div style={{ fontSize: 12, color: "red" }}>{this.state.LastNameError}</div>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Row form>
                            <Col md={6}>
                                <FormGroup>
                                    <Label for="Email"></Label>
                                    <Input type="email" name="Email" id="Email" value={this.state.Email} onChange={this.handleChange} placeholder="Enter Email" />
                                    <div style={{ fontSize: 12, color: "red" }}>{this.state.EmailError}</div>
                                </FormGroup>
                            </Col>
                            <Col md={6}>
                                <FormGroup>
                                    <Label for="password"></Label>
                                    <Input type="password" name="password" id="password" value={this.state.password} onChange={this.handleChange} placeholder="Enter password" />
                                    <div style={{ fontSize: 12, color: "red" }}>{this.state.passwordError}</div>
                                </FormGroup>
                            </Col>
                        </Row>
                        <Col md={4}>
                            <FormGroup>

                                <Label for="select"></Label>
                                <select onChange={this.handleevent} >
                                    <option>Choose</option>
                                    <option value="user">User</option>
                                    <option value="admin">Admin</option>

                                </select>

                            </FormGroup>
                            <Row form>
                                <Col md={10}>
                                    <FormGroup>
                                        <Label for="ContactNumber"></Label>
                                        <Input type="text" name="ContactNumber" id="ContactNumber" onChange={this.handleChange} placeholder="Enter Contact Number" />
                                    </FormGroup>
                                </Col>
                                <Col md={6}>
                                    <FormGroup>
                                        <Label for="age"></Label>
                                        <Input type="text" name="age" id="age" onChange={this.handleChange} placeholder="Enter age" />
                                    </FormGroup>
                                </Col>
                                <Col md={6}>
                                    <FormGroup>
                                        <div >
                                            <Label for="gender">Gender</Label><br />
                                            <input type="radio" value="MALE" name="gender" /> Male &nbsp;
                                             <input type="radio" value="FEMALE" name="gender" /> Female
                                            </div>

                                    </FormGroup>
                                </Col>
                            </Row>
                        </Col>

                        <Button className="btn bg-primary" style={{ color: 'white' }} type="submit" onChange={this.handleChange} value="submit" onClick={this.GenerateRandomnumber}>Registration</Button>
                    </Form>

                </div>
            )
        }

    }
}

export default UserRegistration;
