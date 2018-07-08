import React, { Component } from 'react';
import $ from 'jquery';
import CanvasJS from './CanvasJS';
import {Typography, withStyles} from '@material-ui/core';
import {theme} from "./theme";
//var CanvasJS = require('./CanvasJS')

const styles = {
    typography: {
        marginTop: 50,
        color: theme.palette.primary.text2,
        textAlign: "center",
    },
};

class ECG extends Component {
	componentDidMount() {
		let{idPatient, openECG} = this.props;

		if(idPatient !== '') {


            var id = getQueryVariable("id");

            function getQueryVariable(variable) {
                var query = window.location.search.substring(1);
                var vars = query.split("&");
                for (var i = 0; i < vars.length; i++) {
                    var pair = vars[i].split("=");
                    if (pair[0] == variable) {
                        return pair[1];
                    }
                }
            }

            var id = getQueryVariable("id");
            let start = new Date();
            let date = new Date();
            let d2 = new Date();
            let milli = date.getTime() - 5000;
            let dataPoints = [];
            let dpsLength = 0;
            let linkurl = "http://localhost:8888/data/getECG1PastMilli?id=" + idPatient + "&beginning=";
            let linkurlmiddle = "&ending=";

            let tmplink = linkurl + milli + linkurlmiddle + (milli + 1000);
            let chart = new CanvasJS.Chart("chartContainer", {
                exportEnabled: true,
                title: {
                    text: "ECG1 Points"
                },
                data: [{
                    type: "spline",
                    dataPoints: dataPoints,
                    markerSize: 0,
                }],
                axisY: {
                    includeZero: false,
                    interval: 0.1,
                },
                legend: {
                    horizontalAlign: "right",
                    verticalAlign: "center"
                },
                axisX: {
                    //valueFormatString: "ss:ff",
                    //interval:0.5,
                    /*labelFormatter: function ( e ) {
                        return "";
                    } , */
                    includeZero: false,
                    //	intervalType: "month",
                },

                zoomEnabled: true,


            });


            updateChart();


            function updateChart() {
                date = new Date();
                $.getJSON(linkurl + (d2.getTime() - 1500) + linkurlmiddle + (date.getTime() - 1500), function (data) {
                    d2 = date;
                    $.each(data, function (key, value) {
                        let date = new Date(parseInt(value.longtime));
                        dataPoints.push({x: parseInt(value.longtime) - start.getTime(), y: parseFloat(value.ecg1)});
                    });


                    while (dataPoints.length > 3300) {
                        dataPoints.shift();
                    }
                    chart.render();
                    milli += 1000;
                    updateChart();
                });
            }
        }
}
  render() {
        let {idPatient, openECG, classes} = this.props;
	    if(openECG){
	        if(idPatient !== ''){
                return (
                    <div className="App">
                        <div id="chartContainer"></div>
                    </div>
                );
            }else{
                return (<Typography variant="title" color="inherit" className={classes.typography}>Please select a patient in the patients list</Typography>);
            }
        }else{
	        return '';
        }
  }
}

ECG = withStyles(styles)(ECG);
export {ECG};