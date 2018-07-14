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
var stopGyro = false;
class Gyro extends Component {

    constructor(props){
        super(props);
    }

    componentDidMount() {
        stopGyro = false;
        let {idPatient} = this.props;
		let {firstName} = this.props;
		let {lastName} = this.props;
        let start = new Date();
        let date = new Date();
        let d2 = new Date();
        let dataPointsX = [];
        let dataPointsY = [];
        let dataPointsZ = [];
        let dpsLength = 0;
        let linkurl="http://51.38.185.205:8888/data/getGyroPastMilli?id="+idPatient+"&beginning=";
        let linkurlmiddle="&ending=";

        let chart = new CanvasJS.Chart("chartContainer",{
            exportEnabled: true,
            title:{
                text: "Gyro from "+firstName+" "+lastName
            },
            data: [
                {
                    type: "spline",
                    dataPoints : dataPointsY,
                    markerSize: 0,
                },
                {
                    type: "spline",
                    dataPoints : dataPointsZ,
                    markerSize: 0,
                },
                {
                    type: "spline",
                    dataPoints : dataPointsX,
                    markerSize: 0,
                },
            ],
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


			rangeChanged: function(e){
				stopGyro=!stopGyro
				if(!stopGyro){
					updateChart();
				}
			
			},


        });


        updateChart();

        function updateChart() {
            if(stopGyro){
                return;
            }
            date = new Date();
            $.getJSON(linkurl + (d2.getTime()*1000000 - 3000000000) + linkurlmiddle + (date.getTime()*1000000 - 3000000000), function (data) {
                d2=date;
                $.each(data, function(key, value) {
                    let date =new Date(parseInt(value.longtime));
					if(dataPointsX.length==0||(dataPointsX.length>0&&parseInt(value.longtime)- start.getTime()-20>=dataPointsX.slice(-1)[0].x)){
                    dataPointsX.push({x: parseInt(value.longtime)- start.getTime(), y: parseFloat(value.gyroX)});
                    dataPointsY.push({x: parseInt(value.longtime)- start.getTime(), y: parseFloat(value.gyroY)});
                    dataPointsZ.push({x: parseInt(value.longtime)- start.getTime(), y: parseFloat(value.gyroZ)});
					}
                });


                while (dataPointsX.length >  330 ) {
                    dataPointsX.shift();
                    dataPointsY.shift();
                    dataPointsZ.shift();
                }
                chart.render();
                updateChart();
            });
        }
    }

    componentWillUnmount(){
        stopGyro = true;
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
Gyro = withStyles(styles)(Gyro);

export {Gyro}