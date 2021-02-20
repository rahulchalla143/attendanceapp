import logo from './logo.svg';
import './App.css';
import UserRegistration from './Components/UserRegistration';
import { BrowserRouter as Router,Route, } from "react-router-dom";
import Home from './Components/Home';
import AddTrainer from './Components/AddTrainer';
import AddSession from './Components/AddSession';
import LoginComponent from './Components/Login';


function App() {
  return (
    <Router>

   
    <div className="App">

     <switch>
       <Route path="/" component={Home} exact={true} />
       <Route path="/userregistration" component={UserRegistration} />
       <Route path="/login" component={LoginComponent} /> 
       <Route path="/trainer" component={AddTrainer} />
       <Route path="/session" component={AddSession} />

     </switch>
    </div>
    </Router>
  );
}

export default App;
