$(document).ready(function() {
	  var tips;
	  $(".product-operation a:nth-child(1)").bind("mouseover",function(){
        var message = '加入收藏夹';
        if ($(this).children('span').attr('class') != 'icon-favorite') {
          message = '移除收藏夹';
        }
        tips = layer.tips(message, this);
	  });
	  $(".product-operation a:nth-child(2)").bind("mouseover",function(){
        tips = layer.tips('加入购物车', this);
	  });
	 
  $(".product-operation a").bind("mouseout",function(){
		 layer.close(tips);
  });
  var tempLength = 0; //临时变量,当前移动的长度
  var viewNum = 6; //设置每次显示图片的个数量
  var moveNum = 2; //每次移动的数量
  var moveTime = 300; //移动速度,毫秒
  var scrollDiv = $(".spec-scroll .items ul"); //进行移动动画的容器
  var scrollItems = $(".spec-scroll .items ul li"); //移动容器里的集合
  var moveLength = scrollItems.eq(0).width() * moveNum; //计算每次移动的长度
  var countLength = (scrollItems.length - viewNum) * scrollItems.eq(0).width(); //计算总长度,总个数*单个长度

  //下一张
  $(".spec-scroll .next").bind("click",function(){
    if(tempLength < countLength){
      if((countLength - tempLength) > moveLength){
        scrollDiv.animate({left:"-=" + moveLength + "px"}, moveTime);
        tempLength += moveLength;
      }else{
        scrollDiv.animate({left:"-=" + (countLength - tempLength) + "px"}, moveTime);
        tempLength += (countLength - tempLength);
      }
    }
  });
  //上一张
  $(".spec-scroll .prev").bind("click",function(){
    if(tempLength > 0){
      if(tempLength > moveLength){
        scrollDiv.animate({left: "+=" + moveLength + "px"}, moveTime);
        tempLength -= moveLength;
      }else{
        scrollDiv.animate({left: "+=" + tempLength + "px"}, moveTime);
        tempLength = 0;
      }
    }
  });

  
  tabs(".product-info-tab-hd","active",".product-info-tab-bd");
});

function tabs(tabTit,on,tabCon){
  $(tabTit).children().hover(function(){
    $(this).addClass(on).siblings().removeClass(on);
    var index = $(tabTit).children().index(this);
    $(tabCon).children().eq(index).show().siblings().hide();
  });
};


(function($) {
  $.fn.getParam = function (param) {
    var reg = new RegExp("(^|&)" + param + "=([^&]*)(&|$)","i");
    var r = window.location.search.substr(1).match(reg);
    if (r!=null) return (r[2]);
    return "";
  };

  $.fn.activePage = function(_active){
    $(".nav-lists li").removeClass("active");
    $(".nav-lists ."+_active).addClass("active");
  }
})($);


$(window).on('scroll',function(){
  var $scroll=$(this).scrollTop();
  if($scroll>=400){
    $('#anchor').show();
  }else{
    $('#anchor').hide();
  }

  //4.拖动滚轮，对应的楼梯样式进行匹配
  $('.anchor-info').each(function(){
    var $loutitop=$('.anchor-info').eq($(this).index()).offset().top+ 100;
    if($loutitop>$scroll){
      $('#anchor li').removeClass('active');
      $('#anchor li').eq($(this).index()).addClass('active');
      return false;
    }
  });
});

//2.获取每个楼梯的offset().top,点击楼梯让对应的内容模块移动到对应的位置  offset().left
var $loutili=$('#anchor li').not('.last');
$loutili.on('click',function(){
  $(this).addClass('active').siblings('li').removeClass('active');
  var $loutitop=$('.anchor-info').eq($(this).index()).offset().top - 142;
  //获取每个楼梯的offsetTop值
  $('html,body').animate({//$('html,body')兼容问题body属于chrome
    scrollTop:$loutitop
  })
});

//3.回到顶部
$('.last').on('click',function(){
  $('html,body').animate({//$('html,body')兼容问题body属于chrome
    scrollTop:0
  })
});


