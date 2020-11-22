$(document).ready(function(){
		$(".conent_warp").mouseover(function(){
			$(this).children(".conent_nei").css("border","1px solid #d62c2c");	
		});
		$(".conent_warp").mouseout(function(){
			$(this).children(".conent_nei").css("border","1px solid #e2e2e2");
		});
	});
$('#demo02').flexslider({      
	animation: "solid", //动画效果  
	animationLoop: true, //是否循环播放  
	startAt: 0, // Integer: 开始播放的 slide，从 0 开始计数  
	slideshow: true, // Boolean: 是否自动播放  
	slideshowSpeed: 3000, // Integer: ms 滚动间隔时间  
	animationSpeed: 600, // Integer: ms 动画滚动速度  
	direction: "horizontal",// String: 滚动方向 ["horizontal"|"vertical"]  
	touch: true
});