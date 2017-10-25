
(function($){
  	
	$.fn.portamento = function(options) {
		
		var thisWindow = $(window);
		var thisDocument = $(document);
						
	  	$.fn.viewportOffset = function() {
			var win = $(window);
			var offset = $(this).offset();
	  
			return {
	    		left: offset.left - win.scrollLeft(),
	      		top: offset.top - win.scrollTop()
	    	};
	  	};
		
		function positionFixedSupported () {
			var container = document.body;
	  		if (document.createElement && container && container.appendChild && container.removeChild) {
	      		var el = document.createElement("div");
		  		if (!el.getBoundingClientRect) {
		      		return null;
		  		}
		  		el.innerHTML = "x";
		  		el.style.cssText = "position:fixed;top:0px;";
		  		container.appendChild(el);
		  		var originalHeight = container.style.height, originalScrollTop = container.scrollTop;
		 		container.style.height = "3000px";
		      	container.scrollTop = 500;
		      	var elementTop = el.getBoundingClientRect().top;
		      	container.style.height = originalHeight;
		      	var isSupported = elementTop === 0;
		      	container.removeChild(el);
		      	container.scrollTop = originalScrollTop;
		      	return isSupported;
	  		}
	  		return null;
		}

		
		// ---------------------------------------------------------------------------------------------------
			    
		// get the definitive options
		var opts = $.extend({}, $.fn.portamento.defaults, options);
		
		// setup the vars accordingly
		var panel = this;
		var wrapper = opts.wrapper;
		var gap = opts.gap;
		var disableWorkaround = opts.disableWorkaround;		
		var fullyCapableBrowser = positionFixedSupported();
		
		if(panel.length != 1) {
			// die gracefully if the user has tried to pass multiple elements 
			// (multiple element support is on the TODO list!) or no elements...
			return this;
		}
		
		if(!fullyCapableBrowser && disableWorkaround) {
			// just stop here, as the dev doesn't want to use the workaround
			return this;
		}
		
		// wrap the floating panel in a div, then set a sensible min-height and width
		panel.wrap('<div id="portamento_container" />');
		var float_container = $('#portamento_container');
		float_container.css({
			'min-height': panel.outerHeight(),
			'width': panel.outerWidth()
		});
		
		// calculate the upper scrolling boundary
		var panelOffset = panel.offset().top;
		var panelMargin = parseFloat(panel.css('marginTop').replace(/auto/, 0));
		var realPanelOffset = panelOffset - panelMargin;
		var topScrollBoundary = realPanelOffset - gap;
		
		// a couple of numbers to account for margins and padding on the relevant elements
		var wrapperPaddingFix = parseFloat(wrapper.css('paddingTop').replace(/auto/, 0));
		var containerMarginFix = parseFloat(float_container.css('marginTop').replace(/auto/, 0));
		
		// do some work to fix IE misreporting the document width
		var ieFix = 0;
		
		var isMSIE = /*@cc_on!@*/0;
		
		if (isMSIE) {
			ieFix = getScrollerWidth() + 4;
		} 
						
		// ---------------------------------------------------------------------------------------------------
		
		thisWindow.bind("scroll.portamento", function () {
			
			if(thisWindow.height() > panel.outerHeight() && thisWindow.width() >= (thisDocument.width() - ieFix)) { // don't scroll if the window isn't big enough
				
				var y = thisDocument.scrollTop(); // current scroll position of the document
												
				if (y >= (topScrollBoundary)) { // if we're at or past the upper scrolling boundary
					if((panel.innerHeight() - wrapper.viewportOffset().top) - wrapperPaddingFix + gap >= wrapper.height()) { // if we're at or past the bottom scrolling boundary
						if(panel.hasClass('fixed') || thisWindow.height() >= panel.outerHeight()) { // check that there's work to do
							panel.removeClass('fixed');
							panel.css('top', (wrapper.height() - panel.innerHeight()) + 'px');
						}
					} else { // if we're somewhere in the middle
						panel.addClass('fixed');
						
						if(fullyCapableBrowser) { // supports position:fixed
							panel.css('top', gap + 'px'); // to keep the gap
						} else {							
							panel.clearQueue();
							panel.css('position', 'absolute').animate({top: (0 - float_container.viewportOffset().top + gap)});
						}
					}
				} else {
					// if we're above the top scroll boundary
					panel.removeClass('fixed');
					panel.css('top', '0'); // remove any added gap
				}
			} else {
				panel.removeClass('fixed');
			}
		});
		
		// ---------------------------------------------------------------------------------------------------
		
		thisWindow.bind("resize.portamento", function () {						
			// stop users getting undesirable behaviour if they resize the window too small
			if(thisWindow.height() <= panel.outerHeight() || thisWindow.width() < thisDocument.width()) {			
				if(panel.hasClass('fixed')) {
					panel.removeClass('fixed');
					panel.css('top', '0');
				}				
			} else {
				thisWindow.trigger('scroll.portamento'); // trigger the scroll event to place the panel correctly
			}
		});
		
		// ---------------------------------------------------------------------------------------------------
		
		thisWindow.bind("orientationchange.portamento", function () {
			// if device orientation changes, trigger the resize event
			thisWindow.trigger('resize.portamento'); 
		});
		
		// ---------------------------------------------------------------------------------------------------
		
		// trigger the scroll event immediately so that the panel is positioned correctly if the page loads anywhere other than the top.
		thisWindow.trigger('scroll.portamento');
		
	    // return this to maintain chainability
	    return this;	
	};
	
	// set some sensible defaults
	$.fn.portamento.defaults = {
	  'wrapper'				: $('body'), // the element that will act as the sliding panel's boundaries
	  'gap'					: 10, // the gap (in pixels) left between the top of the viewport and the top of the panel
	  'disableWorkaround' 	: false // option to disable the workaround for not-quite capable browsers 
	};
	
})(jQuery);
/* 代码整理：懒人之家 www.lanrenzhijia.com */