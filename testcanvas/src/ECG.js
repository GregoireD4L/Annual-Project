import React, { Component } from 'react';
import $ from 'jquery';
import CanvasJS from './CanvasJS';
import {withStyles} from '@material-ui/core';
//var CanvasJS = require('./CanvasJS')

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
            if(stopECG){
                return;
            }

            date = new Date();
            $.getJSON(linkurl + (d2.getTime()*1000000 - 3000000000) + linkurlmiddle + (date.getTime()*1000000 - 3000000000), function (data) {
                d2 = date;
                $.each(data, function (key, value) {
                    let date = new Date(parseInt(value.time));
                    dataPoints.push({x: parseInt(value.time) - start.getTime(), y: parseFloat(value.ecg1)});
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
