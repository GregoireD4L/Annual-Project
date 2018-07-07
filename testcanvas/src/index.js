/*import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import registerServiceWorker from './registerServiceWorker';
import {ApplicationRoutes} from "./ApplicationRoutes";

//localhost:3000/login to go to login page

ReactDOM.render(<ApplicationRoutes />, document.getElementById('root'));
registerServiceWorker();*/

import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import registerServiceWorker from './registerServiceWorker';
import {ApplicationRoutes} from "./ApplicationRoutes";
import ECG from './ECG';
import Accelero from './Accelero';
import Temp from './Temp';
import Respi from './Respi';

//localhost:3000/login to go to login page

ReactDOM.render(<Respi/>, document.getElementById('root'));
registerServiceWorker();
