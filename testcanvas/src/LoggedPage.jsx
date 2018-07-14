import React, { Component } from 'react';
import {Layout} from "./Layout";
import {MuiThemeProvider} from '@material-ui/core/styles';
import {theme} from "./theme";
import {Toolbar, Typography, withStyles, IconButton, Button,
    Drawer, List, ListItem, ListItemIcon, ListItemText, Divider,
    Dialog, DialogActions, DialogTitle, DialogContent, DialogContentText,
    TextField, InputLabel, MenuItem, FormControl, Select, Snackbar} from '@material-ui/core';
import CloseIcon from '@material-ui/icons/Close';
import MenuIcon from '@material-ui/icons/Menu';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import ChevronRightIcon from '@material-ui/icons/ChevronRight';
import {Redirect} from 'react-router-dom';
import {Favorite} from '@material-ui/icons';
import firebase from './FirebaseConfig';
import * as firebaseLib from "firebase";
import {ECG} from "./ECG";
import {Accelero} from "./Accelero";
import {Magneto} from "./Magneto";
import {Gyro} from "./Gyro";
import {Spo2} from "./SPO2";
import {Respi} from "./Respi";
import {Temp} from "./Temp";

const styles = {
    root: {
        display: 'flex',
        flexWrap: 'wrap',
    },
    formControl: {
        margin: theme.spacing.unit,
        minWidth: 120,
    },
    selectEmpty: {
        marginTop: theme.spacing.unit * 2,
    },
    typography: {
        color: theme.palette.primary.text,
    },
    menuButton: {
        color: theme.palette.primary.text,
        marginLeft: -12,
        marginRight: 20,
    },
    logout: {
        color: theme.palette.primary.text,
        marginRight: -12,
        marginLeft: 10,
    },
    list: {
        width: 250,
    },
    drawerHeader: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'flex-end',
        padding: '0 8px',
        ...theme.mixins.toolbar,
    },
    addPatient: {
        color: theme.palette.primary.text,
        marginLeft: '60%',
    },
    typo: {
        textAlign: "center",
    },
    textFields: {
        marginTop: 10,
        display: "block",
        width: 350,
    },
    dialogPatient: {
        minHeight: 600,
    },
    graph: {
        marginTop: 50,
        marginLeft: '30%',
    },
};

class LoggedPage extends Component{
    constructor(props){

        const config = {
            apiKey: "AIzaSyAanpOteKt6sJER051WlLtlf3oYHduwpTM",
            authDomain: "data-for-life.firebaseapp.com",
            databaseURL: "https://data-for-life.firebaseio.com",
            projectId: "data-for-life",
            storageBucket: "data-for-life.appspot.com",
            messagingSenderId: "1065890119840"
        };

       
		super(props);
        firebase.auth().onAuthStateChanged((user) => {
            if (user) {
                this.setState({user:user,});
            }
        }).bind(this);

        this.state = {
            user:firebase.auth().currentUser,
            firstNameUser: '',
            lastNameUser: '',
            email:'',
            password:'',
            firstName: '',
            lastName: '',
            openDrawer: false,
			openAlert: false,
            toolbarTitle: 'Welcome',
            choiceMenu: '',
            isLogged: true,
            openDialog: false,
            emailValidation: true,
            passwordValidation: true,
            firstNameValidation: true,
            lastNameValidation: true,
            activePatient: '',
            currentPatient: {
                firstName: '',
                lastName: '',
                uid: '',
            },
            registerSnackBar: false,
            snackBarMessage: '',
            openGraph: '',
            config : {
                apiKey: "AIzaSyAanpOteKt6sJER051WlLtlf3oYHduwpTM",
                authDomain: "data-for-life.firebaseapp.com",
                databaseURL: "https://data-for-life.firebaseio.com",
                projectId: "data-for-life",
                storageBucket: "data-for-life.appspot.com",
                messagingSenderId: "1065890119840"
            },

            secondaryApp : firebaseLib.apps.length<2?firebaseLib.initializeApp(config, "Secondary"):firebaseLib.apps[1],


        };
 

        this.getUserInfos = this.getUserInfos.bind(this);
        this.handleCloseDrawer = this.handleCloseDrawer.bind(this);
        this.handleOpenDrawer = this.handleOpenDrawer.bind(this);
        this.handleLogOut = this.handleLogOut.bind(this);
        this.handleAddPatient = this.handleAddPatient.bind(this);
        this.handleCloseDialog = this.handleCloseDialog.bind(this);
		this.handleCloseAlert = this.handleCloseAlert.bind(this);
        this.handleChangeEmail = this.handleChangeEmail.bind(this);
        this.handleChangeFirstName = this.handleChangeFirstName.bind(this);
        this.handleChangeLastName = this.handleChangeLastName.bind(this);
        this.handleChangePassword = this.handleChangePassword.bind(this);
        this.handleRegisterPatient = this.handleRegisterPatient.bind(this);
        this.handleSelection = this.handleSelection.bind(this);
        this.openECG = this.openECG.bind(this);
        this.openACCELERO = this.openACCELERO.bind(this);
        this.openMAGNETO = this.openMAGNETO.bind(this);
        this.openGYRO = this.openGYRO.bind(this);

        this.openBREATHING = this.openBREATHING.bind(this);
        this.openSPO2 = this.openSPO2.bind(this);
        this.openTEMPERATURE = this.openTEMPERATURE.bind(this);
        this.handleCloseSnackbar = this.handleCloseSnackbar.bind(this);
    }

    setCookie(cname, cvalue) {
        document.cookie = cname + "=" + cvalue + ";path=/";
    }

    getUserInfos(){
        let user = this.state.user;
        if(user) {
            firebase.database().ref('/users/' + user.uid).on('value', snapshot => {

                this.setCookie("name",snapshot.val().firstName);
                this.setCookie("lastname",snapshot.val().lastName);
            });
        }
    }

    componentDidMount(){
        this.getUserInfos();
    }

    handleCloseDrawer(){
        this.setState({
            openDrawer: false,
        });
    }
	handleCloseAlert(){
        this.setState({
            openAlert: false,
        });
    }

    handleOpenDrawer(){
        this.setState({
            openDrawer: true,
        });
    }

    handleLogOut(){
        document.cookie = "email=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
        firebase.auth().signOut();
        this.setState({
            isLogged: false,
        });
    }

    handleAddPatient(){
        this.setState({
            openDialog: true,
        });
    }

    handleCloseDialog(){
        this.setState({
            openDialog: false,
        });
    }
    handleChangeEmail(event){
        var regExpEmail = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if(regExpEmail.test(String(event.target.value).toLowerCase())){
            this.setState({
                email: event.target.value,
                emailValidation: true,
            });
        }else{
            this.setState({
                email: event.target.value,
                emailValidation: false,
            });
        }
    }
    handleChangePassword(event){
        if(event.target.value.length < 8 || event.target.value.length > 20){
            this.setState({
                password: event.target.value,
                passwordValidation: false,
            });
        }else{
            this.setState({
                password: event.target.value,
                passwordValidation: true,
            });
        }

    }
    handleChangeFirstName(event){
        if(event.target.value.length !== 0){
            this.setState({
                firstName: event.target.value,
                firstNameValidation: true,
            });
        }else{
            this.setState({
                firstName: event.target.value,
                firstNameValidation: false,
            });
        }

    }
    handleChangeLastName(event){
        if(event.target.value.length !== 0){
            this.setState({
                lastName: event.target.value,
                lastNameValidation: true,
            });
        }else{
            this.setState({
                lastName: event.target.value,
                lastNameValidation: false,
            });
        }
    }
    handleRegisterPatient(){
        var isSuccessful = true;
        const doctor = firebase.auth().currentUser;
        var doctorId = '';
        if(doctor){
            doctorId = doctor.uid;
        }
        this.state.secondaryApp.auth().createUserWithEmailAndPassword(this.state.email, this.state.password).catch(error => {
            //  firebaseLib.auth().createUserWithEmailAndPassword(this.state.email, this.state.password).catch(error => {

            // Handle Errors here.
            isSuccessful = false;
            var errorMessage = error.message;
            this.displaySnackBarWithErrors(errorMessage);
        }).then(() => {
            if(isSuccessful) {
                var user = this.state.secondaryApp.auth().currentUser;
                //var user = firebaseLib.auth().currentUser;
                if (this.state.user) {
                    this.writePatientData(doctorId, this.state.user.uid, this.state.firstName, this.state.lastName, this.state.email);
                    this.setState({
                        welcome: true,
                        loginState: false,
                        registerState: false,
                        registerSnackBar: true,
                        snackBarMessage: 'Verification Email sent ! please verify your account'
                    });
                    //email verification
                    //user.sendEmailVerification();
                    this.setState({
                        openDialog: false,
                    });
                    this.state.secondaryApp.auth().signOut();
                    //    firebaseLib.auth().signOut();
                }
            }
        });
    }
    displaySnackBarWithErrors(errorMessage){
        if(errorMessage !== ''){
            this.setState({
                registerSnackBar: true,
                snackBarMessage: errorMessage,
            });
        }
    }

    handleCloseSnackbar(){
        this.setState({
            registerSnackBar: false,
        });
    }

    writePatientData(doctorId, userId, firstName, lastName ,email) {
        firebase.database().ref('users/' + doctorId + '/patients/' + userId).set({
            email: email,
            firstName: firstName,
            lastName : lastName
        });
    }

    handleSelection = event => {
		var split = event.target.value.split(";");
        this.setState({
            activePatient: split[0],
            currentPatient: {
				uid: split[0],
				firstName: split[1],
                lastName: split[2],
            }
			
        });
    };

    retrievePatientsList(doctorId){
        var patients = [];
        var i = 0;
        firebase.database().ref('users/' + doctorId + '/patients').on('value', snapshot => {
            snapshot.forEach(child => {
                patients.push(child);
                i++;
            });
        });
        return patients;
    }

    openECG(){
		if(!this.state.currentPatient.uid){
			this.setState({
				openAlert:true,
			});
		}
        this.setState({
            openGraph: 'ECG',
            openDrawer:false,
        });
    }

    openACCELERO(){
		if(!this.state.currentPatient.uid){
		  this.setState({
			openAlert:true,
			});
		}
        this.setState({
            openGraph: 'ACCELERO',
            openDrawer: false,
        });
    }
    openGYRO(){
		if(!this.state.currentPatient.uid){
			this.setState({
			openAlert:true,
			});
		}
        this.setState({
            openGraph: 'GYRO',
            openDrawer: false,
        });
    }
    openMAGNETO(){
		if(!this.state.currentPatient.uid){
			openAlert:true,
			this.setState({
			openAlert:true,
			});
		}
        this.setState({
            openGraph: 'MAGNETO',
            openDrawer: false,
        });
    }

    openBREATHING(){
		if(!this.state.currentPatient.uid){
			this.setState({
			openAlert:true,
			});
		}
        this.setState({
            openGraph: 'BREATHING',
            openDrawer: false,
        });
    }

    openSPO2(){
		if(!this.state.currentPatient.uid){
			this.setState({
			openAlert:true,
			});
		}
        this.setState({
            openGraph: 'SPO2',
            openDrawer: false,
        });
    }

    openTEMPERATURE(){
		if(!this.state.currentPatient.uid){
			this.setState({
			openAlert:true,
			});
		}
        this.setState({
            openGraph: 'TEMPERATURE',
            openDrawer: false,
        });
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
        if(this.state.isLogged){
            let {classes} = this.props;
            const isEnabled = this.state.email.length > 0 && this.state.password.length > 0;
            if(this.state.user) {
                var patients = this.retrievePatientsList(this.state.user.uid);
                var items = [];
                patients.forEach(patient => {
					
                    items.push(<MenuItem
                      value={patient.key +";"+patient.val().firstName+";"+patient.val().lastName}>{patient.val().firstName} {patient.val().lastName}</MenuItem>);
                
                });
                var graph = '';
                if (this.state.activePatient !== '') {
                    if (this.state.openGraph === 'ECG') {
                        graph = <ECG idPatient={this.state.activePatient} firstName={this.state.currentPatient.firstName} lastName={this.state.currentPatient.lastName} />;
                    } else if (this.state.openGraph === 'ACCELERO') {
                        graph = <Accelero idPatient={this.state.activePatient} firstName={this.state.currentPatient.firstName} lastName={this.state.currentPatient.lastName} />;
                    } else if (this.state.openGraph === 'MAGNETO') {
                        graph = <Magneto idPatient={this.state.activePatient} firstName={this.state.currentPatient.firstName} lastName={this.state.currentPatient.lastName} />;
                    } else if (this.state.openGraph === 'SPO2') {
                        graph = <Spo2 idPatient={this.state.activePatient} firstName={this.state.currentPatient.firstName} lastName={this.state.currentPatient.lastName} />;
                    } else if (this.state.openGraph === 'GYRO') {
                        graph = <Gyro idPatient={this.state.activePatient} firstName={this.state.currentPatient.firstName} lastName={this.state.currentPatient.lastName} />;
                    }
                    else if (this.state.openGraph === 'TEMPERATURE') {
                        graph = <Temp idPatient={this.state.activePatient} firstName={this.state.currentPatient.firstName} lastName={this.state.currentPatient.lastName} />;
                    } else if (this.state.openGraph === 'BREATHING') {
                        graph = <Respi idPatient={this.state.activePatient}  firstName={this.state.currentPatient.firstName} lastName={this.state.currentPatient.lastName} />;

                    } else {
                        graph = '';
                    }
                } else {
					
                    graph = <Typography className={classes.graph} variant="title" color="inherit">
                        Please select a patient, then select a metric to display
                    </Typography>;
                }
            }

            return (
                <MuiThemeProvider theme={theme}>
                    <Layout footer="Data for life copyright 2018" appBar={
                        <Toolbar>
                            <IconButton className={classes.menuButton} color="inherit" aria-label="Menu"
                                        onClick={this.handleOpenDrawer}>
                                <MenuIcon/>
                            </IconButton>
                            <Typography className={classes.typography} variant="title" color="inherit">
                                {this.state.toolbarTitle} {this.getCookie("name")} {this.getCookie("lastname")}
                            </Typography>
                            <Button className={classes.addPatient} color="inherit" onClick={this.handleAddPatient}>Add patient</Button>
                            <Button className={classes.logout} color="inherit" onClick={this.handleLogOut}>Log
                                out</Button>
                        </Toolbar>}>

                        <Drawer open={this.state.openDrawer} onClose={this.handleCloseDrawer}>
                            <div tabIndex={0} role="button"
                                 onKeyDown={this.handleCloseDrawer}>
                                <div className={classes.drawerHeader}>
                                    <IconButton onClick={this.handleCloseDrawer}>
                                        {theme.direction === 'rtl' ? <ChevronRightIcon/> : <ChevronLeftIcon/>}
                                    </IconButton>
                                </div>
                                <Divider/>
                                <div className={classes.list}>
                                    <List>
                                        <ListItem>
                                            <form className={classes.root} autoComplete="off">
                                                <FormControl className={classes.formControl}>
                                                    <InputLabel>Patient</InputLabel>
                                                    <Select
                                                        onChange={this.handleSelection}
                                                        value={this.state.activePatient+";"+this.state.currentPatient.firstName+";"+this.state.currentPatient.lastName}>
                                                        {items}
                                                    </Select>
                                                </FormControl>
                                            </form>
                                        </ListItem>
                                        <ListItem button onClick={this.openECG}>
                                            <ListItemIcon>
                                                <Favorite/>
                                            </ListItemIcon>
                                            <ListItemText primary="ECG"/>
                                        </ListItem>
                                        <ListItem button onClick={this.openACCELERO}>
                                            <ListItemIcon>
                                                <Favorite/>
                                            </ListItemIcon>
                                            <ListItemText primary="Accelero"/>
                                        </ListItem>
                                        <ListItem button onClick={this.openMAGNETO}>
                                            <ListItemIcon>
                                                <Favorite/>
                                            </ListItemIcon>
                                            <ListItemText primary="Magneto"/>
                                        </ListItem>
                                        <ListItem button onClick={this.openGYRO}>
                                            <ListItemIcon>
                                                <Favorite/>
                                            </ListItemIcon>
                                            <ListItemText primary="Gyro"/>
                                        </ListItem>
                                        <ListItem button onClick={this.openBREATHING}>
                                            <ListItemIcon>
                                                <Favorite/>
                                            </ListItemIcon>
                                            <ListItemText primary="Breathing"/>
                                        </ListItem>
                                        <ListItem button onClick={this.openSPO2}>
                                            <ListItemIcon>
                                                <Favorite/>
                                            </ListItemIcon>
                                            <ListItemText primary="SpO2"/>
                                        </ListItem>
                                        <ListItem button onClick={this.openTEMPERATURE}>
                                            <ListItemIcon>
                                                <Favorite/>
                                            </ListItemIcon>
                                            <ListItemText primary="Temperature"/>
                                        </ListItem>
                                    </List>
                                </div>
                            </div>
                        </Drawer>
						

                        <Dialog
                            open={this.state.openDialog}
                            onClose={this.handleCloseDialog}
                            className={classes.dialogPatient}
                            aria-labelledby="alert-dialog-title"
                            aria-describedby="alert-dialog-description">
                            <DialogTitle id="alert-dialog-title">{"Add patient to your patients list"}</DialogTitle>
                            <DialogContent>
                                <DialogContentText id="alert-dialog-description">
                                    please provide patient informations :
                                </DialogContentText>
                                <TextField
                                    fullWidth
                                    className={classes.textFields}
                                    label="Enter your First Name"
                                    error={!this.state.firstNameValidation}
                                    helperText={(!this.state.firstNameValidation) ? "validation error" : "first name"}
                                    onChange = {(event) => this.handleChangeFirstName(event)}/>
                                <TextField
                                    fullWidth
                                    className={classes.textFields}
                                    label="Enter your Last Name"
                                    error={!this.state.lastNameValidation}
                                    helperText={(!this.state.lastNameValidation) ? "validation error" : "last name"}
                                    onChange = {(event) => this.handleChangeLastName(event)}/>
                                <TextField
                                    fullWidth
                                    className={classes.textFields}
                                    label="Enter your Email"
                                    type="email"
                                    error={!this.state.emailValidation}
                                    helperText={(!this.state.emailValidation) ? "validation error" : "email"}
                                    onChange = {(event) => this.handleChangeEmail(event)}/>
                                <TextField
                                    fullWidth
                                    className={classes.textFields}
                                    type = "password"
                                    label="Enter your Password"
                                    helperText={(!this.state.passwordValidation) ? "validation error" : "password"}
                                    error={!this.state.passwordValidation}
                                    onChange = {(event) => this.handleChangePassword(event)}/>
                            </DialogContent>
                            <DialogActions>
                                <Button onClick={this.handleCloseDialog} color="primary">
                                    Cancel
                                </Button>
                                <Button onClick={this.handleRegisterPatient} color="primary" autoFocus disabled={!isEnabled}>
                                    Register
                                </Button>
                            </DialogActions>
                        </Dialog>
						 <Dialog
                            open={this.state.openAlert}
                            onClose={this.handleCloseAlert}
                            className={classes.alertPatient}
                            aria-labelledby="alert-dialog-title"
                            aria-describedby="alert-dialog-description">
                            <DialogTitle id="alert-dialog-title">{"You had not chosen a patient"}</DialogTitle>
                            <DialogContent>
                                <DialogContentText id="alert-dialog-description">
                                    Please choose a patient
                                </DialogContentText>
                            </DialogContent>
                            <DialogActions>
                                <Button onClick={this.handleCloseAlert} color="primary">
                                    OK
                                </Button>
                                
                            </DialogActions>
                        </Dialog>

                        <Snackbar anchorOrigin={{
                            vertical: 'bottom',
                            horizontal: 'left',}} message={this.state.snackBarMessage}
                                  open={this.state.registerSnackBar} autoHideDuration={2000} action={<IconButton
                            key="close"
                            aria-label="Close"
                            color="inherit"
                            onClick={this.handleCloseSnackbar}>
                            <CloseIcon />
                        </IconButton>}/>
                        {graph}
                    </Layout>
                </MuiThemeProvider>
            );
        }else{
            return (<Redirect to='/login'/>);
        }
    }
}

LoggedPage = withStyles(styles)(LoggedPage);

export {LoggedPage}