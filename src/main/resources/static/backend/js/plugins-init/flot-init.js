(function($) {
   "use strict"


 var dzChartlist = function(){
	
	var screenWidth = $(window).width();

	var flotBar1 = function(){
		$.plot("#flotBar1", [{
			data: [[0, 3], [2, 8], [4, 5], [6, 13], [8, 5], [10, 7], [12, 4], [14, 6]]
		}], {
			series: {
				bars: {
					show: true,
					lineWidth: 0,
					fillColor: '#2258BF'
				}
			},
			grid: {
				borderWidth: 1,
				borderColor: 'transparent'
			},
			yaxis: {
				tickColor: 'transparent',
				font: {
					color: '#fff',
					size: 10
				}
			},
			xaxis: {
				tickColor: 'transparent',
				font: {
					color: '#fff',
					size: 10
				}
			}
		});
	}
	
	var flotBar2 = function(){
		$.plot("#flotBar2", [{
			data: [[0, 3], [2, 8], [4, 5], [6, 13], [8, 5], [10, 7], [12, 8], [14, 10]],
			bars: {
				show: true,
				lineWidth: 0,
				fillColor: '#2258BF'
			}
		}, {
			data: [[1, 5], [3, 7], [5, 10], [7, 7], [9, 9], [11, 5], [13, 4], [15, 6]],
			bars: {
				show: true,
				lineWidth: 0,
				fillColor: '#709fba'
			}
		}], 
		{
			grid: {
				borderWidth: 1,
				borderColor: 'transparent'
			},
			yaxis: {
				tickColor: 'transparent',
				font: {
					color: '#fff',
					size: 10
				}
			},
			xaxis: {
				tickColor: 'transparent',
				font: {
					color: '#fff',
					size: 10
				}
			}
		});
	}
	
	var flotLine1 = function(){
		var newCust = [[0, 2], [1, 3], [2, 6], [3, 5], [4, 7], [5, 8], [6, 10]];
		var retCust = [[0, 1], [1, 2], [2, 5], [3, 3], [4, 5], [5, 6], [6, 9]];

		var plot = $.plot($('#flotLine1'), [
			{
				data: newCust,
				label: 'New Customer',
				color: '#2258BF'
			},
			{
				data: retCust,
				label: 'Returning Customer',
				color: '#709fba'
			}
		],
		{
			series: {
				lines: {
					show: true,
					lineWidth: 1
				},
				shadowSize: 0
			},
			points: {
				show: false,
			},
			legend: {
				noColumns: 1,
				position: 'nw'
			},
			grid: {
				hoverable: true,
				clickable: true,
				borderColor: '#ddd',
				borderWidth: 0,
				labelMargin: 5,
				backgroundColor: 'transparent'
			},
			yaxis: {
				min: 0,
				max: 15,
				color: 'transparent',
				font: {
					size: 10,
					color: '#999'
				}
			},
			xaxis: {
				color: 'transparent',
				font: {
					size: 10,
					color: '#999'
				}
			}
		});
	}
	
	var flotLine2 = function(){
		var newCust = [[0, 2], [1, 3], [2, 6], [3, 5], [4, 7], [5, 8], [6, 10]];
		var retCust = [[0, 1], [1, 2], [2, 5], [3, 3], [4, 5], [5, 6], [6, 9]];
		
		var plot = $.plot($('#flotLine2'), [
			{
				data: newCust,
				label: 'New Customer',
				color: '#2258BF'
			},
			{
				data: retCust,
				label: 'Returning Customer',
				color: '#709fba'
			}
		],
		{
			series: {
				lines: {
					show: false
				},
				splines: {
					show: true,
					tension: 0.4,
					lineWidth: 1,
					//fill: 0.4
				},
				shadowSize: 0
			},
			points: {
				show: false,
			},
			legend: {
				noColumns: 1,
				position: 'nw'
			},
			grid: {
				hoverable: true,
				clickable: true,
				borderColor: '#ddd',
				borderWidth: 0,
				labelMargin: 5,
				backgroundColor: 'transparent'
			},
			yaxis: {
				min: 0,
				max: 15,
				color: 'transparent',
				font: {
					size: 10,
					color: '#fff'
				}
			},
			xaxis: {
				color: 'transparent',
				font: {
					size: 10,
					color: '#fff'
				}
			}
		});		
	}
	
	var flotLine3 = function(){
		var newCust2 = [[0, 10], [1, 7], [2, 8], [3, 9], [4, 6], [5, 5], [6, 7]];
		var retCust2 = [[0, 8], [1, 5], [2, 6], [3, 8], [4, 4], [5, 3], [6, 6]];
		
		var plot = $.plot($('#flotLine3'), [
			{
				data: newCust2,
				label: 'New Customer',
				color: '#2258BF'
			},
			{
				data: retCust2,
				label: 'Returning Customer',
				color: '#709fba'
			}
		],
		{
			series: {
				lines: {
					show: true,
					lineWidth: 1
				},
				shadowSize: 0
			},
			points: {
				show: true,
			},
			legend: {
				noColumns: 1,
				position: 'nw'
			},
			grid: {
				hoverable: true,
				clickable: true,
				borderColor: '#ddd',
				borderWidth: 0,
				labelMargin: 5,
				backgroundColor: 'transparent'
			},
			yaxis: {
				min: 0,
				max: 15,
				color: 'transparent',
				font: {
					size: 10,
					color: '#fff'
				}
			},
			xaxis: {
				color: 'transparent',
				font: {
					size: 10,
					color: '#fff'
				}
			}
		});
	}
	
	var flotArea1 = function(){
		var newCust = [[0, 2], [1, 3], [2, 6], [3, 5], [4, 7], [5, 8], [6, 10]];
		var retCust = [[0, 1], [1, 2], [2, 5], [3, 3], [4, 5], [5, 6], [6, 9]];
		
		var plot = $.plot($('#flotArea1'), [
			{
				data: newCust,
				label: 'New Customer',
				color: '#2258BF'
			},
			{
				data: retCust,
				label: 'Returning Customer',
				color: '#709fba'
			}
		],
		{
			series: {
				lines: {
					show: true,
					lineWidth: 0,
					fill: 1
				},
				shadowSize: 0
			},
			points: {
				show: false,
			},
			legend: {
				noColumns: 1,
				position: 'nw'
			},
			grid: {
				hoverable: true,
				clickable: true,
				borderColor: '#ddd',
				borderWidth: 0,
				labelMargin: 5,
				backgroundColor: 'transparent'
			},
			yaxis: {
				min: 0,
				max: 15,
				color: 'transparent',
				font: {
					size: 10,
					color: '#fff'
				}
			},
			xaxis: {
				color: 'transparent',
				font: {
					size: 10,
					color: '#fff'
				}
			}
		});
	}
	
	var flotArea2 = function(){
		var newCust = [[0, 2], [1, 3], [2, 6], [3, 5], [4, 7], [5, 8], [6, 10]];
		var retCust = [[0, 1], [1, 2], [2, 5], [3, 3], [4, 5], [5, 6], [6, 9]];
		
		var plot = $.plot($('#flotArea2'), [
			{
				data: newCust,
				label: 'New Customer',
				color: '#2258BF'
			},
			{
				data: retCust,
				label: 'Returning Customer',
				color: '#709fba'
			}
		],
		{
			series: {
				lines: {
					show: false
				},
				splines: {
					show: true,
					tension: 0.4,
					lineWidth: 0,
					fill: 1
				},
				shadowSize: 0
			},
			points: {
				show: false,
			},
			legend: {
				noColumns: 1,
				position: 'nw'
			},
			grid: {
				hoverable: true,
				clickable: true,
				borderColor: '#ddd',
				borderWidth: 0,
				labelMargin: 5,
				backgroundColor: 'transparent'
			},
			yaxis: {
				min: 0,
				max: 15,
				color: 'transparent',
				font: {
					size: 10,
					color: '#fff'
				}
			},
			xaxis: {
				color: 'transparent',
				font: {
					size: 10,
					color: '#fff'
				}
			}
		});
	}
	
	var flotLine4 = function(){
		var previousPoint = null;

		$('#flotLine4, #flotLine4').bind('plothover', function (event, pos, item) {
			$('#x').text(pos.x.toFixed(2));
			$('#y').text(pos.y.toFixed(2));

			if (item) {
				if (previousPoint != item.dataIndex) {
					previousPoint = item.dataIndex;

					$('#tooltip').remove();
					var x = item.datapoint[0].toFixed(2),
						y = item.datapoint[1].toFixed(2);

					showTooltip(item.pageX, item.pageY, item.series.label + ' of ' + x + ' = ' + y);
				}
			} else {

				$('#tooltip').remove();
				previousPoint = null;
			}
		});
		$('#flotLine4, #flotLine4').bind('plotclick', function (event, pos, item) {
			if (item) {
				plot.highlight(item.series, item.datapoint);
			}
		});
	}

	function showTooltip(x, y, contents) {
		$('<div id="tooltip" class="tooltipflot">' + contents + '</div>').css({
			position: 'absolute',
			display: 'none',
			top: y + 5,
			left: x + 5
		}).appendTo('body').fadeIn(200);
	}

	var flotRealtime1 = function(){
		/*********** REAL TIME UPDATES **************/

		var data = [], totalPoints = 50;

		function getRandomData() {
			if (data.length > 0)
				data = data.slice(1);
			while (data.length < totalPoints) {
				var prev = data.length > 0 ? data[data.length - 1] : 50,
					y = prev + Math.random() * 10 - 5;
				if (y < 0) {
					y = 0;
				} else if (y > 100) {
					y = 100;
				}
				data.push(y);
			}
			var res = [];
			for (var i = 0; i < data.length; ++i) {
				res.push([i, data[i]])
			}
			return res;
		}


		// Set up the control widget
		var updateInterval = 1000;

		var plot4 = $.plot('#flotRealtime1', [getRandomData()], {
			colors: ['#2258BF'],
			series: {
				lines: {
					show: true,
					lineWidth: 1
				},
				shadowSize: 0	// Drawing is faster without shadows
			},
			grid: {
				borderColor: 'transparent',
				borderWidth: 1,
				labelMargin: 5
			},
			xaxis: {
				color: 'transparent',
				font: {
					size: 10,
					color: '#fff'
				}
			},
			yaxis: {
				min: 0,
				max: 100,
				color: 'transparent',
				font: {
					size: 10,
					color: '#fff'
				}
			}
		});
		update_plot4();
		function update_plot4() {
			plot4.setData([getRandomData()]);
			plot4.draw();
			setTimeout(update_plot4, updateInterval);
		}
	}
	
	var flotRealtime2 = function(){
		var data = [], totalPoints = 50;

		function getRandomData() {
			if (data.length > 0)
				data = data.slice(1);
			while (data.length < totalPoints) {
				var prev = data.length > 0 ? data[data.length - 1] : 50,
					y = prev + Math.random() * 10 - 5;
				if (y < 0) {
					y = 0;
				} else if (y > 100) {
					y = 100;
				}
				data.push(y);
			}
			var res = [];
			for (var i = 0; i < data.length; ++i) {
				res.push([i, data[i]])
			}
			return res;
		}
		
		// Set up the control widget
		var updateInterval = 1000;
		
		var plot5 = $.plot('#flotRealtime2', [getRandomData()], {
			colors: ['#2258BF'],
			series: {
				lines: {
					show: true,
					lineWidth: 0,
					fill: 0.9
				},
				shadowSize: 0	// Drawing is faster without shadows
			},
			grid: {
				borderColor: 'transparent',
				borderWidth: 1,
				labelMargin: 5
			},
			xaxis: {
				color: 'transparent',
				font: {
					size: 10,
					color: '#fff'
				}
			},
			yaxis: {
				min: 0,
				max: 100,
				color: 'transparent',
				font: {
					size: 10,
					color: '#fff'
				}
			}
		});

		

		
		update_plot5();
		function update_plot5() {
			plot5.setData([getRandomData()]);
			plot5.draw();
			setTimeout(update_plot5, updateInterval);
		}
	}
	
	/* Function ============ */
	return {
		init:function(){
		},
		
		
		load:function(){
			flotBar1();	
			flotBar2();
			flotLine1();	
			flotLine2();	
			flotLine3();		
			flotArea1();
			flotArea2();
			flotLine4();
			flotRealtime1();
			flotRealtime2();
		},
		
		resize:function(){
		}
	}

}();

jQuery(document).ready(function(){
});
	
jQuery(window).on('load',function(){
	dzChartlist.load();
});

jQuery(window).on('resize',function(){
	dzChartlist.resize();
});     

})(jQuery);