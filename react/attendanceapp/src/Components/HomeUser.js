import React, { Component } from 'react'
import "../StyleSheets/MyStyle.css";
import MenuComponent from './MenuComponents';
import Sidebar from "react-sidebar";
import { MenuButtonWideFill } from "react-bootstrap-icons";
import { Container, Row, Button } from "react-bootstrap";
import UserViewSessions from './UserViewSessions';
import UserViewEnrolledSessions from './UserViewEnrolledSessions';
import Notifications from './Notifications';

const mql = window.matchMedia(`(min-width: 800px)`);

class Home extends Component {

    constructor(props) {
        super(props);
        this.state = {
            content:"viewSessions",
            sidebarMaxWidth: "25%",
            sidebarDocked: mql.matches,
            sidebarOpen: false,
            token: null,
            userId: 0,
            userName: null,
            role: null,
            email: null,
        };
        this.mediaQueryChanged = this.mediaQueryChanged.bind(this);
        this.onSetSidebarOpen = this.onSetSidebarOpen.bind(this);
        this.onHamBurgerClicked = this.onHamBurgerClicked.bind(this);
        this.onHamBurgerClosed = this.onHamBurgerClosed.bind(this);
        this.setInitialState = this.setInitialState.bind(this);
        this.setContent = this.setContent.bind(this);
    }

    componentWillMount() {
        mql.addListener(this.mediaQueryChanged);
    }

    mediaQueryChanged() {
        if(mql.matches){
            this.setState({ sidebarDocked: mql.matches, sidebarOpen: false, sidebarMaxWidth:"25%" });       
        }
        else{
            this.setState({ sidebarDocked: mql.matches, sidebarOpen: false, sidebarMaxWidth:"90%" });
        }
    }

    onSetSidebarOpen(open) {
        this.setState({ sidebarOpen: open });
    }

    componentWillUnmount() {
        mql.removeListener(this.mediaQueryChanged);
    }

    onHamBurgerClicked() {
        this.setState({ sidebarOpen: true });
    }

    onHamBurgerClosed() {
        this.setState({ sidebarOpen: false });
    }

    componentDidMount() {
        this.setInitialState()
    }

    setContent(event){
        this.setState({content:event.target.id})
    }

    setInitialState() {
        try {
            this.setState({
                token: this.props.location.state.token,
                userId: this.props.location.state.userId,
                userName: this.props.location.state.userName,
                role: this.props.location.state.role,
                email: this.props.location.state.email,
                age: this.props.location.state.age,
                gender: this.props.location.state.gender,
                contact: this.props.location.state.contact
            })
        }
        catch (e) {
            this.setState({
                token: this.props.token,
                userId: this.props.userId,
                userName: this.props.userName,
                role: this.props.role,
                email: this.props.email,
                age: this.props.age,
                gender: this.props.gender,
                contact: this.props.contact
            })
        }
    }


    render() {
        return (
            < div >
                <MenuComponent
                    token={this.state.token}
                    userId={this.state.userId}
                    userName={this.state.userName}
                    role={this.state.role}
                    email={this.state.email}
                />
                <Sidebar
                    styles={
                        {
                        root: {
                            marginTop: "62px"
                        },
                        sidebar:{
                            width:this.state.sidebarMaxWidth
                        }
                    }
                    }

                    sidebar={
                        <Container fluid className="bgimage">
                            <Row>
                                {!this.state.sidebarDocked && <MenuButtonWideFill size={30} onClick={this.onHamBurgerClosed} />}
                            </Row>
                            <Row className="d-flex justify-content-center">
                                <h1 className="text-white font-weight-lighter my-4">Welcome {this.state.userName}</h1>
                            </Row>
                            <hr style={{borderTop:"1px solid white"}}/>
                            <Row className="d-flex justify-content-center mt-5">
                                <Button className="btn bg-transparent border border-white p-3 px-5 mb-3" id="viewSessions" onClick={this.setContent}>View Sessions</Button>
                            </Row>
                            <Row className="d-flex justify-content-center">
                                <Button className="btn bg-transparent border border-white p-3 px-5 mb-3" id="enrolledSessions" onClick={this.setContent}>Enrolled Sessions</Button>
                            </Row>
                            <Row className="d-flex justify-content-center">
                                <Button className="btn bg-transparent border border-white p-3 px-5 mb-3" id="notifications" onClick={this.setContent}>Notifications</Button>
                            </Row>
                            <div style={{height:"180px"}}/>
                        </Container>
                    }
                    open={this.state.sidebarOpen}
                    docked={this.state.sidebarDocked}
                    onSetOpen={this.onSetSidebarOpen}>
                    <Container>

                        <Row>
                            {!this.state.sidebarDocked && <MenuButtonWideFill size={30} onClick={this.onHamBurgerClicked} />}
                        </Row>
                        <Row>
                            {this.state.content=="viewSessions" && <UserViewSessions/>}
                            {this.state.content=="enrolledSessions" && <UserViewEnrolledSessions/>}
                            {this.state.content=="notifications" && <Notifications/>}
                        </Row>
                    </Container>
                </Sidebar>

            </div >
        )
    }
}

export default Home;