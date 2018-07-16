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
var stopMag = false;
class Magneto extends Component {

    componentDidMount() {
        stopMag = false;
        let {idPatient} = this.props;
		let {firstName} = this.props;
		let {lastName} = this.props;
        let start = new Date();
        let date = new Date();
        let d2 = new Date();
        let dataPointsX = [];
        let dataPointsY = [];
        let dataPointsZ = [];
        let linkurl="http://51.38.185.205:8888/data/getMagnetoPastMilli?id="+idPatient+"&beginning=";
        let linkurlmiddle="&ending=";

        let chart = new CanvasJS.Chart("chartContainer",{
            exportEnabled: true,
            title:{
                text: "Magneto from "+firstName+" "+lastName
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
                includeZero: false,
            },

            zoomEnabled: true,


			rangeChanged: function(e){
				if(e.trigger=="reset"){
					stopMag=false;
					updateChart();
				}
				else{
					stopMag=true;
				}
				
			},


        });


        updateChart();

        function updateChart() {
            if(stopMag){
                return;
            }
            date = new Date();
            $.getJSON(linkurl + (d2.getTime()*1000000 - 3000000000) + linkurlmiddle + (date.getTime()*1000000 - 3000000000), function (data) {
                d2=date;
                $.each(data, function(key, value) {
                    let date =new Date(parseInt(value.longtime));
					if(dataPointsX.length==0||(dataPointsX.length>0&&parseInt(value.longtime)- start.getTime()-20+3000>=dataPointsX.slice(-1)[0].x)){
                    dataPointsX.push({x: parseInt(value.longtime)- start.getTime()+3000, y: parseFloat(value.magnetoX)});
                    dataPointsY.push({x: parseInt(value.longtime)- start.getTime()+3000, y: parseFloat(value.magnetoY)});
                    dataPointsZ.push({x: parseInt(value.longtime)- start.getTime()+3000, y: parseFloat(value.magnetoZ)});
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
        stopMag = true;
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
Magneto = withStyles(styles)(Magneto);

export {Magneto}