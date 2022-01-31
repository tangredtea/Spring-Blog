

(function($) {
    /* "use strict" */
	
 var dzChartlist = function(){
	
	var screenWidth = $(window).width();	
		var peityLine = function(){
		$(".peity-line").peity("line", {
			fill: ["rgba(34, 88, 191, .0)"], 
			stroke: '#2258BF', 
			strokeWidth: '4', 
			width: "280",
			height: "50"
		});
	}
	
 
	/* Function ============ */
		return {
			init:function(){
			},
			
			
			load:function(){
				peityLine();			
				
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