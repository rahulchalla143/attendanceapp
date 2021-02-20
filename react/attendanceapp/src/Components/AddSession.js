import React, { Component } from 'react'
import { Col, Button, Form, FormGroup, Label, Input, FormText,Row } from 'reactstrap';


class AddSession extends Component {
    render() {
        return (
            <div>
            <Form>
           
            <FormGroup check inline>
              <Label for="exampleEmail">Email</Label>
              <Input
                type="email"
                name="email"
                id="exampleEmail"
                placeholder="with a placeholder"
              />
            </FormGroup>
            <FormGroup>
              <Label for="examplePassword">Password</Label>
              <Input
                type="password"
                name="password"
                id="examplePassword"
                placeholder="password placeholder"
              />
            </FormGroup>
            <FormGroup>
              <Label for="exampleUrl">Url</Label>
              <Input
                type="url"
                name="url"
                id="exampleUrl"
                placeholder="url placeholder"
              />
            </FormGroup>
            <FormGroup>
              <Label for="exampleNumber">Number</Label>
              <Input
                type="number"
                name="number"
                id="exampleNumber"
                placeholder="number placeholder"
              />
            </FormGroup>
            <FormGroup>
              <Label for="exampleDatetime">Datetime</Label>
              <Input
                type="datetime"
                name="datetime"
                id="exampleDatetime"
                placeholder="datetime placeholder"
              />
            </FormGroup>
            <FormGroup>
              <Label for="exampleDate">Date</Label>
              <Input
                type="date"
                name="date"
                id="exampleDate"
                placeholder="date placeholder"
              />
            </FormGroup>
            <FormGroup>
              <Label for="exampleTime">Time</Label>
              <Input
                type="time"
                name="time"
                id="exampleTime"
                placeholder="time placeholder"
              />
            </FormGroup>
            <FormGroup>
              <Label for="exampleColor">Color</Label>
              <Input
                type="color"
                name="color"
                id="exampleColor"
                placeholder="color placeholder"
              />
            </FormGroup>
            <FormGroup>
              <Label for="exampleSearch">Search</Label>
              <Input
                type="search"
                name="search"
                id="exampleSearch"
                placeholder="search placeholder"
              />
            </FormGroup>
          </Form>
          </div>
        );
    }
}

export default AddSession
