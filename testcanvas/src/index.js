import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import Login from './Login';
import registerServiceWorker from './registerServiceWorker';
import Register from "./Register";
import Loginscreen from "./LoginScreen";

ReactDOM.render(<Loginscreen />, document.getElementById('root'));
registerServiceWorker();
