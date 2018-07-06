import React, { Component } from 'react';
import {Route, Redirect} from 'react-router';
import {LoginScreen} from "./LoginScreen";
import {BrowserRouter} from 'react-router-dom';
import {LoggedPage} from "./LoggedPage";
import firebase from './FirebaseConfig';

class ApplicationRoutes extends Component{

    constructor(props){
        super(props);
        this.state = {
            isLogged: false,
        };
        this.checkAuth = this.checkAuth.bind(this);
    }

    checkAuth(){
        var user = firebase.auth().currentUser;
        if(user){
            this.setState({
                isLogged: true,
            });
        }else{
            this.setState({
                isLogged: false,
            });
        }
    }

    componentDidMount(){
        this.checkAuth();
    }

    setRouter(router) {
        if (router) {
            router.history.listen((location, action) => {
                console.log(`The current URL is ${location.pathname}${location.search}${location.hash}`);
                console.log(`Url base `);
                console.log(`The last navigation action was ${action}`);
                if (window.parent) {
                    window.parent.history.replaceState(window.parent.history.state, location.pathname, location.pathname)
                }
            });
        }
    }

    render(){
        return (
            <BrowserRouter ref={this.setRouter.bind(this)}>
                <div>
                    <Route path='/login' component={LoginScreen}/>
                    <Route path='/home' component={LoggedPage}/>
                </div>
            </BrowserRouter>
        )
    }
}
export {ApplicationRoutes}