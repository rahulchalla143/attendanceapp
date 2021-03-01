import { React, Component } from "react";
import UserSessionComponent from "./UserSessionComponent";
import { Container, Row } from "react-bootstrap";

class UserViewEnrolledSessions extends Component {
    constructor(props) {
        super(props)
        this.list = []
        this.state = {
            searchKey: "",
            searchType:"trainerName",
            filteredList: [
                {
                    sessionId: 1,
                    sessionDesc: "This is going to be the description of the Session 1",
                    trainerName: "Trainer 1",
                    sessionDate: "2015-05-25",
                    sessionTime: "20:25",
                },
                {
                    sessionId: 2,
                    sessionDesc: "This is going to be the description of the Session 1",
                    trainerName: "Trainer 2",
                    sessionDate: "2015-05-25",
                    sessionTime: "20:25",
                },
                {
                    sessionId: 3,
                    sessionDesc: "This is going to be the description of the Session 1",
                    trainerName: "Trainer 3",
                    sessionDate: "2015-05-25",
                    sessionTime: "20:25",
                },
                {
                    sessionId: 4,
                    sessionDesc: "This is going to be the description of the Session 1",
                    trainerName: "Trainer 4",
                    sessionDate: "2015-05-25",
                    sessionTime: "20:25",
                }
            ],
            sessionsList: [
                {
                    sessionId: 1,
                    sessionDesc: "This is going to be the description of the Session 1",
                    trainerName: "Trainer 1",
                    sessionDate: "2015-05-25",
                    sessionTime: "20:25",
                },
                {
                    sessionId: 2,
                    sessionDesc: "This is going to be the description of the Session 1",
                    trainerName: "Trainer 2",
                    sessionDate: "2015-05-25",
                    sessionTime: "20:25",
                },
                {
                    sessionId: 3,
                    sessionDesc: "This is going to be the description of the Session 1",
                    trainerName: "Trainer 3",
                    sessionDate: "2015-05-25",
                    sessionTime: "20:25",
                },
                {
                    sessionId: 4,
                    sessionDesc: "This is going to be the description of the Session 1",
                    trainerName: "Trainer 4",
                    sessionDate: "2015-05-25",
                    sessionTime: "20:25",
                }
            ],
            userSessionComponentList: []
        }
        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSearchButton = this.handleSearchButton.bind(this);
        this.clearFilter = this.clearFilter.bind(this)
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

    componentDidMount() {
        this.state.filteredList.map(session => {
            this.list.push(<UserSessionComponent sessionDetails={session} userId={1} token="erewer" />)
        })
        this.setState({ userSessionComponentList: this.list }, () => {
            this.list = []
        })
    }

    clearFilter(){
        var filteredList = this.state.sessionsList.filter(session => session.trainerName.toLowerCase().includes(""))
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
    
    handleevent = (event) => {
        this.setState({searchType:event.target.value})
    }

    render() {
        console.log("????")
        console.log(this.state.userSessionComponentList)
        console.log("?????")
        return (
            <Container fluid className="px-5" style={{ backgroundColor: "black" }}>
                <h1 className="my-3" style={{ color: "silver" }}>Sessions Enrolled</h1>
                <Row className="d-flex flex-row">
                    <button onClick={this.clearFilter}>Clear Filter</button>
                    <select className="ml-auto" onChange={this.handleevent}>
                                    <option value="trainerName">Filter By</option>
                                    <option value="trainerName">TrainerName</option>
                                    <option value="sessionDesc" >Description</option>
                                    <option value="sessionDate" >Date</option>
                                    <option value="sessionTime" >Time</option>

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