import React, { Component } from 'react';
import $ from 'jquery';
import CanvasJS from './CanvasJS';
import {withStyles} from '@material-ui/core';
//var CanvasJS = require('./CanvasJS')
/***
 *  Created by Nicolas Sirac
 ****/
const styles = {
    graph: {
        marginTop: 50,
    },
};
var stopECG = false;
class ECG extends Component {

    constructor(props){
        super(props);
    }
    componentDidMount() {
        stopECG = false;
        let {idPatient} = this.props;
		let {firstName} = this.props;
		let {lastName} = this.props;
        let start = new Date();
        let date = new Date();
        let d2 = new Date();
        let dataPoints = [];
        let linkurl = "http://51.38.185.205:8888/data/getECG1PastMilli?id=" + idPatient + "&beginning=";
        let linkurlmiddle = "&ending=";

        let chart = new CanvasJS.Chart("chartContainer", {
            exportEnabled: true,
            title: {
                text: "ECG from "+firstName+" "+lastName
            },
            data: [{
                type: "spline",
                dataPoints: dataPoints,
                markerSize: 0,
            }],
            axisY: {
                includeZero: false,
            },
            legend: {
                horizontalAlign: "right",
                verticalAlign: "center"
            },
            axisX: {
                includeZero: false,
            },

            zoomEnabled: true,

			rangeChanged: function(e){
				if(e.trigger=="reset"){
					stopECG=false;
					updateChart();
				}
				else{
					stopECG=true;
				}
				
			},

        });


        updateChart();


        function updateChart() {
            if(stopECG){
                return;
            }

            date = new Date();
          $.getJSON(linkurl + (d2.getTime()*1000000 - 3000000000) + linkurlmiddle + (date.getTime()*1000000 - 3000000000), function (data) {
		       d2 = date;
                $.each(data, function (key, value) {
                    let date = new Date(parseInt(value.longtime));
					
                    dataPoints.push({x: parseInt(value.longtime) - start.getTime()+3000, y: parseFloat(value.ecg1)});
                });


                while (dataPoints.length > 3300) {
                    dataPoints.shift();
                }
                chart.render();
                updateChart();
            });

        }
    }

    componentWillUnmount(){
        stopECG = true;
    }

    render() {
        let {classes} = this.props;
        return (
            <div className="App">
                <div id="chartContainer" className={classes.graph}></div>
            </div>
        );
    }
}

ECG = withStyles(styles)(ECG);
export {ECG};
