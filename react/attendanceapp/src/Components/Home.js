import React, { Component } from 'react'
import "../StyleSheets/MyStyle.css";
import MenuComponent from './MenuComponents';
import AddTrainer from './AddTrainer';
import AddSession from './AddSession';
import Sidebar from "react-sidebar";
import { MenuButtonWideFill } from "react-bootstrap-icons";
import { Container, Row, Button } from "react-bootstrap";
import {Link} from "react-router-dom";

const mql = window.matchMedia(`(min-width: 800px)`);

class Home extends Component {


    constructor(props) {
        super(props)
        this.state = {
            content: "viewTrainers",
            sidebarMaxWidth: "25%",
            sidebarDocked: mql.matches,
            sidebarOpen: false,
            token: null,
            userId: 0,
            userName: null,
            role: null,
            email: null,
            age: null,
            gender: null,
            contact: null
        }
        this.mediaQueryChanged = this.mediaQueryChanged.bind(this);
        this.onSetSidebarOpen = this.onSetSidebarOpen.bind(this);
        this.onHamBurgerClicked = this.onHamBurgerClicked.bind(this);
        this.onHamBurgerClosed = this.onHamBurgerClosed.bind(this);
        this.setInitialState = this.setInitialState.bind(this);
        this.setContent = this.setContent.bind(this);
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




    render() {
        if (this.state.userId == 0 || this.state.userId == undefined) {
            return (
                <div className="bgimage">
                    <MenuComponent userId={0}/>
                    <Container fluid className="p-5 mt-1">
                        <h1 className="text-white mb-5">Welcome To AttendanceApp</h1>
                        <div style={{height:"200px"}}>

                        </div>
                        <h2 className="text-white font-weight-lighter mb-5 mt-5">Please <Link to="/login">Login</Link> with your Email</h2>
                        <h2 className="text-white font-weight-lighter mb-5">New User? Register <Link to="/userregistration">Here</Link></h2>
                    </Container>
                </div>
            )
        }
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
                                <Button className="btn bg-transparent border border-white p-3 px-5 mb-3" id="viewTrainers" onClick={this.setContent}>View Trainers</Button>
                            </Row>
                            <Row className="d-flex justify-content-center">
                                <Button className="btn bg-transparent border border-white p-3 px-5 mb-3" id="viewSessions" onClick={this.setContent}>View Sessions</Button>
                            </Row>
                            <Row className="d-flex justify-content-center">
                                <Button className="btn bg-transparent border border-white p-3 px-5 mb-3" id="addSkills" onClick={this.setContent}>Add Skills</Button>
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
                            {this.state.content=="viewTrainers" && <AddTrainer/>}
                            {this.state.content=="viewSessions" && <AddSession/>}
                            {this.state.content=="addSkills" && <AddTrainer/>}
                        </Row>
                    </Container>
                </Sidebar>

            </div >
        )
    }
}

export default Home;