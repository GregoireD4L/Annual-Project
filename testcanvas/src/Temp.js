import React, { Component } from 'react';
import $ from 'jquery';
import CanvasJS from './CanvasJS';
//var CanvasJS = require('./CanvasJS')

class Temp extends Component {
    componentDidMount() {

        let {idPatient, openGraph} = this.props;
        let start = new Date();
        let date = new Date();
        let d2 = new Date();
        let milli= date.getTime()-5000;
        let dataPoints = [];
        let dpsLength = 0;
        let linkurl="http://localhost:8888/data/getTempPastMilli?id="+idPatient+"&beginning=";
        let linkurlmiddle="&ending=";

        let tmplink=linkurl+milli+linkurlmiddle+(milli+1000);
        let chart = new CanvasJS.Chart("chartContainer",{
            exportEnabled: true,
            title:{
                text:"Temp Points"
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




        });


        updateChart();

        function updateChart() {
            if(openGraph !== 'TEMPERATURE'){
                return;
            }
            date = new Date();
            $.getJSON(linkurl+(d2.getTime()-1500)+linkurlmiddle+(date.getTime()-1500), function(data) {
                d2=date;
                $.each(data, function(key, value) {
                    let date =new Date(parseInt(value.longtime));
                    dataPoints.push({x: parseInt(value.longtime)- start.getTime(), y: parseFloat(value.temp)});
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
    render() {
        return (
            <div className="App">
                <div id="chartContainer"></div>
            </div>
        );
    }
}

export {Temp}