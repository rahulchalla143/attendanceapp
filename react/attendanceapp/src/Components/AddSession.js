import React, { Component } from 'react'
import { Col, Button, Form, FormGroup, Label, Input, FormText, Row } from 'reactstrap';
import axios from 'axios';
import { Select } from 'semantic-ui-react';


class AddSession extends Component {

  constructor(props) {
    super(props)
    this.state = {
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
      ]
    }
    this.submitHandler = this.submitHandler.bind(this)
    this.handleChange = this.handleChange.bind(this)
    this.handleSelect = this.handleSelect.bind(this)
  }


  validate = () => {
    let sessionIdError = "";
    let sessionDateError = "";
    let sessionTimeError = "";
    let sessionDescError = "";
    let sessionSkillSetError = "";

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

    if (sessionIdError || sessionDescError || sessionTimeError || sessionDateError || sessionSkillSetError) {
      this.setState({
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
      const data = {
        sessionid: this.state.sessionId,
        sessiondesc: this.state.sessionDesc,
        sessiondate: this.state.sessionDate,
        sessiontime: this.state.sessionTime,
      }

      console.log(JSON.stringify(data))
      axios.post("http://localhost:8082/sessionapp/addsession", data,{
        headers:{
          Authorization : 'Bearer '+this.props.token
        }
      }
      ).then((res) => {
        alert("Data submitted successfully")
      })
    }
  }

  handleChange = (event) => {
    this.setState({ [event.target.name]: event.target.value })
  };

  handleSelect = (event, { value }) => {
    this.setState({ sessionSkillSet: value })
  };


  render() {
    return (
      <div className="p-2">
        <h2>Add Session Details</h2>
        <Form onSubmit={this.submitHandler}>
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
                <Label for="skillSet">Skill Set</Label>
                <Select onChange={this.handleSelect} options={this.state.skillOptions} id="skillSet" name="skillSet" placeholder="Select" />
              </FormGroup>
              <div style={{ fontSize: 12, color: "red" }}>{this.state.sessionSkillSetError}</div>
            </Col>
          </Row>

          <Button className="mt-3">Submit</Button>

        </Form>
      </div>
    );
  }
}

export default AddSession
