import React, { Component } from 'react'
import { Col, Row, Button, Form, FormGroup, Label, Input } from 'reactstrap';


class AddTrainer extends Component {
    render() {
        return (
            <div className="p-2">
                <h2>Add Trainer Details</h2>
                <Form>
                    <Row form className="mt-3">
                        <Col md={6}>
                            <FormGroup>
                                <Label for="TrainerId">Trainer Id</Label>
                                <Input type="email" name="TrainerId" id="TrainerId" placeholder="Enter Id" />
                            </FormGroup>
                        </Col>
                        <Col md={6}>
                            <FormGroup>
                                <Label for="TrainerName">Trainer Name</Label>
                                <Input type="text" name="TrainerName" id="TrainerName" placeholder="Enter Trainer Name" />
                            </FormGroup>
                        </Col>
                    </Row>

                    <Row form className="mt-3">
                        <Col md={6}>
                            <FormGroup>
                                <Label for="ContactNumber">Contact Number</Label>
                                <Input type="text" name="ContactNumber" id="ContactNumber" placeholder="Enter Contact Number" />
                            </FormGroup>
                        </Col>
                        <Col md={4}>
                            <FormGroup>
                                <Label for="Email">Email Address</Label>
                                <Input type="email" name="Email" id="Email" placeholder="Enter Email" />
                            </FormGroup>
                        </Col>
                        <Col md={2}>
                            <FormGroup>
                                <Label for="select">Skill Set</Label>
                                <Input type="select" name="select" id="select">
                                    <option>Select</option>
                                    <option>User</option>
                                    <option>Admin</option>

                                </Input>
                            </FormGroup>
                        </Col>
                    </Row>

                    <Button className="mt-3">Submit</Button>
                </Form>
            </div>
        )
    }
}

export default AddTrainer
