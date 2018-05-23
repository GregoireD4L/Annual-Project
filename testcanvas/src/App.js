import React, { Component } from 'react';
import $ from 'jquery';
import CanvasJS from './CanvasJS';
//var CanvasJS = require('./CanvasJS')
 
class App extends Component {
	componentDidMount() {
		let dataPoints = [];
		let dpsLength = 38;
		let linkurl="http://localhost:8888/data/getECG1PastMilli?id=1&beginning=";
		let linkurlmiddle="&ending="
		let beginning=1526287105000
		let tmplink=linkurl+beginning+linkurlmiddle+(beginning+1000);
		let chart = new CanvasJS.Chart("chartContainer",{
 		        exportEnabled: true,
			title:{
				text:"ECG1 Points"
			},
			data: [{
				type: "spline",
				dataPoints : dataPoints,
			}]
		});
		
		$.getJSON(linkurl+beginning+linkurlmiddle+(beginning+1000), function(data) {  
			$.each(data, function(key, value){
				
				dataPoints.push({x: new Date(parseInt(value.longtime)), y: parseFloat(value.p1)});
				
			});
			dpsLength++;
			chart.render();
			updateChart();
		});
		function updateChart() {	
		$.getJSON(linkurl+beginning+linkurlmiddle+(beginning+1000), function(data) {
			$.each(data, function(key, value) {
				dataPoints.push({x: new Date(parseInt(value.longtime)), y: parseFloat(value.p1)});
				});
				 beginning+=1000;
			
			
			while (dataPoints.length >  100 ) {
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