import React, { Component } from 'react';
import $ from 'jquery';
import CanvasJS from './CanvasJS';
//var CanvasJS = require('./CanvasJS')
 
class App extends Component {
	componentDidMount() {
		let dataPoints = [];
		let dpsLength = 38;
		let linkurl="http://localhost:8888/data/getECG1PastMilli?id=1&beginning=";
		let linkurlmiddle="&ending=";
		let beginning=1527590793043 ;
		let tmplink=linkurl+beginning+linkurlmiddle+(beginning+1000);
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
				includeZero: false,
			//	intervalType: "month",
			},
		
		zoomEnabled: true,
		
 
 
 
		});
		
		$.getJSON(linkurl+beginning+linkurlmiddle+(beginning+1000), function(data) {  
			$.each(data, function(key, value){
				let date =new Date(parseInt(value.longtime));
				dataPoints.push({x:  date.getSeconds()/*+":"+date.getMilliseconds()*/, y: parseFloat(value.ecg1)});
				
			});
			dpsLength++;
			chart.render();
			//updateChart();
		});
	/*	function updateChart() {	
		$.getJSON(linkurl+beginning+linkurlmiddle+(beginning+1000), function(data) {
			$.each(data, function(key, value) {
				let date =new Date(parseInt(value.longtime));
				dataPoints.push({x: date.getSeconds()/*+":"+date.getMilliseconds()*//*, y: parseFloat(value.ecg1)});
				});
				 beginning+=1000;
			
			
			while (dataPoints.length >  500 ) {
				dataPoints.shift();				
			}
			chart.render();
			setTimeout(function(){updateChart()}, 1000);
		});
	}*/
}
  render() {
    return (
      <div className="App">
        <div
		id="chartContainer"></div>
      </div>
    );
  }
}
 
export default App;