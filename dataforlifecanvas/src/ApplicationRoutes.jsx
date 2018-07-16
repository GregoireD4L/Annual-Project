import React, { Component } from 'react';
import {Route, Redirect, withRouter} from 'react-router';
import {LoginScreen} from "./LoginScreen";
import {BrowserRouter} from 'react-router-dom';
import {LoggedPage} from "./LoggedPage";
import firebase from './FirebaseConfig';

class ApplicationRoutes extends Component{

    constructor(props){
        super(props);
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

    getCookie(cname) {
        var name = cname + "=";
        var decodedCookie = decodeURIComponent(document.cookie);
        var ca = decodedCookie.split(';');
        for(var i = 0; i <ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }

    render(){
        var currentUser = this.getCookie("email");
        return (
            <BrowserRouter ref={this.setRouter.bind(this)}>
                <div>
				<Route path='/' render={() => {
                        return ((firebase.auth().currentUser || currentUser!=='') ? <LoggedPage/> : <Redirect to='/login'/>);
                    }}/>
                    <Route path='/login' component={LoginScreen}/>
                    <Route path='/home' render={() => {
                        return ((firebase.auth().currentUser || currentUser!=='') ? <LoggedPage/> : <Redirect to='/login'/>);
                    }}/>
                </div>
            </BrowserRouter>
        )
    }
}
export {ApplicationRoutes}