
function getJsonData(parameter){
	$.ajax({
		url: "/second/buildingdata?place="+parameter,
		type: 'GET',
		async: false, // 동기
		timout: 10000,
		dataType: 'JSON',		
		success: function (data){
			console.log(data);
			alert(data);
		}
		
	})
};

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
	minMaxLevel : [ 1, 14 ],
	size : new nhn.api.map.Size(1100, 600)
});
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
	var oPoint = oCustomEvent.point;
	var oTarget = oCustomEvent.target;
	mapInfoTestWindow.setVisible(false);
	// 마커 클릭하면
	if (oTarget instanceof nhn.api.map.Marker) {
		// 겹침 마커 클릭한거면
		if (oCustomEvent.clickCoveredMarker) {
			return;
		}
		getJsonData(oTarget.getTitle());

		// - InfoWindow 에 들어갈 내용은 setContent 로 자유롭게 넣을 수 있습니다. 외부 css를 이용할 수 있으며,
		// - 외부 css에 선언된 class를 이용하면 해당 class의 스타일을 바로 적용할 수 있습니다.
		// - 단, DIV 의 position style 은 absolute 가 되면 안되며,
		// - absolute 의 경우 autoPosition 이 동작하지 않습니다.
		mapInfoTestWindow.setContent('<DIV style="border-top:1px solid; border-bottom:2px groove black; border-left:1px solid; border-right:2px groove black;margin-bottom:1px;color:black;background-color:white; width:auto; height:auto;">'+
				'<span style="color: #000000 !important;display: inline-block;font-size: 12px !important;font-weight: bold !important;letter-spacing: -1px !important;white-space: nowrap !important; padding: 2px 2px 2px 2px !important">' +
				'Hello World <br /> ' + oTarget.getPoint() + oTarget.getTitle()
				+'<span></div>');



		mapInfoTestWindow.setPoint(oTarget.getPoint());
		mapInfoTestWindow.setVisible(true);
		mapInfoTestWindow.setPosition({right : 15, top : 30});
		mapInfoTestWindow.autoPosition();
		return;
	}
	var oMarker = new nhn.api.map.Marker(oIcon, { title : '마커 : ' + oPoint.toString() });
	oMarker.setPoint(oPoint);
	oMap.addOverlay(oMarker);
});

//공대9호관,정보전산원(전자계산소),IT대학4호관(제2학생회관),공대12호관,IT대학3호관(공대11호관)
//키 : 공대9호관, 값 : PLACE : 128.608503, 35.8868758
//키 : 정보전산원(전자계산소), 값 : PLACE : 128.6136625 , 35.8913899
//키 : IT대학4호관(제2학생회관), 값 : PLACE 128.6109246, 35.8881246
//키 : 공대12호관, 값 : PLACE 128.6101646, 35.8884472
//키 : IT대학3호관(공대11호관), 값 : PLACE 128.6104694, 35.8880567

var LatList = [35.8868758, 35.8913899 , 35.8881246, 35.8884472, 35.8880567];
var LonList = [128.608503, 128.6136625, 128.6109246, 128.6101646, 128.6104694];
var TitleList = [ "공대9호관" , "정보전산원(전자계산소)" , "IT대학4호관(제2학생회관)" , "공대12호관" , "IT대학3호관(공대11호관)" ];

for( var i = 0 ; i < LatList.length ; i++){
	var oPoint = new nhn.api.map.LatLng(  LatList[i] , LonList[i] );
	var oMarker = new nhn.api.map.Marker(oIcon, { title : TitleList[i]});
	oMarker.setPoint(oPoint);
	oMap.addOverlay(oMarker);
}

