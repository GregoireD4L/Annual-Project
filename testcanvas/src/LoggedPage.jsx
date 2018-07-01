import React, { Component } from 'react';
import {Layout} from "./Layout";
import {MuiThemeProvider} from '@material-ui/core/styles';
import {theme} from "./theme";
import {Toolbar, Typography, withStyles} from '@material-ui/core';
import firebase from './FirebaseConfig';

const styles = {
    typography: {
        color: theme.palette.primary.text,
    },
};

class LoggedPage extends Component{

    constructor(props){
        super(props);
        this.state = {
            firstName: '',
            lastName: '',
        };
        this.getUserInfos = this.getUserInfos.bind(this);
    }

    getUserInfos(){
        let user = firebase.auth().currentUser;
        if(user) {
            var firstname='';
            var lastname='';
            firebase.database().ref('/users/' + user.uid).child("firstName").on('value', function(snapshot){
               firstname = snapshot.val();
               console.log(firstname);
            });
            firebase.database().ref('/users/' + user.uid).child("lastName").on('value', function(snapshot){
                lastname = snapshot.val();
                console.log(lastname);
            });
            this.setState({
                firstName: firstname,
                lastName: lastname,
            });
        }
    }

    componentWillMount(){
        this.getUserInfos();
    }

    render(){
        let {classes} = this.props;
        return (
            <MuiThemeProvider theme={theme}>
                <Layout footer="Data for life copyright 2018" appBar={
                    <Toolbar>
                        <Typography className={classes.typography} variant="title" color="inherit">
                            Welcome {this.state.firstName} {this.state.lastName}
                        </Typography>
                    </Toolbar>}>

                </Layout>
            </MuiThemeProvider>
        );
    }
}

LoggedPage = withStyles(styles)(LoggedPage);

export {LoggedPage}