import React, { Component } from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import AppBar from 'material-ui/AppBar';
import RaisedButton from 'material-ui/RaisedButton';
import TextField from 'material-ui/TextField';
import firebase from "firebase";
import * as Colors from 'material-ui/styles/colors';
import getMuiTheme from 'material-ui/styles/getMuiTheme';

const muiTheme = getMuiTheme({
    palette: {
        textColor: Colors.darkBlack,
        primary1Color: '#11c1ff',
        primary2Color: Colors.indigo700,
        accent1Color: Colors.redA200,
        pickerHeaderColor: Colors.darkBlack,
        alternateTextColor: Colors.white
    },
    appBar: {
        height: 60,
    },

    textField: {
        top:'50%',
        left:'50%'
    }
});


// Initialize Firebase
var config = {
    apiKey: "AIzaSyAanpOteKt6sJER051WlLtlf3oYHduwpTM",
    authDomain: "data-for-life.firebaseapp.com",
    databaseURL: "https://data-for-life.firebaseio.com",
    projectId: "data-for-life",
    storageBucket: "data-for-life.appspot.com",
    messagingSenderId: "1065890119840"
};
firebase.initializeApp(config);

class Login extends Component{

    constructor(props){
        super(props);
        this.state={
            email:'',
            password:''
        }

        this.handleChangeEmail = this.handleChangeEmail.bind(this);
        this.handleChangePassword = this.handleChangePassword.bind(this);
    }

    handleClick(event){
        firebase.auth().signInWithEmailAndPassword(this.state.email, this.state.password).catch(function(error) {
            // Handle Errors here.
            var errorCode = error.code;
            var errorMessage = error.message;
            console.error(errorMessage);
            console.error(this.state.email);
        });
        //connecté
    }

    handleChangeEmail(event, newValue){
        this.setState({
            email: newValue
        });
    }

    handleChangePassword(event, newValue){
        this.setState({
            password: newValue
        });
    }

    render() {
        return (
            <div>
                <MuiThemeProvider muiTheme={muiTheme}>
                    <div>
                        <AppBar
                            title="Login"
                        />
                        <TextField
                            hintText="Enter your Email"
                            floatingLabelText="Email"
                            onChange = {(event,newValue) => this.handleChangeEmail(event, newValue)}
                        />
                        <br/>
                        <TextField
                            type="password"
                            hintText="Enter your Password"
                            floatingLabelText="Password"
                            onChange = {(event,newValue) => this.handleChangePassword(event, newValue)}
                        />
                        <br/>
                        <RaisedButton label="Submit" primary={true} style={style} onClick={(event) => this.handleClick(event)}/>
                    </div>
                </MuiThemeProvider>
            </div>
        );
    }
}
const style = {
    margin: 15,
};

//Login = withStyles(Login);

export default Login;