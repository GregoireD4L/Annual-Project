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
var stopRES = false;

class Respi extends Component {

    componentDidMount() {
        stopRES = false;
        let {idPatient} = this.props;
		let {firstName} = this.props;
		let {lastName} = this.props;
        let start = new Date();
        let date = new Date();
        let d2 = new Date();
        let milli= date.getTime()-5000;
        let dataPoints = [];
        let dpsLength = 0;
        let linkurl="http://localhost:8888/data/getRespiPastMilli?id="+idPatient+"&beginning=";
        let linkurlmiddle="&ending=";

        let tmplink=linkurl+milli+linkurlmiddle+(milli+1000);
        let chart = new CanvasJS.Chart("chartContainer",{
            exportEnabled: true,
            title:{
                text: "Breathing from "+firstName+" "+lastName
            },
            data: [{
                type: "spline",
                dataPoints : dataPoints,
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

			rangeChanged: function(e){
				stopRES=!stopRES
				if(!stopRES){
					updateChart();
				}
			
			},



        });


        updateChart();

        function updateChart() {
            if(stopRES){
                return;
            }

            date = new Date();
         $.getJSON(linkurl + (d2.getTime()*1000000 - 3000000000) + linkurlmiddle + (date.getTime()*1000000 - 3000000000), function (data) {
		       d2=date;
                $.each(data, function(key, value) {
                    let date =new Date(parseInt(value.longtime));
					if(dataPoints.length==0||(dataPoints.length>0&&parseInt(value.longtime)- start.getTime()-20>=dataPoints.slice(-1)[0].x)){
                    dataPoints.push({x: parseInt(value.longtime)- start.getTime(), y: parseFloat(value.respiThorax)});
					}
                });


                while (dataPoints.length >  330 ) {
                    dataPoints.shift();
                }
                chart.render();
                milli+=1000;
                updateChart();
            });
        }
    }

    componentWillUnmount(){
        stopRES = true;
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

Respi = withStyles(styles)(Respi);
export {Respi}