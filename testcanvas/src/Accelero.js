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
var stopACC = false;
class Accelero extends Component {

    componentDidMount() {
        stopACC = false;
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
        let linkurl="http://localhost:8888/data/getAcceleroPastMilli?id="+idPatient+"&beginning=";
        let linkurlmiddle="&ending=";

        let chart = new CanvasJS.Chart("chartContainer",{
            exportEnabled: true,
            title:{
               text: "Accelero from "+firstName+" "+lastName
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
                interval : 0.2,
                /*maximum: 0.2,
                minimum: -0.2,*/
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
				stopACC=!stopACC
				if(!stopACC){
					updateChart();
				}
			
			},


        });


        updateChart();

        function updateChart() {
            if(stopACC){
                return;
            }

            date = new Date();
     $.getJSON(linkurl + (d2.getTime()*1000000 - 3000000000) + linkurlmiddle + (date.getTime()*1000000 - 3000000000), function (data) {
		          d2=date;
                $.each(data, function(key, value) {
                    let date =new Date(parseInt(value.longtime));
					if(dataPointsX.length==0||(dataPointsX.length>0&&parseInt(value.longtime)- start.getTime()-20>=dataPointsX.slice(-1)[0].x)){
                    dataPointsX.push({x: parseInt(value.longtime)- start.getTime(), y: parseFloat(value.acceleroX)});
                    dataPointsY.push({x: parseInt(value.longtime)- start.getTime(), y: parseFloat(value.acceleroY)});
                    dataPointsZ.push({x: parseInt(value.longtime)- start.getTime(), y: parseFloat(value.acceleroZ)});
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
        stopACC = true;
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
Accelero = withStyles(styles)(Accelero);

export {Accelero}