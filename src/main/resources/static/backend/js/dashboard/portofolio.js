

(function($) {
    /* "use strict" */
	
 var dzChartlist = function(){
	
	var screenWidth = $(window).width();	
		 var WeeklysummaryChart = function(){
			 var options = {
			  series: [{
			  name: 'PRODUCT A',
			  data: [44, 55, 41, 67, 22, 43, 21]
			}, {
			  name: 'PRODUCT B',
			  data: [13, 23, 20, 8, 13, 27, 33]
			}, {
			  name: 'PRODUCT C',
			  data: [11, 17, 15, 15, 21, 14, 15]
			}],
			  chart: {
			  type: 'bar',
			  height: 150,
			  stacked: true,
			  stackType: '100%',
			  toolbar:{
				  show:false,
			  },
			},
			plotOptions:{
				bar:{
					columnWidth: '25%',
					endingShape: "rounded",
					startingShape: "rounded",
					borderRadius: 4,
					colors:{
						 //backgroundBarColors:['#13b440','#ff9574','#c4c4c4'],
						 backgroundBarOpacity: 1,
						  backgroundBarRadius: 5,
					},
					
				}, 
			},
			grid:{
				  show: false,
			},
			colors:['#13b440','#ff9574','#c4c4c4'],
			dataLabels:{
				 enabled: false,
			},
			responsive: [{
			  breakpoint: 480,
			  options: {
				legend: {
				  position: 'bottom',
				  offsetX: -10,
				  offsetY: 0
				}
			  }
			}],
			xaxis: {
			  categories: ['SUN', 'MON', 'TUE', 'WED', 'THU', 'FRI','SAT'],
			   axisBorder: {
				   show: false,
			   },
			    axisTicks:{
					  show: false,
				},
			},
			yaxis:{
				 show: false,
			},
			fill: {
			  opacity: 1
			},
			legend: {
			  position: 'right',
			  offsetX: 0,
			  offsetY: 50,
			  show:false
			},
			};

        var chart = new ApexCharts(document.querySelector("#WeeklysummaryChart"), options);
        chart.render();
	 }	
	 var CurrentGraph = function(){
		 var options = {
          series: [{
          name: 'Buy',
          data: [44, 55, 57, 56, 61]
        }, {
          name: 'Sell',
          data: [76, 85, 101, 98, 87]
        }],
          chart: {
          type: 'bar',
          height: 350,
		  toolbar: {
					show: false
				},
        },
		grid: {	
			show: false
		},
        plotOptions: {
          bar: {
            horizontal: false,
            columnWidth: '55%',
            endingShape: 'rounded',
			startingShape: "rounded",
			borderRadius: 7,
          },
        },
        dataLabels: {
          enabled: false
        },
        stroke: {
          show: true,
          width: 0,
          colors: ['transparent'],
		  lineCap: 'smooth',
        },
        xaxis: {
          categories: ['Feb', 'Mar', 'Apr', 'May', 'Jun'],
		   labels: {
			show: false,
		   },
			 axisBorder:{
				   show: false,	
			 },
			 axisTicks: {
				show: false,
			},
        },
        yaxis: {
			show: false	
        },
		legend:{
			itemMargin: {
			  horizontal: 15,
			  vertical: 0
			},
			 markers:{
				  radius: 12,
			 },
		},
        fill: {
          opacity: 1
        },
		colors: ['#2258BF', '#FAC438'],
        tooltip: {
          y: {
            formatter: function (val) {
              return "$ " + val + " thousands"
            }
          }
        }
        };

        var chart = new ApexCharts(document.querySelector("#CurrentGraph"), options);
        chart.render();
	}
	var pieChart = function(){
		 var options = {
          series: [34, 12, 41, 22],
          chart: {
          type: 'donut',
		  height:250
        },
		dataLabels: {
          enabled: false
        },
		stroke: {
          width: 0,
        },
		colors:['#374C98', '#FFAB2D', '#FF782C', '#00ADA3'],
		legend: {
              position: 'bottom',
			  show:false
            },
        responsive: [{
          breakpoint: 768,
          options: {
           chart: {
			  height:200
			},
          }
        }]
        };

        var chart = new ApexCharts(document.querySelector("#pieChart"), options);
        chart.render();
    
	}
	
	/* Function ============ */
		return {
			init:function(){
			},
			
			
			load:function(){
				WeeklysummaryChart();
				CurrentGraph();
				pieChart();
				
			},
			
			resize:function(){
			}
		}
	
	}();

	
		
	jQuery(window).on('load',function(){
		setTimeout(function(){
			dzChartlist.load();
		}, 1000); 
		
	});

     

})(jQuery);