import React, { Component } from 'react';
import $ from 'jquery';
import CanvasJS from './CanvasJS';
//var CanvasJS = require('./CanvasJS')
 
class App extends Component {
	
	componentDidMount() {
		let start = new Date();
		let date = new Date();
		let d2 = new Date();
	    let milli= date.getTime()-5000;
		let dataPointsX = [];
		let dataPointsY = [];
		let dataPointsZ = [];
		let dpsLength = 0;
		let linkurl="http://localhost:8888/data/getAcceleroPastMilli?id=y8F7Bd0LzJNeGMjbTrmV78NC4h33&beginning=";
		let linkurlmiddle="&ending=";
		                                 
		let tmplink=linkurl+milli+linkurlmiddle+(milli+1000);
		let chart = new CanvasJS.Chart("chartContainer",{
 		        exportEnabled: true,
			title:{
				text:"Accelero Points"
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
		
 
 
 
		});
		
		
			updateChart();
		
		function updateChart() {	
		date = new Date();
		$.getJSON(linkurl+(d2.getTime()-1500)+linkurlmiddle+(date.getTime()-1500), function(data) {
			d2=date;
			$.each(data, function(key, value) {
				let date =new Date(parseInt(value.longtime));
				dataPointsX.push({x: parseInt(value.longtime)- start.getTime(), y: parseFloat(value.acceleroX)});
				dataPointsY.push({x: parseInt(value.longtime)- start.getTime(), y: parseFloat(value.acceleroY)});
				dataPointsZ.push({x: parseInt(value.longtime)- start.getTime(), y: parseFloat(value.acceleroZ)});
				});
			
			
			while (dataPointsX.length >  330 ) {
				dataPointsX.shift();	
				dataPointsY.shift();
				dataPointsZ.shift();				
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
 
export default App;