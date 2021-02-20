import React, { Component } from 'react'
import { Menu } from 'semantic-ui-react';
import "../StyleSheets/MyStyle.css";
import MenuComponent from './MenuComponents';
import AddTrainer from './AddTrainer';
import AddSession from './AddSession';
import { Container, Row, Col } from 'react-bootstrap';


class Home extends Component {
    

    constructor(props) {
        super(props)
        this.handleMenuSelect = this.handleMenuSelect.bind(this);
        this.setInitialState = this.setInitialState.bind(this);
        this.state = {
            content:"addTrainer",
            token: null,
            userId: 0,
            userName: null,
            role:null,
            email:null,
            age:null,
            gender:null,
            contact:null
        }
    }

    setInitialState(){
        try{
                this.setState({
                    token: this.props.location.state.token,
                    userId: this.props.location.state.userId,
                    userName: this.props.location.state.userName,
                    role:this.props.location.state.role,
                    email:this.props.location.state.email,
                    age:this.props.location.state.age,
                    gender:this.props.location.state.gender,
                    contact:this.props.location.state.contact
                })
            }
            catch(e){
                this.setState({
                    token: this.props.token,
                    userId: this.props.userId,
                    userName: this.props.userName,
                    role:this.props.role,
                    email:this.props.email,
                    age:this.props.age,
                    gender:this.props.gender,
                    contact:this.props.contact
                })
            }
    }

    handleMenuSelect(event,{name}){
        this.setState({content:name})
    }

    
    render() {
        if(this.state.userId==0){
            this.setInitialState()
        }
        console.log(JSON.stringify(this.state))
        return(
            <div>
                <MenuComponent
                 token={this.state.token}
                 userId={this.state.userId}
                 userName={this.state.userName}
                 role={this.state.role}
                 email={this.state.email}
                 age={this.state.age}
                 gender={this.state.gender}
                 contact={this.state.contact}
                />
                <Container fluid>
                    <Row>
                        <Col lg={2} className="sideBar bg-dark">
                            <Menu vertical fluid className="bg-dark my-4">
                                <h2 className="bg-dark text-white mb-5">Welcome {this.state.userName}</h2>
                                <Menu.Item className="bg-light mb-2" name="addTrainer" onClick={this.handleMenuSelect}>
                                    AddTrainer
                                </Menu.Item>
                                <Menu.Item className="bg-light mb-2" name="addSession" onClick={this.handleMenuSelect}>
                                    AddSession
                                </Menu.Item>
                                <Menu.Item className="bg-light mb-2" name="addTrainer" onClick={this.handleMenuSelect}>
                                    AddTrainer
                                </Menu.Item>
                                <Menu.Item className="bg-light mb-2" name="addTrainer" onClick={this.handleMenuSelect}>
                                    AddTrainer
                                </Menu.Item>
                            </Menu>
                        </Col>
                        <Col lg={9}>
                            {this.state.content==="addTrainer" && <AddTrainer/>}
                            {this.state.content==="addSession" && <AddSession/>}
                        </Col>
                    </Row>
                </Container>
            </div>
        )
        }
    }

    export default Home;
