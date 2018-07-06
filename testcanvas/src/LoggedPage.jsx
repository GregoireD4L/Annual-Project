import React, { Component } from 'react';
import {Layout} from "./Layout";
import {MuiThemeProvider} from '@material-ui/core/styles';
import {theme} from "./theme";
import {Toolbar, Typography, withStyles, IconButton, Button,
    Drawer, List, ListItem, ListItemIcon, ListItemText, Divider} from '@material-ui/core';
import MenuIcon from '@material-ui/icons/Menu';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import ChevronRightIcon from '@material-ui/icons/ChevronRight';
import {Favorite} from '@material-ui/icons';
import ECG from './ECG';
import firebase from './FirebaseConfig';

const styles = {
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
        marginLeft: '80%',
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
};

class LoggedPage extends Component{

    constructor(props){
        super(props);
        this.state = {
            firstName: '',
            lastName: '',
            openDrawer: false,
            toolbarTitle: 'Welcome',
            choiceMenu: '',
        };
        this.getUserInfos = this.getUserInfos.bind(this);
        this.handleCloseDrawer = this.handleCloseDrawer.bind(this);
        this.handleOpenDrawer = this.handleOpenDrawer.bind(this);
    }

    getUserInfos(){
        let user = firebase.auth().currentUser;
        if(user) {
            var firstname='';
            var lastname='';
            firebase.database().ref('/users/' + user.uid).on('value', function(snapshot) {
                firstname = snapshot.val().firstName;
                lastname = snapshot.val().lastName;
            });
            this.setState({
                firstName: firstname,
                lastName: lastname,
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

    handleOpenDrawer(){
        this.setState({
            openDrawer: true,
        });
    }

    render(){
        let {classes} = this.props;
        return (
            <MuiThemeProvider theme={theme}>
                <Layout footer="Data for life copyright 2018" appBar={
                    <Toolbar>
                        <IconButton className={classes.menuButton} color="inherit" aria-label="Menu" onClick={this.handleOpenDrawer}>
                            <MenuIcon />
                        </IconButton>
                        <Typography className={classes.typography} variant="title" color="inherit">
                            {this.state.toolbarTitle} {this.state.firstName} {this.state.lastName}
                        </Typography>
                        <Button className={classes.logout} color="inherit">Log out</Button>
                    </Toolbar>}>

                    <Drawer open={this.state.openDrawer} onClose={this.handleCloseDrawer}>
                        <div tabIndex={0} role="button" onClick={this.handleCloseDrawer} onKeyDown={this.handleCloseDrawer}>
                            <div className={classes.drawerHeader}>
                                <IconButton onClick={this.handleCloseDrawer}>
                                    {theme.direction === 'rtl' ? <ChevronRightIcon /> : <ChevronLeftIcon />}
                                </IconButton>
                            </div>
                            <Divider />
                            <div className={classes.list}>
                                <List>
                                    <ListItem button>
                                        <ListItemIcon>
                                            <Favorite />
                                        </ListItemIcon>
                                        <ListItemText primary="ECG" />
                                    </ListItem>
                                    <ListItem button >
                                        <ListItemIcon>
                                            <Favorite />
                                        </ListItemIcon>
                                        <ListItemText primary="Accelero" />
                                    </ListItem>
                                    <ListItem button >
                                        <ListItemIcon>
                                            <Favorite />
                                        </ListItemIcon>
                                        <ListItemText primary="Breathing" />
                                    </ListItem>
                                    <ListItem button>
                                        <ListItemIcon>
                                            <Favorite />
                                        </ListItemIcon>
                                        <ListItemText primary="SpO2" />
                                    </ListItem>
                                </List>
                            </div>
                        </div>
                    </Drawer>

                </Layout>
            </MuiThemeProvider>
        );
    }
}

LoggedPage = withStyles(styles)(LoggedPage);

export {LoggedPage}