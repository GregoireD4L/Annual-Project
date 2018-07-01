import React, { Component } from 'react';
import {Route} from 'react-router';
import {LoginScreen} from "./LoginScreen";
import {BrowserRouter} from 'react-router-dom';


class ApplicationRoutes extends Component{

    constructor(props){
        super(props);
    }

    setRouter(router) {
        let {classes} = this.props;
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
                <Route path='/login' component={LoginScreen}/>
            </BrowserRouter>
        )
    }
}
export {ApplicationRoutes}