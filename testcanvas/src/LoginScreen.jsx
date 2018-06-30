import React, { Component } from 'react';
import {Toolbar, Typography,
    Card, Button, withStyles,
    CardContent, CardActions, TextField} from '@material-ui/core';
import {MuiThemeProvider} from '@material-ui/core/styles';
import {Layout} from "./Layout";
import {theme} from "./theme";
import firebase from "firebase";

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

const styles = {
    card: {
        float: "none",
        margin: "0 auto",
        marginTop: '200px',
        maxWidth: 400,
        maxHeight: 400,
    },
    cardLogin: {
        float: "none",
        margin: "0 auto",
        marginTop: '170px',
        maxWidth: 400,
        maxHeight: 400,
    },
    cardRegister: {
        float: "none",
        margin: "0 auto",
        marginTop: '150px',
        maxWidth: 400,
        maxHeight: 400,
    },
    buttons: {
        backgroundColor: theme.palette.primary.main,
        margin: "0 35px",
        marginTop: 50,
    },
    textFields: {
        marginTop: 10,
        display: "block",
    },
};


class LoginScreen extends Component {
    constructor(props){
        super(props);
        this.state = {
            email:'',
            password:'',
            firstName: '',
            lastName: '',
            welcome: true,
            loginState: false,
            registerState: false,
        };

        this.handleLoginState = this.handleLoginState.bind(this);
        this.handleChangeEmail = this.handleChangeEmail.bind(this);
        this.handleChangePassword = this.handleChangePassword.bind(this);
        this.handleRegisterState = this.handleRegisterState.bind(this);
        this.handleChangeFirstName = this.handleChangeFirstName.bind(this);
        this.handleChangeLastName = this.handleChangeLastName.bind(this);
    }

    handleLoginState(event){
        this.setState({
            welcome: false,
            loginState: true,
            registerState: false,
        });
    }
    handleRegisterState(event){
        this.setState({
            welcome: false,
            loginState: false,
            registerState: true,
        });
    }
    handleChangeEmail(event){
        this.setState({
            email: event.target.value,
        });
    }
    handleChangePassword(event){
        this.setState({
            password: event.target.value,
        });
    }
    handleChangeFirstName(event){
        this.setState({
            firstName: event.target.value,
        });
    }
    handleChangeLastName(event){
        this.setState({
            lastName: event.target.value,
        });
    }
    handleCancel(event){
        this.setState({
            welcome: true,
            loginState: false,
            registerState: false,
        })
    }
    handleRegister(event){
        firebase.auth().createUserWithEmailAndPassword(this.state.email, this.state.password).catch(function(error) {
            // Handle Errors here.
            var errorCode = error.code;
            var errorMessage = error.message;
            console.error(errorMessage);
        });
        //connecté
        var user = firebase.auth().currentUser;
        if(user){
            var userId = user.uid;
            this.writeUserData(userId, this.state.firstName,this.state.lastName,this.state.email);
        }

    }

    writeUserData(userId, firstName, lastName ,email) {
        firebase.database().ref('users/' + userId).set({
            email: email,
            firstName: firstName,
            lastName : lastName
        });
    }
    handleLogin(event){
        firebase.auth().signInWithEmailAndPassword(this.state.email, this.state.password).catch(function(error) {
            // Handle Errors here.
            var errorCode = error.code;
            var errorMessage = error.message;
            console.error(errorMessage);
            console.error(this.state.email);
        });

        //var database = firebase.database();
        //connecté
    }

    render() {
        let {classes} = this.props;
        let cardRender;
        if(this.state.welcome){
            cardRender = <Card className={classes.card}>
                <CardContent>
                    <Typography variant="title" color="inherit">Welcome, sign in or sign up</Typography>
                    <CardActions className={classes.actions}>
                        <Button className={classes.buttons} onClick={(event) => this.handleLoginState(event)}>Login</Button>
                        <Button className={classes.buttons} onClick={(event) => this.handleRegisterState(event)}>Register</Button>
                    </CardActions>
                </CardContent>
            </Card>;
        }else if(this.state.loginState){
            cardRender = <Card className={classes.cardLogin}>
                <CardContent>
                    <Typography variant="title" color="inherit">Sign in</Typography>
                    <TextField
                        className={classes.textFields}
                        label="Enter your email"
                        id="email"
                        onChange={(event, value) => this.handleChangeEmail(event, value)}/>
                    <TextField
                        className={classes.textFields}
                        label="Enter your password"
                        type="password"
                        id="password"
                        onChange={(event, value) => this.handleChangePassword(event, value)}/>
                    <CardActions className={classes.actions}>
                        <Button className={classes.buttons}>Login</Button>
                        <Button className={classes.buttons} onClick={(event) => this.handleCancel(event)}>Cancel</Button>
                    </CardActions>
                </CardContent>
            </Card>;
        }else if(this.state.registerState){
            cardRender = <Card className={classes.cardRegister}>
                <CardContent>
                    <Typography variant="title" color="inherit">Sign up</Typography>
                    <TextField
                        className={classes.textFields}
                        label="Enter your First Name"
                        onChange = {(event) => this.handleChangeFirstName(event)}/>
                    <TextField
                        className={classes.textFields}
                        label="Enter your Last Name"
                        onChange = {(event) => this.handleChangeLastName(event)}/>
                    <TextField
                        className={classes.textFields}
                        label="Enter your Email"
                        type="email"
                        onChange = {(event) => this.handleChangeEmail(event)}/>
                    <TextField
                        className={classes.textFields}
                        type = "password"
                        label="Enter your Password"
                        onChange = {(event) => this.handleChangePassword(event)}/>
                    <CardActions className={classes.actions}>
                        <Button className={classes.buttons} onClick={(event => this.handleRegister(event))}>Register</Button>
                        <Button className={classes.buttons} onClick={(event) => this.handleCancel(event)}>Cancel</Button>
                    </CardActions>
                </CardContent>
            </Card>;
        }
        return (
            <MuiThemeProvider theme={theme}>
                <Layout appBarStyle={this.state.appBarStyle} footer="Data for life copyright 2018" appBar={
                    <Toolbar>
                        <Typography variant="title" color="inherit">
                            Welcome
                        </Typography>
                    </Toolbar>}>
                    {cardRender}
                </Layout>
            </MuiThemeProvider>
        );
    }
}

LoginScreen = withStyles(styles)(LoginScreen);

export {LoginScreen};