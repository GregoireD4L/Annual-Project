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
var stopSPO = false;
class Spo2 extends Component {

    componentDidMount() {
        stopSPO = false;
        let {idPatient} = this.props;
		let {firstName} = this.props;
		let {lastName} = this.props;
        let start = new Date();
        let date = new Date();
        let d2 = new Date();
        let milli= date.getTime()-5000;
        let dataPoints = [];
        let dpsLength = 0;
        let linkurl="http://51.38.185.205:8888/data/getSpo2PastMilli?id="+idPatient+"&beginning=";
        let linkurlmiddle="&ending=";

        let tmplink=linkurl+milli+linkurlmiddle+(milli+1000);
        let chart = new CanvasJS.Chart("chartContainer",{
            exportEnabled: true,
            title:{
                text: "SPO2 from "+firstName+" "+lastName
            },
            data: [{
                type: "spline",
                dataPoints : dataPoints,
                markerSize: 0,
            }],
            axisY: {
                includeZero: false,
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
				stopSPO=!stopSPO
				if(!stopSPO){
					updateChart();
				}
			
			},



        });


        updateChart();

        function updateChart() {
        	if(stopSPO){
        		return;
			}

            date = new Date();
            $.getJSON(linkurl + (d2.getTime()*1000000 - 3000000000) + linkurlmiddle + (date.getTime()*1000000 - 3000000000), function (data) {
                d2=date;
                $.each(data, function(key, value) {
                    let date =new Date(parseInt(value.longtime));
					if(dataPoints.length==0||(dataPoints.length>0&&parseInt(value.longtime)- start.getTime()-20>=dataPoints.slice(-1)[0].x)){
                    dataPoints.push({x: parseInt(value.longtime)- start.getTime(), y: parseFloat(value.spo2Chan1_1)});
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
        stopSPO = true;
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
Spo2 = withStyles(styles)(Spo2);

export {Spo2}