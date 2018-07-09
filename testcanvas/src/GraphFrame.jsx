import React, { Component } from 'react';
import {ECG} from "./ECG";
import {Accelero} from "./Accelero";
import {Temp} from "./Temp";
import {Respi} from "./Respi";

class GraphFrame extends Component {

    constructor(props){
        super(props);
        this.state = {
            openGraph: '',
            idPatient: '',
        }
    }

    componentDidMount(){
        let {openGraph, idPatient} = this.props;
        this.setState({
            openGraph: openGraph,
            idPatient: idPatient,
        });
        console.log("bla "+this.state.openGraph);
    }


    render(){
        if(this.state.idPatient !== ''){
            if(this.state.openGraph === 'ECG') {
                return <ECG idPatient={this.state.idPatient}/>;
            }else if(this.state.openGraph === 'ACCELERO') {
                return <Accelero idPatient={this.state.idPatient}/>;
            }else if(this.state.openGraph === 'TEMPERATURE') {
                return <Temp idPatient={this.state.idPatient}/>;
            }else if(this.state.openGraph === 'SPO2') {
                return '';
            }else if(this.state.openGraph === 'BREATHING') {
                return <Respi idPatient={this.state.idPatient}/>;
            }else{
                return '';
            }
        }else{
            return '';
        }
    }
}

export {GraphFrame}