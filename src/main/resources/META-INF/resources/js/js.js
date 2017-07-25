// JavaScript Document

// 焦点图
(function(d,D,v){d.fn.responsiveSlides=function(h){var b=d.extend({auto:!0,speed:1E3,timeout:7E3,pager:!1,nav:!1,random:!1,pause:!1,pauseControls:!1,prevText:"Previous",nextText:"Next",maxwidth:"",controls:"",namespace:"rslides",before:function(){},after:function(){}},h);return this.each(function(){v++;var e=d(this),n,p,i,k,l,m=0,f=e.children(),w=f.size(),q=parseFloat(b.speed),x=parseFloat(b.timeout),r=parseFloat(b.maxwidth),c=b.namespace,g=c+v,y=c+"_nav "+g+"_nav",s=c+"_here",j=g+"_on",z=g+"_s",
o=d("<ul class='"+c+"_tabs "+g+"_tabs' />"),A={"float":"left",position:"relative"},E={"float":"none",position:"absolute"},t=function(a){b.before();f.stop().fadeOut(q,function(){d(this).removeClass(j).css(E)}).eq(a).fadeIn(q,function(){d(this).addClass(j).css(A);b.after();m=a})};b.random&&(f.sort(function(){return Math.round(Math.random())-0.5}),e.empty().append(f));f.each(function(a){this.id=z+a});e.addClass(c+" "+g);h&&h.maxwidth&&e.css("max-width",r);f.hide().eq(0).addClass(j).css(A).show();if(1<
f.size()){if(x<q+100)return;if(b.pager){var u=[];f.each(function(a){a=a+1;u=u+("<li><a href='#' class='"+z+a+"'>"+a+"</a></li>")});o.append(u);l=o.find("a");h.controls?d(b.controls).append(o):e.after(o);n=function(a){l.closest("li").removeClass(s).eq(a).addClass(s)}}b.auto&&(p=function(){k=setInterval(function(){var a=m+1<w?m+1:0;b.pager&&n(a);t(a)},x)},p());i=function(){if(b.auto){clearInterval(k);p()}};b.pause&&e.hover(function(){clearInterval(k)},function(){i()});b.pager&&(l.bind("click",function(a){a.preventDefault();
b.pauseControls||i();a=l.index(this);if(!(m===a||d("."+j+":animated").length)){n(a);t(a)}}).eq(0).closest("li").addClass(s),b.pauseControls&&l.hover(function(){clearInterval(k)},function(){i()}));if(b.nav){c="<a href='#' class='"+y+" prev'>"+b.prevText+"</a><a href='#' class='"+y+" next'>"+b.nextText+"</a>";h.controls?d(b.controls).append(c):e.after(c);var c=d("."+g+"_nav"),B=d("."+g+"_nav.prev");c.bind("click",function(a){a.preventDefault();if(!d("."+j+":animated").length){var c=f.index(d("."+j)),
a=c-1,c=c+1<w?m+1:0;t(d(this)[0]===B[0]?a:c);b.pager&&n(d(this)[0]===B[0]?a:c);b.pauseControls||i()}});b.pauseControls&&c.hover(function(){clearInterval(k)},function(){i()})}}if("undefined"===typeof document.body.style.maxWidth&&h.maxwidth){var C=function(){e.css("width","100%");e.width()>r&&e.css("width",r)};C();d(D).bind("resize",function(){C()})}})}})(jQuery,this,0);
$(function() {
    $(".f426x240").responsiveSlides({
        auto: true,
        pager: true,
        nav: true,
        speed: 700,
        maxwidth: 517
    });
	$(".proinfo .f426x240").responsiveSlides({
        auto: true,
        pager: true,
        nav: true,
        speed: 700,
        maxwidth: 650
    });
    $(".f160x160").responsiveSlides({
        auto: true,
        pager: true,
        speed: 700,
        maxwidth: 160
    });
});

$(function(){
	//导航
	$(".navClick").click(function(){
		$(".nav").toggleClass("navanimate")
		$(this).toggleClass("navClickHover")
		})
	$(".nav li").click(function(){
		$(this).addClass("active").siblings("li").removeClass("active");
		})
	var bodyWidth=$(window).width();
	var bodyHeight=$(window).height();
	$(".headerbg").width(bodyWidth);
	$(".headerbg").height(bodyHeight);
	$(".hdtx1").animate({
		bottom:"0",
		opacity:"1"
		},1000,function(){
			$(".hdtx2").animate({
				bottom:"0",
		opacity:"1"
				})
			})
	//偶数添加newlist
	$(".newlist:odd").addClass("newlist2")
	$(".newlist2 .zhou").attr("src","images/zhou2.png");
	//系统集成显示隐藏
	$(".systemimg li").click(function(){
		$(this).parents("ul").hide();
		var showindex=$(this).attr("title");
		$(".systemlist").eq(showindex).fadeIn().siblings(".systemBox").hide();
		if(showindex>3){
			alert("暂时只有前四个有效果,四个以后默认显示第一个");
			$(".systemlist").eq(0).fadeIn().siblings(".systemBox").hide();
			}
		})
	$(".systemlist .sysLeft").click(function(){
		$(".systemlist").hide();
		$(this).parents(".systemlist").prev(".systemlist").fadeIn();
		var lefteq=$(this).parents(".systemlist").attr("title");
		if(lefteq==0){
			$(".systemlist:first").fadeIn();
			}
		})
	$(".systemlist .sysRight").click(function(){
		$(".systemlist").hide();
		$(this).parents(".systemlist").next(".systemlist").fadeIn();
		var righteq=$(this).parents(".systemlist").attr("title");
		var eqlastsys=$(".systemlist:last").attr("title");
		if(righteq==3){
			$(".systemlist:last").fadeIn();
			}
		})
	$(".systemlist .sysback a,.proMore3,.proback").click(function(){
		location.reload();//刷新页面
		})
	//关于媒体
	$(".midiabox li").click(function(){
		$(".midiabox").hide();
		var mediaeqs=$(this).index();
		$(".midialist").eq(mediaeqs).show();
		})
	//关于媒体切换
	var $oBox=$('.slide');
	 $oBox.each(function(){
	     var imgeq1=$(this).find(".midiabox li")
		 var texteq1=$(this).find(".midialist")
		 imgeq1.click(function(){
			var imglistindex2=$(this).index();
		    $(texteq1).eq(imglistindex2).show().siblings(".case-details").hide();
			})
	   })
	 $(".medBack a").click(function(){
		 $(".midialist").hide();
		 $(this).parents(".slide").find(".midiabox").fadeIn();
		 })
	//点击显示对应产品
	$(".prosmimg").click(function(){
		var proimgsrc=$(this).find("img").attr("src");
		$(".proBigImg img").fadeIn();
		$(".proBigImg img").attr("src",proimgsrc);
		var proeq=$(this).attr("title");
		$(".proBigImg").attr("title",proeq);
		})
	$(".proBigImg").click(function(){
		var probigeq=$(this).attr("title")
		$(".proinfo").eq(probigeq).fadeIn().siblings(".proinfo").hide();
		$(this).parents(".product").hide();
		if(probigeq>3){
			alert("暂时只有前四个有效果,四个以后默认显示第一个");
			$(".proinfo").eq(0).fadeIn().siblings(".proinfo").hide();
			}
		})
	
	$(".proleft").click(function(){
		$(".proinfo").hide();
		$(this).parents(".proinfo").prev(".proinfo").fadeIn();
		var lefteq2=$(this).parents(".proinfo").attr("title");
		if(lefteq2==0){
			$(".proinfo:first").fadeIn();
			}
		})
	$(".proright").click(function(){
		$(".proinfo").hide();
		$(this).parents(".proinfo").next(".proinfo").fadeIn();
		var righteq2=$(this).parents(".proinfo").attr("title");
		var eqlastpro=$(".proinfo:last").attr("title");
		if(righteq2==3){
			$(".proinfo:last").fadeIn();
			}
		})
	//技术支持
	$(".jishu").click(function(){
		$(this).parents(".product").hide();
		$(".Technology").fadeIn();
		})	
	//方案体现
	$(".plan-shaixuan:first").show();
	$(".planeq a").click(function(){
		$(this).addClass("planCur").siblings("a").removeClass("planCur");
		var planindexs=$(this).index();
		$(".scrolllist").show();
		$(".planlist").hide();
		$(".plan-shaixuan").eq(planindexs).fadeIn().siblings(".plan-shaixuan").hide();
	})
	
	var $fangantixian=$('.plan-shaixuan');
	 $fangantixian.each(function(){
	     var click1=$(this).find(".scrolllist li")
		 var list1=$(this).find(".planlist")
		 click1.click(function(){
			$(".scrolllist").hide();
			var listindex1=$(this).index();
		    $(list1).eq(listindex1).show().siblings(".planlist").hide();
			})
		var planLeft=$(this).find(".planlist .sysLeft")
		var planRight=$(this).find(".planlist .sysRight")
		planLeft.click(function(){
		$(".planlist").hide();
		$(this).parents(".planlist").prev(".planlist").fadeIn();
		var lefteq3=$(this).parents(".planlist").attr("title");
		if(lefteq3==0){
			$(".planlist:first").fadeIn();
			}
		})
		var planlen=$(this).find(".planlist").length;
		var planlen2=planlen-1;
		planRight.click(function(){
			$(".planlist").hide();
			$(this).parents(".planlist").next(".planlist").fadeIn();
			var righteq3=$(this).parents(".planlist").attr("title");
			if(righteq3==planlen2){
				$(this).parents(".planlist:first").fadeIn();
				}
			})	
	   })
	   
	$(".planlist .sysback").click(function(){
		$(".plan-shaixuan:first").show();
		$(".scrolllist:first").show().siblings(".scrolllist").hide();
		$(".planlist").hide();
		$(".planeq a:first").addClass("planCur").siblings("a").removeClass("planCur");
		})
		
		
	/*关于我们*/
	$(".aboutList:first").fadeIn();
	$(".aboutEq li").click(function(){
		$(this).addClass("aboutCur").siblings("li").removeClass("aboutCur");
		var aboutEq=$(this).index();
		$(".aboutList").eq(aboutEq).fadeIn().siblings(".aboutList").hide();
		})
	//屏幕滚动
	/*$(window).scroll(function(){
		if($(window).scrollTop() > 0)
		{
		 alert(bodyHeight)
		}
		
	  })*/
	})


	
	
	