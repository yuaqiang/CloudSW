jQuery.fn.liveSearch=function(conf){var config=jQuery.extend({url:'/search-results.php?q=',id:'jquery-live-search',duration:400,typeDelay:200,loadingClass:'loading',onSlideUp:function(){},uptadePosition:false},conf);var liveSearch=jQuery('#'+config.id);if(!liveSearch.length){liveSearch=jQuery('<div id="'+config.id+'"></div>').appendTo(document.body).hide().slideUp(0);jQuery(document.body).click(function(event){var clicked=jQuery(event.target);if(!(clicked.is('#'+config.id)||clicked.parents('#'+config.id).length||clicked.is('input'))){liveSearch.slideUp(config.duration,function(){config.onSlideUp()})}})}return this.each(function(){var input=jQuery(this).attr('autocomplete','off');var liveSearchPaddingBorderHoriz=parseInt(liveSearch.css('paddingLeft'),10)+parseInt(liveSearch.css('paddingRight'),10)+parseInt(liveSearch.css('borderLeftWidth'),10)+parseInt(liveSearch.css('borderRightWidth'),10);var repositionLiveSearch=function(){var tmpOffset=input.offset();var inputDim={left:tmpOffset.left,top:tmpOffset.top,width:input.outerWidth(),height:input.outerHeight()};inputDim.topPos=inputDim.top+inputDim.height;inputDim.totalWidth=inputDim.width-liveSearchPaddingBorderHoriz;liveSearch.css({position:'absolute',left:inputDim.left+'px',top:inputDim.topPos+'px',width:inputDim.totalWidth+'px'})};var showLiveSearch=function(){repositionLiveSearch();$(window).unbind('resize',repositionLiveSearch);$(window).bind('resize',repositionLiveSearch);liveSearch.slideDown(config.duration)};var hideLiveSearch=function(){liveSearch.slideUp(config.duration,function(){config.onSlideUp()})};input.focus(function(){if(this.value!==''){if(liveSearch.html()==''){this.lastValue='';input.keyup()}else{setTimeout(showLiveSearch,1)}}}).keyup(function(){if(this.value!=this.lastValue){input.addClass(config.loadingClass);var q=this.value;if(this.timer){clearTimeout(this.timer)}this.timer=setTimeout(function(){jQuery.get(config.url+q,function(data){input.removeClass(config.loadingClass);if(data.length&&q.length){liveSearch.html(data);showLiveSearch()}else{hideLiveSearch()}})},config.typeDelay);this.lastValue=this.value}})})};