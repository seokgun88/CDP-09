var GetData;

//현재 주소를 가져오는 것 같다: http://localhost:8083/myproj/view/my.jsp
var curWwwPath=window.document.location.href;
//호스트 주소 목록 가져오기 후 같다: myproj/view/my.jsp
var pathName=window.document.location.pathname;
var pos=curWwwPath.indexOf(pathName);
//호스트 주소 가져오는 것 같다: http://localhost:8083
var localhostPaht=curWwwPath.substring(0,pos);
//테이프 가져오는 '/' 프로젝트 이름, 만일: /myproj
var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
//받다 http://localhost:8083/myproj
var realPath=localhostPaht+projectName;

var timetable_height;
function resizeIframe(obj) {
	if($(window).width() < 800 && $(window).width() < 500 ){
		timetable_height= obj.contentWindow.document.body.scrollHeight*1.3 + 'px';
	}
	else{
		timetable_height = 1500 + 'px';	
	}
	obj.style.height = timetable_height;
	obj.style.width = '100%';
}
function getClassTimetable(place,placenum){
	console.log(place);
	console.log(placenum);
	var classtimetable_URL = realPath + "/classtimetable?place="+place+"&placenum="+placenum;
	
	console.log(classtimetable_URL);
	var classtimetable_HTML = "<iframe id=\'timetableIframe\' src="+classtimetable_URL+" style= \"display:block;\" frameBorder=\'0\' scrolling=\"no\" marginwidth=\'0\' marginheight=\'0\' onload=\"resizeIframe(this)\"></iframe>";
	document.getElementById('ClasstimetableDiv').innerHTML = "<br /> <h2 align=\'center\'>" + place+placenum +"</h2>"+ classtimetable_HTML;
	$(window).scrollTop($('#ClasstimetableDiv').position().top);
	//.animate({ scrollTop: $("#ClasstimetableDiv").offset().top }, 100);
		
//	document.getElementById('ClasstimetableDiv').innerHTML(place+placenum);
}; 
var w = 100, h = 100;
$(document).ready(function(){
	if($(window).height() < 800 && $(window).width() < 500 ){
		w = $(window).width() - 50;
		h = $(window).height() - 50;		
		oMap.setLevel(12);
	}
	else{
		w = 1200;
		h = 600;		
	}
	oMap.setSize(new nhn.api.map.Size(w, h));
	$(window).resize(function() {
		if($(window).height() < 800 && $(window).width() < 500 ){
			w = $(window).width() - 50;
			h = $(window).height() - 50;
		}
		else{
			w = 1200;
			h = 600;
		}
		oMap.setSize(new nhn.api.map.Size(w, h));
		if($(window).width() < 300){
			document.getElementById('timetableIframe').style.height = timetable_height;
		}
		else if($(window).width() < 500){
			document.getElementById('timetableIframe').style.height = timetable_height;
		}
		else{
			document.getElementById('timetableIframe').style.height = '1500px';
		}
	});
});

// 과목넘버 <a href="링크">과목이름</a> <button>버튼</button></br>
function getJsonData(parameter){
	var BuildingList = new Array();
	$.ajax({		
		url: realPath+"/getroomusalbe?place="+encodeURI(encodeURIComponent(parameter)),
		type: 'GET',
		async: false, // 동기
		timout: 10000,
		dataType: 'JSON',
		success: function (data){			
			GetData = "";			
			$.each(data, function(key,subdata){
				console.log(subdata.room);
				BuildingList.push(subdata.room);
			});
		}		
	})
	$.ajax({
		url: realPath+"/getroom?place="+encodeURI(encodeURIComponent(parameter)),
		type: 'GET',
		async: false, // 동기
		timout: 10000,
		dataType: 'JSON',
		success: function (data){
			GetData = "";
			$.each(data, function(key,subdata){
				var color = "9ac2b7";
				for( var i = 0 ; i < BuildingList.length ; i++){
					// 사용중인 방일 때 red
					if(subdata.room == BuildingList[i]){ 
						color = "F98E9D";
						break;
					}
				}
//				console.log(subdata.room);
//				console.log(color);
				//green : style="background-color:#9ac2b7;border-color:#9ac2b7;
				//red   : style="background-color:#F98E9D;border-color:#F98E9D;

				GetData += '<input type="button" onclick="getClassTimetable(' + "'" + parameter + "'" + ',' + "'" + subdata.room + "'" + ')" value='
						+ subdata.room + ' style="border-radius: 4px; width: 100px;background-color:#'+color+';border-color:#'+color+';">' + "</br>";
			});
		}		
	})
};

//function getJsonData(parameter){
//	$.ajax({
//		url: "/second/buildingdata?place="+parameter,
//		type: 'GET',
//		async: false, // 동기
//		timout: 10000,
//		dataType: 'JSON',
//		success: function (data){			
//			GetData = "";
//
//			console.log(data);
//			$.each(data, function(key,subdata){
//
//				GetData += '<span style="font-weight:bold;">'+subdata.placeName + "-" + subdata.placeNum + "</span></br>";;
//				//  과목넘버 <a href="링크">과목이름</a> <button>버튼</button></br>			
//				$.each(subdata.classList, function(key,state){
//					obj = state;
//					console.log(obj.professor);
//					GetData += '<input type="button" value=' + obj.subjectNum +' style="border-radius: 4px;width: 100px">    ' + '  <a href="'+obj.link+'" target="_blank">'+obj.subject+"</a></br>";
//
////					GetData += "[*]";
////					GetData += obj.time + "/";
////					GetData += obj.professor + "/";
////					GetData += obj.subject + "/";
////					GetData += obj.subjectNum + "/";
////					GetData += obj.reg_college + "/";
////					GetData += obj.link + "/\n";
//
//				});
//			});
//
//
//		}		
//	})
//};



var oPoint = new nhn.api.map.LatLng(35.8900362,128.610107);
nhn.api.map.setDefaultPoint('LatLng');
oMap = new nhn.api.map.Map('testMap' ,{
	point : oPoint,
	zoom : 11,
	enableWheelZoom : true,
	enableDragPan : true,
	enableDblClickZoom : false,
	mapMode : 0,
	activateTrafficMap : false,
	activateBicycleMap : false,
	minMaxLevel : [ 11, 14 ],
	size : new nhn.api.map.Size(1100, 600)
});
var mapZoom = new nhn.api.map.ZoomControl(); // - 줌 컨트롤 선언
mapZoom.setPosition({left:40, bottom:40}); // - 줌 컨트롤 위치 지정
oMap.addControl(mapZoom); // - 줌 컨트롤 추가.

var markerCount = 0;

var oSize = new nhn.api.map.Size(28, 37);
var oOffset = new nhn.api.map.Size(14, 37);
var oIcon = new nhn.api.map.Icon('http://static.naver.com/maps2/icons/pin_spot2.png', oSize, oOffset);

var mapInfoTestWindow = new nhn.api.map.InfoWindow(); // - info window 생성
mapInfoTestWindow.setVisible(false); // - infowindow 표시 여부 지정.
oMap.addOverlay(mapInfoTestWindow);	// - 지도에 추가.

var oLabel = new nhn.api.map.MarkerLabel(); // - 마커 라벨 선언.
oMap.addOverlay(oLabel); // - 마커 라벨 지도에 추가. 기본은 라벨이 보이지 않는 상태로 추가됨.

mapInfoTestWindow.attach('changeVisible', function(oCustomEvent) {
	if (oCustomEvent.visible) {
		oLabel.setVisible(false);
	}
});


oMap.attach('mouseenter', function(oCustomEvent) {
	var oTarget = oCustomEvent.target;
	// 마커위에 마우스 올라간거면
	if (oTarget instanceof nhn.api.map.Marker) {
		var oMarker = oTarget;
		oLabel.setVisible(true, oMarker); // - 특정 마커를 지정하여 해당 마커의 title을 보여준다.
	}
});

oMap.attach('mouseleave', function(oCustomEvent) {
	var oTarget = oCustomEvent.target;
	// 마커위에서 마우스 나간거면
	if (oTarget instanceof nhn.api.map.Marker) {
		oLabel.setVisible(false);
	}
});

oMap.attach('click', function(oCustomEvent) {	
	var oTarget = oCustomEvent.target;
	mapInfoTestWindow.setVisible(false);
	// 마커 클릭하면
	if (oTarget instanceof nhn.api.map.Marker) {
		// 겹침 마커 클릭한거면
		if (oCustomEvent.clickCoveredMarker) {
			return;
		}
		console.log(oTarget.getTitle());
		getJsonData(oTarget.getTitle());

		// - InfoWindow 에 들어갈 내용은 setContent 로 자유롭게 넣을 수 있습니다. 외부 css를 이용할 수 있으며,
		// - 외부 css에 선언된 class를 이용하면 해당 class의 스타일을 바로 적용할 수 있습니다.
		// - 단, DIV 의 position style 은 absolute 가 되면 안되며,
		// - absolute 의 경우 autoPosition 이 동작하지 않습니다.
		mapInfoTestWindow.setContent('<DIV style="overflow:scroll; overflow-x:hidden; border-top:1px solid; border-bottom:2px groove black; border-left:1px solid; border-right:2px groove black;margin-bottom:1px;color:black;background-color:white; width:auto; height:200px;">'+
				'<span style="color: #000000 !important;display: inline-block;font-size: 12px !important;letter-spacing: -1px !important;white-space: nowrap !important; padding: 2px 2px 2px 2px !important">' +
				GetData				
				+'<span></div>');



		mapInfoTestWindow.setPoint(oTarget.getPoint());
		mapInfoTestWindow.setVisible(true);
		mapInfoTestWindow.setPosition({right : 15, top : 30});
		mapInfoTestWindow.autoPosition();
		return;
	}
//	var oPoint = oCustomEvent.point;
//	var oMarker = new nhn.api.map.Marker(oIcon, { title : '마커 : ' + oPoint.toString() });
//	oMarker.setPoint(oPoint);
//	oMap.addOverlay(oMarker);
});



//공대9호관,정보전산원(전자계산소),IT대학4호관(제2학생회관),공대12호관,IT대학3호관(공대11호관)
//키 : 공대9호관, 값 : PLACE : 128.608503, 35.8868758
//키 : 정보전산원(전자계산소), 값 : PLACE : 128.6136625 , 35.8913899
//키 : IT대학4호관(제2학생회관), 값 : PLACE 128.6109246, 35.8881246
//키 : 공대12호관, 값 : PLACE 128.6101646, 35.8884472
//키 : IT대학3호관(공대11호관), 값 : PLACE 128.6104694, 35.8880567
//키 : IT대학2호관(공대5호관), 값 : PLACE
//128.6116539 , 35.8874354
//키 : IT대학1호관(공대10호관), 값 : PLACE
//128.6126824 , 35.8874637

var LatList = [35.8868758 , 35.8913899 , 35.8881246 , 35.8884472 , 35.8880567 ,
               35.8875293 , 35.8879707 , 35.887663 , 35.8872754 , 35.8872658 , 
               35.8874637 , 35.8874354 , 35.8897331 , 35.8918308 , 35.8868033 ,
               35.8935001 , 35.891216 , 35.8909301 , 35.8914319 , 35.8902045 ,
               35.8891189 , 35.8867463 , 35.8909446 , 35.8864752 , 35.8922183 ,
               35.8884811 , 35.8865567 , 35.8908397 , 35.8911701 , 35.8902696 ,
               35.8891031 , 35.8901736 , 35.8936514 , 35.8899044 , 35.8927298 ,
               35.8897575 , 35.8898075 , 35.8898798 , 35.8903515 , 35.8880305 ,
               35.8877403 , 35.8895046 , 35.8931888 , 35.8925757 , 35.8893142 , 
               35.8892481 , 35.8898105 , 35.8868925 , 35.8907796 , 35.8896678];
var LonList = [128.608503 , 128.6136625 , 128.6109246 , 128.6101646 , 128.6104694 ,
               128.6085177 , 128.608557 , 128.6096496 , 128.6096442 , 128.6106855 ,
               128.6126824 , 128.6116539 , 128.609058 , 128.6113254 , 128.6060923 ,
               128.6111714 , 128.6095662 , 128.6082992 , 128.6086385 , 128.6058405 ,
               128.6140462 , 128.6132374 , 128.614526 , 128.6084365 , 128.6132919 , 
               128.6157434 , 128.6139472 , 128.6093061 , 128.6106292 , 128.6066059 ,
               128.6157522 , 128.615036 , 128.6123591 , 128.6158631 , 128.6106288 ,
               128.6064104 , 128.6078293 , 128.613658 , 128.6133544 , 128.6066667 ,
               128.6156145 , 128.6149413 , 128.612367 , 128.6123695 , 128.6160244 , 
               128.604645 , 128.6052511 , 128.6136095 , 128.6070229 , 128.6102648];
var TitleList = [ "공대9호관" , "정보전산원(전자계산소)" , "IT대학4호관(제2학생회관)" , "공대12호관" , "IT대학3호관(공대11호관)", 
                  "공대1호관" , "공대2호관" , "공대3호관" , "공대6호관" , "공대7호관",
                  "IT대학1호관(공대10호관)" , "IT대학2호관(공대5호관)","생명공학관(교양과정동)","KNU글로벌프라자","생물학관",
                  "예술대학" , "농대1호관" , "농대2호관" , "농대3호관", "수영장",
                  "복지관" , "수의과대학1" , "외국어교육관" , "화학관" , "종합정보센타",
                  "사회과학대학" , "동물병원" , "농대사과센타" , "인문대학" , "자연과학대학",
                  "경상대학" , "우당교육관" , "조소동" , "생활과학대학" , "대강당" ,
                  "제2과학관" , "제1과학관" , "교육대학원" , "사범대학" , "운동장" ,
                  "법과대학" , "제4합동강의동" , "조형관(제3합동강의동)" , "약학대학(제2합동강의동)" , "국제경상관" ,
                  "제1체육관" , "제2체육관" , "수의대해부학실습실" , "복현회관" , "대학원동"];


for( var i = 0 ; i < LatList.length ; i++){
	var oPoint = new nhn.api.map.LatLng(  LatList[i] , LonList[i] );
	var oMarker = new nhn.api.map.Marker(oIcon, { title : TitleList[i]});
	oMarker.setPoint(oPoint);
	oMap.addOverlay(oMarker);
}

