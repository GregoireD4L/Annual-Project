import React, { Component } from 'react';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import AppBar from 'material-ui/AppBar';
import RaisedButton from 'material-ui/RaisedButton';
import TextField from 'material-ui/TextField';
import * as Colors from 'material-ui/styles/colors';
import getMuiTheme from 'material-ui/styles/getMuiTheme';
import firebase from './FirebaseConfig';

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


class Register extends Component {
    constructor(props){
        super(props);
        this.state={
            first_name:'',
            last_name:'',
            email:'',
            password:''
        }

        this.handleChangeEmail = this.handleChangeEmail.bind(this);
        this.handleChangePassword = this.handleChangePassword.bind(this);
        this.handleChangeLastName = this.handleChangeLastName.bind(this);
        this.handleChangeFirstName = this.handleChangeFirstName.bind(this);
    }



    handleClick(event){
        firebase.auth().createUserWithEmailAndPassword(this.state.email, this.state.password).catch(function(error) {
            // Handle Errors here.
            var errorCode = error.code;
            var errorMessage = error.message;
            console.error(errorMessage);
            console.error(this.state.email);
        });
        //connecté
        var userId = firebase.auth().currentUser.uid;
        this.writeUserData(userId,this.state.email, this.state.first_name,this.state.last_name);
    }

    writeUserData(userId, firstName, lastName ,email) {
        firebase.database().ref('users/' + userId).set({
            email: email,
            firstName: firstName,
            lastName : lastName
        });
    }

    handleChangeEmail(event, newValue){
        this.setState({
            email: newValue
        });
    }

    handleChangeFirstName(event, newValue){
        this.setState({
            first_name: newValue
        });
    }

    handleChangeLastName(event, newValue){
        this.setState({
            last_name: newValue
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
                            title="Register"
                        />
                        <TextField
                            hintText="Enter your First Name"
                            floatingLabelText="First Name"
                            onChange = {(event,newValue) => this.handleChangeFirstName(event, newValue)}
                        />
                        <br/>
                        <TextField
                            hintText="Enter your Last Name"
                            floatingLabelText="Last Name"
                            onChange = {(event,newValue) => this.handleChangeLastName(event, newValue)}
                        />
                        <br/>
                        <TextField
                            hintText="Enter your Email"
                            type="email"
                            floatingLabelText="Email"
                            onChange = {(event,newValue) => this.handleChangeEmail(event, newValue)}
                        />
                        <br/>
                        <TextField
                            type = "password"
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
export default Register;