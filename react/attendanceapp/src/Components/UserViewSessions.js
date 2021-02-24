import { React, Component } from "react";
import UserSessionComponent from "./UserSessionComponent";
import { Container } from "react-bootstrap";



class UserViewSessions extends Component {

    constructor(props) {
        super(props)
        this.list=[]
        this.state={
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
            userSessionComponentList:[]
        }
    }



    componentDidMount(){
        this.state.sessionsList.map(session=>{
            this.list.push(<UserSessionComponent sessionDetails={session} userId={1} token="erewer"/>)
        })
        this.setState({userSessionComponentList:this.list})
    }

    render() {
        return (
            <Container fluid className="px-5" style={{backgroundColor:"black"}}>
                <h1 className="my-3" style={{color:"silver"}}>Sessions Available</h1>
                {this.state.userSessionComponentList}
            </Container>
        )
    }

}

export default UserViewSessions;