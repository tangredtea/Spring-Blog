

(function($) {
    /* "use strict" */
	
 var dzChartlist = function(){
	
	var screenWidth = $(window).width();	
	var currentChart = function(){
		 var options = {
		  series: [85, 60, 67, 50],
		  chart: {
		  height: 350,
		  type: 'radialBar',
		},
		plotOptions: {
		  radialBar: {
				startAngle:-90,
			   endAngle: 90,
			dataLabels: {
			  name: {
				fontSize: '22px',
			  },
			  value: {
				fontSize: '16px',
			  },
			}
		  },
		},
		stroke:{
			 lineCap: 'round',
		},
		labels: ['Income', 'Income', 'Imcome', 'Income'],
		 colors:['#FFAF65', '#4441DE','#60C695','#F34F80'],
		};

		var chart = new ApexCharts(document.querySelector("#currentChart"), options);
		chart.render();
	}
	
	var marketChart = function(){
		 var options = {
          series: [{
          name: 'series1',
          data: [200, 200, 200, 450, 300, 400, 300,400, 500, 300]
        }, {
          name: 'series2',
          data: [400, 300, 450, 350, 700, 200, 800, 800, 700, 750]
        }],
          chart: {
          height: 350,
          type: 'line',
		  toolbar:{
			  show:false
		  }
        },
		colors:["#2258BF","#FF7213"],
        dataLabels: {
          enabled: false
        },
        stroke: {
          curve: 'smooth',
		   width: 10,
        },
		legend:{
			show:false
		},
		grid:{
			borderColor: '#AFAFAF',
			strokeDashArray: 10,
		},
		yaxis: {
		  labels: {
			style: {
				colors: '#787878',
				fontSize: '13px',
				fontFamily: 'Poppins',
				fontWeight: 400
				
			},
			formatter: function (value) {
			  return value + "k";
			}
		  },
		},
        xaxis: {
          categories: ["Week 01","Week 02","Week 03","Week 04","Week 05","Week 06","Week 07","Week 08","Week 09","Week 10"],
		  labels:{
			  style: {
				colors: '#787878',
				fontSize: '13px',
				fontFamily: 'Poppins',
				fontWeight: 400
				
			},
		  },
		  axisBorder:{
			show:false,  
		  },
		  axisTicks:{
			  show: false,
		},
		  
        },
        tooltip: {
          x: {
            format: 'dd/MM/yy HH:mm'
          },
        },
        };

        var chart = new ApexCharts(document.querySelector("#marketChart"), options);
        chart.render();
	}
	var recentContact = function(){
		jQuery('.card-slide').owlCarousel({
			loop:false,
			margin:30,
			nav:true,
            rtl:(getUrlParams('dir') == 'rtl')?true:false,
			autoWidth:true,
            //rtl:true,
			dots: false,
			navText: ['', ''],
		});	
	}
	var carouselReview = function(){
		jQuery('.testimonial-two').owlCarousel({
			loop:true,
			autoplay:true,
			margin:10,
			nav:false,
			stagePadding: 20,
			rtl:false,
			dots: false,
			navText: ['', ''],
			responsive:{
				0:{
					items:2
				},
				450:{
					items:3
				},
				600:{
					items:3
				},	
				991:{
					items:4
				},			
				
				1200:{
					items:5
				},
				1600:{
					items:4
				},
			}
		})
	}
	
	/* Function ============ */
		return {
			init:function(){
			},
			
			
			load:function(){
				currentChart();
				marketChart();
				recentContact();
				carouselReview();
				
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