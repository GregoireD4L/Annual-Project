import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import registerServiceWorker from './registerServiceWorker';
import {ApplicationRoutes} from "./ApplicationRoutes";

//localhost:3000/login to go to login page

ReactDOM.render(<ApplicationRoutes />, document.getElementById('root'));
registerServiceWorker();
