import React, { Component } from 'react';
import $ from 'jquery';
import CanvasJS from './CanvasJS';
//var CanvasJS = require('./CanvasJS')
class App extends Component {
	
	componentDidMount() {
		let dataPoints = [];
		let dpsLength = 0;
		let date = new Date();
		let d2 = new Date();
		let par=0;
		let milli= date.getTime()-1000;
		let linkurl="http://localhost:8888/data/getECG1?id=8866skUXvbbhSJZo1qctm9o6Kej1";
		                              
		let tmplink=linkurl;
		let chart = new CanvasJS.Chart("chartContainer",{
 		        exportEnabled: true,
			title:{
				text:"ECG1 Points"
			},
			data: [{
				type: "spline",
				dataPoints : dataPoints,
			}],
			axisY: {
				includeZero: false,
				interval : 0.1,
			},
				legend: {
        horizontalAlign: "right",
        verticalAlign: "center"
      },
			axisX: {
				//valueFormatString: "ss:ff",
				//interval:0.5,
				labelFormatter: function ( e ) {
					return "";  
				} , 
				includeZero: false,
			//	intervalType: "month",
			},
		
		zoomEnabled: true,
		
 
 
 
		});
		
		$.getJSON(linkurl+"&time=1000", function(data) {  
			$.each(data, function(key, value){
				let date =new Date(parseInt(value.longtime));
				dataPoints.push({x:  parseInt(value.longtime), y: parseFloat(value.ecg1)});
				
			});
			dpsLength++;
			chart.render();
			updateChart();
			
		});
		function updateChart() {	
		date=new Date();
		$.getJSON(linkurl+"&time="+(date.getTime()-d2.getTime()), function(data) {
			
			d2 = date;
			$.each(data, function(key, value) {
				let date =new Date(parseInt(value.longtime));
				dataPoints.push({x: parseInt(value.longtime), y: parseFloat(value.ecg1)});
				});
			
			
			while (dataPoints.length >  2500 ) {
				dataPoints.shift();				
			}
			chart.render();
			setTimeout(function(){updateChart()}, 100);
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