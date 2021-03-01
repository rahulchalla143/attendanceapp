import { React, Component } from "react";
import UserSessionComponent from "./UserSessionComponent";
import { Container, Row } from "react-bootstrap";
import axios from 'axios';

class UserViewEnrolledSessions extends Component {
    constructor(props) {
        super(props)
        this.list = []
        this.state = {
            searchKey: "",
            searchType:"sessionid",
            filteredList: [
                
            ],
            sessionsList: [
                
            ],
            userSessionComponentList: []
        }
        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSearchButton = this.handleSearchButton.bind(this);
        this.clearFilter = this.clearFilter.bind(this)
        this.setInitialState = this.setInitialState.bind(this)
    }

    handleInputChange(event) {
        this.setState({ searchKey: event.target.value })
    }

    handleSearchButton() {
        const searchKind = this.state.searchType
        console.log(this.state.searchKey)
        var filteredList = this.state.sessionsList.filter(session => session[searchKind].toLowerCase().includes(this.state.searchKey.toLowerCase()))
        console.log(filteredList)
        filteredList.map(session => {
            this.list.push(<UserSessionComponent sessionDetails={session} userId={1} token="erewer" />)
        })
        console.log(this.list)
        this.setState({ userSessionComponentList: this.list }, () => {
            console.log(this.state.userSessionComponentList)
            this.list = []
        })
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
                this.setState({ token: this.props.token, userId: this.props.userId, sessionsList: res.data, filteredList: res.data }, () => {
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

    componentDidMount() {
        this.state.filteredList.map(session => {
            this.list.push(<UserSessionComponent sessionDetails={session} userId={this.state.userId} token={this.state.token} />)
        })
        this.setState({ userSessionComponentList: this.list }, () => {
            this.list = []
        })
    }

    clearFilter(){
        var filteredList = this.state.sessionsList.filter(session => session.sessionid.toLowerCase().includes(""))
        console.log(filteredList)
        filteredList.map(session => {
            this.list.push(<UserSessionComponent sessionDetails={session} userId={this.state.userId} token={this.state.token} />)
        })
        console.log(this.list)
        this.setState({ userSessionComponentList: this.list }, () => {
            console.log(this.state.userSessionComponentList)
            this.list = []
        })
    }
    
    handleevent = (event) => {
        this.setState({searchType:event.target.value})
    }

    render() {

        return (
            <Container fluid className="px-5" style={{ backgroundColor: "black" }}>
                <h1 className="my-3" style={{ color: "silver" }}>Sessions Enrolled</h1>
                <Row className="d-flex flex-row">
                    <button onClick={this.clearFilter}>Clear Filter</button>
                    <select className="ml-auto" onChange={this.handleevent}>
                                    <option value="sessionid">Filter By</option>
                                    <option value="trainername">TrainerName</option>
                                    <option value="sessiondesc" >Description</option>
                                    <option value="sessiondate" >Date</option>
                                    <option value="sessiontime" >Time</option>

                                </select>
                    <input type="text" value={this.state.searchKey} onChange={this.handleInputChange}>
                    </input>
                    <button type="submit" onClick={this.handleSearchButton}>Search</button>
                </Row>
                {this.state.userSessionComponentList}
            </Container>
        )
    }


}

export default UserViewEnrolledSessions;