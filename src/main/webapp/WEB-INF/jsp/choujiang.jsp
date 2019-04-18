<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!--
    这里通过get形式的type字段判断不同的明细(乐豆明细，游戏币明细，对战记录);
-->


<html style="font-size: 64px;" lang="en">
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>抽奖页面</title>
<meta charset="UTF-8">
<!--移动端需要的meta-->
<meta name="viewport"
	content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta name="format-detection" content="telephone=no">
<!--关键词和描述-->
<meta name="keywords" content="">
<meta name="description" content="">

<!--css-->
<link rel="stylesheet" type="text/css" href="static/rotate.css">

<!--js-->
<script type="text/javascript" src="static/jquery.js"></script>
<script type="text/javascript" src="static/rem.js"></script>
<script type="text/javascript">
    //提示框
    
      //一来就播放动画且设置能翻牌的数量
      var obj2 =   $("#allParent2").rotateEx({
          maxNum:1,
          maxNumCall:function(){
              showTool("翻到了最大的数量啦");
          },
          clickAmtStart:function(o1,o2,o3){
              showTool("恭喜抽中"+o3.innerText);
          },
          changeAmtCall:function(o1,o2){
              //随机修改奖品的位置
              obj2.reset();
          }
      });
      obj2.rotate.allBack();
</script>
<!--页面抽奖流程相关js-->
<script type="text/javascript" src="static/rotate.js"></script>
<!--传统的流式布局-->
<style>
body {
	margin: 0;
	padding: 0;
}

.jpBox {
	position: absolute;
	left: 50%;
	top: 50%;
	transform: translate(-50%, -50%);
	font-size: 0.375rem;
	color: #333333;
	white-space: nowrap;
}

.title {
	text-align: center;
	font-size: 16px;
	margin-top: 100px;
	margin-bottom: 30px;
}
#yanhua {
	postion:absolute;
	float:left;
}
</style>
</head>
<body style="max-width: 640px; display: block; margin: auto; background-color: #b5d0c2">
	
<!--一来就将牌设置为反面状态-->
	
	<div class="title"><h1>猪年抽大奖</h1></div>
	<!-- 添加背景音乐 -->
	<div id="yanhua">
		<embed id="embed" src="static/bgm.mp3" autostart="true" loop="true" hidden="true" align="top" width="0" height="0">
	</div>
	
	<div id="allParent2" class="Y-content">
		<div class="item i1"
			style="transform: translate3d(-203.974px, -259.5px, 0px);">
			<div class="parent">
				<div class="face"
					style="transition-duration: 0ms; transform: rotateY(90deg) translate3d(0px, 0px, 0px); display: none;">

					<div class="jpBox">${message0 }</div>
				</div>
				<div class="back"
					style="transition-duration: 0ms; transform: rotateY(360deg) translate3d(0px, 0px, 0px);">
					<img src="static/a1.png" style="width: 100%; height: 100%">
				</div>
			</div>
		</div>
		<div class="item i2"
			style="transform: translate3d(0px, -259.5px, 0px);">
			<div class="parent">
				<div class="face"
					style="transition-duration: 0ms; transform: rotateY(90deg) translate3d(0px, 0px, 0px); display: none;">

					<div class="jpBox">${message1 }</div>
				</div>
				<div class="back"
					style="transition-duration: 0ms; transform: rotateY(360deg) translate3d(0px, 0px, 0px);">
					<img src="static/a1.png" style="width: 100%; height: 100%">
				</div>
			</div>
		</div>
		<div class="item i3"
			style="transform: translate3d(203.974px, -259.5px, 0px);">
			<div class="parent">
				<div class="face"
					style="transition-duration: 0ms; transform: rotateY(90deg) translate3d(0px, 0px, 0px); display: none;">

					<div class="jpBox">${message2 }</div>
				</div>
				<div class="back"
					style="transition-duration: 0ms; transform: rotateY(360deg) translate3d(0px, 0px, 0px);">
					<img src="static/a1.png" style="width: 100%; height: 100%">
				</div>
			</div>
		</div>
		<div class="item i4"
			style="transform: translate3d(-203.974px, 0px, 0px);">
			<div class="parent">
				<div class="face"
					style="transition-duration: 0ms; transform: rotateY(90deg) translate3d(0px, 0px, 0px); display: none;">

					<div class="jpBox">${message3 }</div>
				</div>
				<div class="back"
					style="transition-duration: 0ms; transform: rotateY(360deg) translate3d(0px, 0px, 0px);">
					<img src="static/a1.png" style="width: 100%; height: 100%">
				</div>
			</div>
		</div>
		<div class="item i5"
			style="transform: translate3d(203.974px, 0px, 0px);">
			<div class="parent">
				<div class="face"
					style="transition-duration: 0ms; transform: rotateY(90deg) translate3d(0px, 0px, 0px); display: none;">

					<div class="jpBox">${message4 }</div>
				</div>
				<div class="back"
					style="transition-duration: 0ms; transform: rotateY(360deg) translate3d(0px, 0px, 0px);">
					<img src="static/a1.png" style="width: 100%; height: 100%">
				</div>
			</div>
		</div>
		<div class="item i6"
			style="transform: translate3d(-203.974px, 259.5px, 0px);">
			<div class="parent">
				<div class="face"
					style="transition-duration: 0ms; transform: rotateY(90deg) translate3d(0px, 0px, 0px); display: none;">

					<div class="jpBox">${message5 }</div>
				</div>
				<div class="back"
					style="transition-duration: 0ms; transform: rotateY(360deg) translate3d(0px, 0px, 0px);">
					<img src="static/a1.png" style="width: 100%; height: 100%">
				</div>
			</div>
		</div>
		<div class="item i7"
			style="transform: translate3d(0px, 259.5px, 0px);">
			<div class="parent">
				<div class="face"
					style="transition-duration: 0ms; transform: rotateY(90deg) translate3d(0px, 0px, 0px); display: none;">

					<div class="jpBox">${message6 }</div>
				</div>
				<div class="back"
					style="transition-duration: 0ms; transform: rotateY(360deg) translate3d(0px, 0px, 0px);">
					<img src="static/a1.png" style="width: 100%; height: 100%">
				</div>
			</div>
		</div>
		<div class="item i8"
			style="transform: translate3d(203.974px, 259.5px, 0px);">
			<div class="parent">
				<div class="face"
					style="transition-duration: 0ms; transform: rotateY(90deg) translate3d(0px, 0px, 0px); display: none;">

					<div class="jpBox">${message7 }</div>
				</div>
				<div class="back"
					style="transition-duration: 0ms; transform: rotateY(360deg) translate3d(0px, 0px, 0px);">
					<img src="static/a1.png" style="width: 100%; height: 100%">
				</div>
			</div>
		</div>
		<div class="selectBox" id="">
			<div class="parent">
				<div class="face">
					<div class="jpBox">猪年大吉</div>
				</div>
				<div class="back"></div>
			</div>
		</div>
	</div>
	<!--提示框-->
	
	<div id="tooltip" 
		style="display: none; position: fixed; left: 50%; top: 50%; transform: translate(-50%, -50%); font-size: 18px; background-color: rgba(0, 0, 0, 0.5); color: white; padding: 5px 10px; z-index: 1000; white-space: nowrap"></div>
	<form action="handleChoujiang.do" method="post" style="margin: auto;">
		<input type="submit" id="tip666" name="tip999" style="margin: auto; width: 96px; height: 32px" value="点击领奖"/>
	</form>
	<script type="text/javascript">
    //提示框
    function showTool(str){
        var ele =  document.getElementById("tooltip");
        ele.innerHTML = str;
        ele.style.display="";
        //setTimeout(hideTool,1000);
    }
    function hideTool(str){
        var ele =  document.getElementById("tooltip");
        ele.innerHTML = str;
        ele.style.display="none";
    }

    window.onload = function(){
        //一来就播放动画且设置能翻牌的数量
        var obj2 =   $("#allParent2").rotateEx({
            maxNum:1,
            maxNumCall:function(){
                //showTool("翻到了最大的数量啦");
            },
            clickAmtStart:function(o1,o2,o3){
                showTool("恭喜抽中"+o3.innerText);
                $("#tip666").val(o3.innerText);
                /* 通过id获取烟花特效和烟花音效 */
                $("#yanhua").html("");
                $("#yanhua").html("<embed id='mySwf' src='static/gif.swf' type='application/x-shockwave-flash' width='1003' height='600' wmode='transparent'><embed id='embed' src='static/yanhua.mp3' autostart='true' loop='true' hidden='true' align='top' width='0' height='0'>");
                //$("#yanhua").html("<embed id='embed' src='static/yanhua.mp3' autostart='true' loop='true' hidden='true' align='top' width='0' height='0'>");
            },
            clickAmtEnd:function(o1,o2){
                //o2显示所有奖品
                o2.allFace();
            },
            changeAmtCall:function(o1,o2){
                //随机修改奖品的位置
                obj2.reset();
            }
        });
        obj2.rotate.allBack();
      };
		
</script>

</body></html>