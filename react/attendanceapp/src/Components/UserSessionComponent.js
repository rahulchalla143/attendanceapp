import React, { Component } from 'react'
import "../StyleSheets/MyStyle.css";
import Sidebar from "react-sidebar";
import { MenuButtonWideFill } from "react-bootstrap-icons";
import { Card, Badge, Button } from "react-bootstrap";
import axios from 'axios';


class UserSessionComponent extends Component {

    constructor(props) {
        super(props);
        this.state = {
            token: null,
            userId: 0,
            buttonData: "enroll",
            slot: 0,
            showFeedback: false,
            feedbackbutton: "Feedback",
            feedback:"",
            sessionDetails: {

            },
            userSessionsList: {

            }
        };
        this.setInitialState = this.setInitialState.bind(this);
        this.setButtonData = this.setButtonData.bind(this);
        this.onCancelButton = this.onCancelButton.bind(this);
        this.deRollSession = this.deRollSession.bind(this);
        this.setSlot = this.setSlot.bind(this);
        this.getAvailableSlot = this.getAvailableSlot.bind(this);
        this.setFeedback = this.setFeedback.bind(this);
        this.onFeedBackChange = this.onFeedBackChange.bind(this);
    }

    componentDidMount() {
        this.setInitialState()
    }

    componentDidUpdate() {
        if (this.state.sessionDetails != this.props.sessionDetails) {
            this.setInitialState()
        }
    }

    setInitialState() {
        this.setState({
            token: this.props.token,
            userId: this.props.userId,
            sessionDetails: this.props.sessionDetails,
            userSessionsList: this.props.userSessionsList
        }, () => {
            this.state.userSessionsList.forEach((sessionMap) => {
                if (sessionMap.sessionid === this.state.sessionDetails.sessionid) {
                    if (sessionMap.attended === "Yes") {
                        if (sessionMap.feedback === null) {
                            this.setState({ buttonData: "feedback" })
                        }
                        else {
                            this.setState({ buttonData: "" })
                        }
                    }
                    else if (false) {
                        this.setState({ buttonData: "mark" })
                    }
                    else {
                        this.setState({ buttonData: "deroll" })
                    }
                }
            })

        })
    }

    getAvailableSlot() {
        var list = []
        this.state.sessionDetails.availableslots.split(",").forEach(slot => {
            list.push(<Badge id={slot} onClick={this.setSlot} variant="light border border-danger p-3 mx-2">{slot}</Badge>)
        })
        return list
    }

    setButtonData() {
        if (this.state.buttonData == "enroll") {
            this.setState({ buttonData: "submit" })
        }
        if (this.state.buttonData == "submit") {
            console.log(this.state.token + "___" + this.state.slot)
            axios.post("http://localhost:8082/sessionapp/addsession/" + this.state.userId + "/" + this.state.sessionDetails.sessionid + "/" + this.state.slot,
                { dummy: "dummy" },
                {
                    headers: {
                        Authorization: 'Bearer ' + this.state.token
                    },
                })
                .then(res => {
                    this.setState({ buttonData: "deroll" })
                    alert("Session with ID " + this.state.sessionDetails.sessionid + " has been Enrolled Successfully to Slot " + this.state.slot)
                })
        }
    }

    setFeedback(event) {
        if (this.state.feedbackbutton === "Feedback") {
            this.setState({ feedbackbutton: "Submit", showFeedback: true })
        }
        if (this.state.feedbackbutton === "Submit") {
            axios.post("http://localhost:8082/sessionapp/setfeedback/" + this.state.userId + "/" + this.state.sessionDetails.sessionid + "/" + this.state.feedback,
            { dummy: "dummy" },
            {
                headers: {
                    Authorization: 'Bearer ' + this.state.token
                },
            })
            .then(res => {
                this.setState({ showFeedback: false, buttonData: "" })
                alert("Submitted feedback successfully!");
            })  
        }
    }

    onCancelButton() {
        this.setState({ buttonData: "enroll" })
    }

    onFeedBackChange(event){
        this.setState({feedback:event.target.value})
    }

    deRollSession() {
        if (window.confirm("Are you sure? Do you want to deroll?")) {
            axios.delete("http://localhost:8082/sessionapp/deletesession/" + this.state.sessionDetails.sessionid + "/" + this.state.userId,
                {
                    headers: {
                        Authorization: 'Bearer ' + this.props.token
                    },
                })
                .then(res => {
                    this.setState({ buttonData: "enroll" })
                    alert("Derolled succesfully!")
                })
        }
    }

    setSlot(event) {
        this.setState({ slot: event.target.id })
    }


    render() {
        console.log("______" + JSON.stringify(this.state.sessionDetails))
        return (
            <Card className="mx-5 my-3">
                <Card.Header className="d-flex flex-row" style={{ backgroundColor: "silver" }}>
                    <h5>Session Id : {this.state.sessionDetails.sessionid}</h5>
                    <span className="ml-auto">
                        <h5>Timings : {this.state.sessionDetails.sessiondate} {this.state.sessionDetails.sessiontime}</h5>
                    </span>
                </Card.Header>
                <Card.Body>
                    <Card.Title>Trainer Name : {this.state.sessionDetails.trainerName}</Card.Title>
                    <Card.Text>
                        {this.state.sessionDetails.sessiondesc}
                    </Card.Text>
                    {this.state.showFeedback===true && <textarea className="d-flex flex-row w-100 m-2" value={this.state.feedback} type="textarea" placeholder="Enter FeedBack" onChange={this.onFeedBackChange}></textarea>}
                    <div className="d-flex flex-row">
                        {this.state.buttonData == "submit" && <div className="d-flex flex-row">
                            <h6 className="text-muted pt-3">Select Slot : </h6>
                            {this.getAvailableSlot()}
                        </div>}
                        {this.state.buttonData === "enroll" && <Button className="ml-auto mr-2" onClick={this.setButtonData}>Enroll</Button>}
                        {this.state.buttonData === "submit" && <Button className="ml-auto mr-2" onClick={this.onCancelButton}>Cancel</Button>}
                        {this.state.buttonData === "submit" && <Button className="mx-2" onClick={this.setButtonData}>Submit</Button>}
                        {this.state.buttonData === "feedback" && <Button className="ml-auto mr-2" name="feedback" onClick={this.setFeedback}>{this.state.feedbackbutton}</Button>}
                        {this.state.buttonData === "deroll" && <Button className="ml-auto mr-2" onClick={this.deRollSession}>Deroll</Button>}

                    </div>
                </Card.Body>
            </Card >
        )
    }
}

export default UserSessionComponent;