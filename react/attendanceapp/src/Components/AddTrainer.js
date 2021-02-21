import React, { Component } from 'react'
import { Col, Row, Button, Form, FormGroup, Label, Input } from 'reactstrap';
import axios from 'axios';
import { Select } from 'semantic-ui-react';
import AddSession from './AddSession';

class AddTrainer extends Component {

    constructor(props) {
        super(props)
        this.state = {
            trainerId: null,
            trainerName: null,
            contactNumber: null,
            email: null,
            skillSet: null,
            trainerIdError: null,
            trainerNameError: null,
            contactNumberError: null,
            emailError: null,
            skillSetError: null,
            sessionId: null,
            sessionDate: null,
            sessionTime: null,
            sessionDesc: null,
            sessionSkillSet: null,
            sessionIdError: null,
            sessionDateError: null,
            sessionTimeError: null,
            sessionDescError: null,
            sessionSkillSetError: null,
            skillOptions: [
                { key: 'admin', value: 'admin', text: 'Admin' },
                { key: 'user', value: 'user', text: 'User' }
            ],
            sessionSkillOptions: [
                { key: 'admin', value: 'admin', text: 'Admin' },
                { key: 'user', value: 'user', text: 'User' }
            ]
        }
        this.submitHandler = this.submitHandler.bind(this)
        this.handleChange = this.handleChange.bind(this)
        this.handleSelect = this.handleSelect.bind(this)
        this.handleTrainerSelect = this.handleTrainerSelect.bind(this)
    }


    validate = () => {
        let trainerIdError = "";
        let trainerNameError = "";
        let contactNumberError = "";
        let emailError = "";
        let skillSetError = "";
        let sessionIdError = "";
        let sessionDateError = "";
        let sessionTimeError = "";
        let sessionDescError = "";
        let sessionSkillSetError = "";

        if (!this.state.trainerId) {
            trainerIdError = "trainerId is Required"
        }

        if (!this.state.trainerName) {
            trainerNameError = "trainerName is Required"
        }

        if (!this.state.email) {
            emailError = "email is missing"
        }

        if (!this.state.contactNumber) {
            contactNumberError = "contactNumber is required"
        }
        else {
            if (this.state.contactNumber < 1000000000) {
                contactNumberError = "Invalid Contact Number"
            }
        }

        if (!this.state.skillSet) {
            skillSetError = "skillSet is required"
        }

        if (!this.state.sessionId) {
            sessionIdError = "sessionId is Required"
        }

        if (!this.state.sessionDesc) {
            sessionDescError = "sessionDesc is Required"
        }

        if (!this.state.sessionTime) {
            sessionTimeError = "sessionTime is missing"
        }

        if (!this.state.sessionDate) {
            sessionDateError = "sessionDate is required"
        }

        if (!this.state.sessionSkillSet) {
            sessionSkillSetError = "sessionSkillSet is required"
        }

        if (trainerIdError || trainerNameError || contactNumberError || skillSetError || emailError
            || sessionIdError || sessionDescError || sessionTimeError || sessionDateError || sessionSkillSetError) {
            this.setState({
                trainerIdError: trainerIdError,
                trainerNameError: trainerNameError,
                skillSetError: skillSetError,
                contactNumberError: contactNumberError,
                emailError: emailError,
                sessionIdError: sessionIdError,
                sessionDescError: sessionDescError,
                sessionTimeError: sessionTimeError,
                sessionDateError: sessionDateError,
                sessionSkillSetError: sessionSkillSetError
            })
            return false;
        }
        return true;
    }

    submitHandler(event) {
        event.preventDefault();
        let isValid = this.validate();
        if (isValid) {
            const sessionData = {
                sessionid: this.state.sessionId,
                sessiondesc: this.state.sessionDesc,
                sessiondate: this.state.sessionDate,
                sessiontime: this.state.sessionTime
            }

            const trainerData = {
                id: this.state.trainerId,
                name: this.state.trainerName,
                contactNumber: this.state.contactNumber,
                email: this.state.email,
                skill_id: 1,
                session_id: this.state.sessionId
            }

            axios.post("http://localhost:8083/trainerapp/addTrainer", trainerData, {
                headers: {
                    Authorization: 'Bearer ' + this.props.token
                }
            }
            ).then((res) => {
                axios.post("http://localhost:8082/sessionapp/addsession", sessionData, {
                    headers: {
                        Authorization: 'Bearer ' + this.props.token
                    }
                }
                ).then((res2) => {
                    alert("Data submitted successfully")
                })
            })
        }
    }

    handleChange = (event) => {
        this.setState({ [event.target.name]: event.target.value })
    };

    handleSelect = (event, { value }) => {
        this.setState({ sessionSkillSet: value })
    };

    handleTrainerSelect = (event, { value }) => {
        this.setState({ skillSet: value })
    };

    render() {
        return (
            <div className="p-2">
                <h2>Add Trainer Details</h2>
                <Form onSubmit={this.submitHandler}>
                    <Row form className="mt-3">
                        <Col md={6}>
                            <FormGroup>
                                <Label for="TrainerId">Trainer Id</Label>
                                <Input type="number" name="trainerId" id="TrainerId" value={this.state.trainerId} onChange={this.handleChange} placeholder="Enter Id" />
                                <div style={{ fontSize: 12, color: "red" }}>{this.state.trainerIdError}</div>
                            </FormGroup>
                        </Col>
                        <Col md={6}>
                            <FormGroup>
                                <Label for="TrainerName">Trainer Name</Label>
                                <Input type="text" name="trainerName" id="TrainerName" value={this.state.trainerName} onChange={this.handleChange} placeholder="Enter Trainer Name" />
                                <div style={{ fontSize: 12, color: "red" }}>{this.state.trainerNameError}</div>
                            </FormGroup>
                        </Col>
                    </Row>

                    <Row form className="mt-3">
                        <Col md={4}>
                            <FormGroup>
                                <Label for="ContactNumber">Contact Number</Label>
                                <Input type="number" name="contactNumber" id="ContactNumber" value={this.state.contactNumber} onChange={this.handleChange} placeholder="Enter Contact Number" />
                                <div style={{ fontSize: 12, color: "red" }}>{this.state.contactNumberError}</div>
                            </FormGroup>
                        </Col>
                        <Col md={5}>
                            <FormGroup>
                                <Label for="Email">Email Address</Label>
                                <Input type="email" name="email" id="Email" value={this.state.email} onChange={this.handleChange} placeholder="Enter Email" />
                                <div style={{ fontSize: 12, color: "red" }}>{this.state.emailError}</div>
                            </FormGroup>
                        </Col>
                        <Col md={3}>
                            <FormGroup>
                                <Label for="skillSet">Skill Set</Label>
                                <Select onChange={this.handleTrainerSelect} options={this.state.skillOptions} name="skillSet" id="skillSet" placeholder="Select" />
                                <div style={{ fontSize: 12, color: "red" }}>{this.state.skillSetError}</div>
                            </FormGroup>
                        </Col>
                    </Row>

                    <hr />

                    <h2>Add Session Details</h2>
                    <Row form className="mt-3">
                        <Col lg={6}>
                            <FormGroup>
                                <Label for="sessionId">Session Id</Label>
                                <Input
                                    type="number"
                                    name="sessionId"
                                    id="sessionId"
                                    placeholder="Enter SessionId"
                                    value={this.state.sessionId}
                                    onChange={this.handleChange}
                                />
                            </FormGroup>
                            <div style={{ fontSize: 12, color: "red" }}>{this.state.sessionIdError}</div>
                        </Col>
                        <Col lg={3}>
                            <FormGroup>
                                <Label for="sessionDate">Session Date</Label>
                                <Input
                                    type="date"
                                    name="sessionDate"
                                    id="sessionDate"
                                    placeholder="Enter Session Date"
                                    value={this.state.sessionDate}
                                    onChange={this.handleChange}
                                />
                            </FormGroup>
                            <div style={{ fontSize: 12, color: "red" }}>{this.state.sessionDateError}</div>
                        </Col>
                        <Col lg={3}>
                            <FormGroup>
                                <Label for="sessionTime">Session Time</Label>
                                <Input
                                    type="time"
                                    name="sessionTime"
                                    id="sessionTime"
                                    placeholder="Enter Session Time"
                                    value={this.state.sessionTime}
                                    onChange={this.handleChange}
                                />
                            </FormGroup>
                            <div style={{ fontSize: 12, color: "red" }}>{this.state.sessionTimeError}</div>
                        </Col>
                    </Row>
                    <Row form className="mt-3">
                        <Col lg={9}>
                            <FormGroup>
                                <Label for="sessionDesc">Session Description</Label>
                                <Input
                                    type="text"
                                    name="sessionDesc"
                                    id="sessionDesc"
                                    placeholder="Enter Session Description"
                                    value={this.state.sessionDesc}
                                    onChange={this.handleChange}
                                />
                            </FormGroup>
                            <div style={{ fontSize: 12, color: "red" }}>{this.state.sessionDescError}</div>
                        </Col>
                        <Col lg={3}>
                            <FormGroup>
                                <Label for="sessionSkillSet">Skill Set</Label>
                                <Select onChange={this.handleSelect} options={this.state.skillOptions} id="sessionSkillSet" name="sessionSkillSet" placeholder="Select" />
                            </FormGroup>
                            <div style={{ fontSize: 12, color: "red" }}>{this.state.sessionSkillSetError}</div>
                        </Col>
                    </Row>

                    <Button className="mt-3">Submit</Button>
                </Form>
            </div>
        )
    }
}

export default AddTrainer
