import { React, Component } from "react";
import UserSessionComponent from "./UserSessionComponent";
import { Container } from "react-bootstrap";
import axios from "axios";



class UserViewSessions extends Component {

    constructor(props) {
        super(props)
        this.list = []
        this.state = {
            token: "",
            userId:0,
            sessionsList: [

            ],
            userSessionComponentList: []
        }
        this.setInitialState = this.setInitialState.bind(this)
    }

    componentDidUpdate() {
        if (this.props.token != this.state.token) {
            this.setInitialState();
        }
    }

    setInitialState() {
        axios.get("http://localhost:8082/sessionapp/sessions",
            {
                headers: {
                    Authorization: 'Bearer ' + this.props.token
                },
            })
            .then((res) => {
                console.log(JSON.stringify(res.data))
                this.setState({ token: this.props.token, userId: this.props.userId, sessionsList: res.data }, () => {
                    this.state.sessionsList.map(session => {
                        this.list.push(<UserSessionComponent sessionDetails={session} userId={this.state.userId} token={this.state.token} />)
                    })
                    this.setState({ userSessionComponentList: this.list }, () => {
                        this.list = []
                    })
                })
            })
            .catch((e) => {
                console.log("ERRRRRRRrr" + e)
            })
    }

    render() {
        return (
            <Container fluid className="px-5" style={{ backgroundColor: "black" }}>
                <h1 className="my-3" style={{ color: "silver" }}>Sessions Available</h1>
                {this.state.userSessionComponentList}
            </Container>
        )
    }

}

export default UserViewSessions;