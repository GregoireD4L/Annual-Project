import React from 'react';
import Select from '@material-ui/core/Select';
import { withStyles } from '@material-ui/core/styles';
import Input from '@material-ui/core/Input';
import InputLabel from '@material-ui/core/InputLabel';
import MenuItem from '@material-ui/core/MenuItem';
import FormHelperText from '@material-ui/core/FormHelperText';
import FormControl from '@material-ui/core/FormControl';
import firebase from './FirebaseConfig';
import {theme} from "./theme";

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
};

class PatientList extends React.Component {

    constructor(props){
        super(props);
        this.state = {
            names: '',
            currentPatient: {
                firstName: '',
                lastName: '',
                uid: '',
            }
        };
        this.handleSelection = this.handleSelection.bind(this);
    }

    retrievePatientsList(doctorId){
        var patients = [];
        var i = 0;
        firebase.database().ref('users/' + doctorId + '/patients').on('value', snapshot => {
            snapshot.forEach(child => {
                patients[i] = child;
                i++;
            });
        });
        return patients;
    }

    handleSelection = event => {
        var values = event.target.value.split(' ');
        this.setState({
            names: values[1]+' '+values[2],
            currentPatient: {
                firstName: values[1],
                lastName: values[2],
                uid: values[0],
            }
        });
        console.log(this.state.names);
    };

    render(){
        const { classes, doctorId } = this.props;
        var patients = this.retrievePatientsList(doctorId);
        var items = '';
        patients.forEach(patient => {
            items = <MenuItem value={patient.key + ' ' + patient.val().firstName + ' ' + patient.val().lastName}>{patient.val().firstName} {patient.val().lastName}</MenuItem>
        });
        return (<form className={classes.root} autoComplete="off">
            <FormControl className={classes.formControl}>
                <InputLabel>Patient</InputLabel>
                <Select
                    value={this.state.names}
                    onChange={this.handleSelection}>
                    {items}
                </Select>
            </FormControl>
        </form>);
    }
}

PatientList = withStyles(styles)(PatientList);
export {PatientList}