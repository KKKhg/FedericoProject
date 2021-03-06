<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
 	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
<title>Federico Company</title>
<link href="/federico/resources/css/styles.css" rel="stylesheet" />
<script  src="https://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
<script src="/federico/resources/myLib/jquery-3.2.1.min.js"></script>
<script src="/federico/resources/myLib/headOffice_Script.js"></script>
<!-- chart js -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
<script>
$(function(){
	
	$.ajax({
		type: 'get',
		url: 'allFcchartsevenday',
		success: function(data){
			if(data.success == 'success'){
				var chartLabel =[];
				var chartData =[];
				var chartColor = [];
				var borderColor = [];
				var chartID ="allFcLastSevenDaysChart";
				
				
				var data = data.charData;
				$.each(data, function(index, element){
					chartLabel.push(element.chartLabel);
					chartData.push(element.chartCount);
					var strRGBA = 'rgba(154, 205, 50, 0.3)';
					var borderRGBA = 'rgb(50, 205, 89)';
					chartColor.push(strRGBA);
					borderColor.push(borderRGBA);
					
					console.log(element.chartCount);
					console.log(element.chartLabel);
				}) //each
				
				new Chart(chartID, {
					type : 'horizontalBar',
					data : {
						labels : chartLabel,
						datasets : [ {
							label: '?????????',
							data : chartData,
							backgroundColor : chartColor,
							borderColor : borderColor,
							fill : false,
							borderWidth: 1
						} ]
					},
					options : {
						legend: {
								labels: {
										fontColor: 'Black'	
								}
						},
						title: {
								display: true,
								text: '??? ????????? ?????? ?????? ??????',
								fontSize: 20,
								fontStyle: 'bold',
								fontColor: 'black'
						},
						scales: {
								yAxes:[{
										barPercentage:0.4,
										fontColor: 'black'
								}],
								xAxes: [{
										fontColor: 'black'
								}]
						}
					}
				});//?????????		
				
			} else {
				alert('??????????????? ??????????????????.\n?????? ??????????????????.');
			}
		},
		error: function(){
			alert('??????????????? ??????????????????.\n?????? ??????????????????.');
		}
	})//ajax
})//ready
</script>
<style type="text/css">
.vw-1{font-size: 1vw;}

</style>
</head>
<body>
<!-- navtop inlcud -->
 	<%@ include file="navtop.jsp" %> 

<!-- layoutSidenav ?????? -->
<div id="layoutSidenav">
	<%@ include file="navside.jsp" %> 
	<div id="layoutSidenav_content">
	<!-- ?????? ?????? -->
		<!-- ?????? ???????????? ?????? -->
		<div class="row m-1" style=" border: 1px solid lightgray; height: 400px; overflow: auto;">
				<!-- ?????? ?????? -->
				<div class="col-6 vw-1" align="center">
					<div class="row m-1" style="height: 190px; border: 1px solid lightgray;">
						<div class="col-4 p-0">
							<br><span>?????? ??????</span><br><b style="font-size: 1.7vw"><fmt:formatNumber value="${yesterdaySales}" pattern="#,###"/></b> ???
						</div>
						<div class="col-4 p-0">
							<br><span>?????? ??????</span><br><b style="font-size: 1.7vw"><fmt:formatNumber value="${todaySales}" pattern="#,###"/></b> ???<br>
						
							<c:if test="${yesterdaySales != 0 && todaySales != 0}">
								<fmt:parseNumber var="dailySalesRatio" value="${todaySales/yesterdaySales * 100}" integerOnly="true" />
								<c:if test="${dailySalesRatio>=100}">
									<span style="color: blue;">(+ ${dailySalesRatio-100}%)</span>
								</c:if>
								<c:if test="${dailySalesRatio<100}">
									<span style="color: red;">(- ${100-dailySalesRatio}%)</span>
								</c:if>
							</c:if>
							<c:if test="${yesterdaySales == 0 || todaySales == 0}">
								<span style="color: red;">( - )</span>
							</c:if>	
						</div>
						<div class="col-4 p-0">
							<br><span>?????? ??????</span><br><b style="font-size: 1.7vw"><fmt:formatNumber value="${thisMonthSales}" pattern="#,###"/></b> ???<br>
							<c:if test="${lastMonthSales != 0 && thisMonthSales != 0}">
								<fmt:parseNumber var="monthlySalesRatio" value="${thisMonthSales/lastMonthSales * 100}" integerOnly="true" />
								<c:if test="${monthlySalesRatio>=100}">
									<span style="color: blue;">(+ ${monthlySalesRatio-100}%)</span>
								</c:if>
								<c:if test="${monthlySalesRatio<100}">
									<span style="color: red;">(- ${100-monthlySalesRatio}%)</span>
								</c:if>									
							</c:if>		
							<c:if test="${lastMonthSales == 0 || thisMonthSales == 0}">
								<span style="color: red;">( - )</span>
							</c:if>
						</div>	
					</div>
					<div class="row m-1" style="height: 190px; border: 1px solid lightgray;">
						<div class="col-6">
							<br><span>??????????????? ??????</span><br><b style="font-size: 1.7vw">${numberOfAllFranchise}</b>
						</div>
						<div class="col-6">
							<br><span>?????? ???????????????</span><br><b style="font-size: 1.7vw">${numberOfNewFranchise}</b>
						</div>
						
					</div>
				</div>
				<!-- ?????? ?????? -->
				<div class="col-6">
					<div class="container-fluid px-4 mt-3">
						<canvas id="allFcLastSevenDaysChart" class="chartCanvas" ></canvas>
					</div>
				</div>
		</div>		
		<!-- ???????????? ?????? ?????????????????? -->
		<div class="row m-1" style=" border: 1px solid lightgray; height: 400px; overflow: auto;">
			<div class="container-fluid" align="center">
				<div class="mt-1 mb-3">
					<span class="fs-3 fw-bold btn" onclick="javascript:location.href='fcorder?flag=N'">????????? ???????????? ??????</span>
				</div>
				<!-- ???????????? ????????? ????????? -->
					<table class="table table-striped" onclick="javascript:location.href='fcorder?flag=N'" style="cursor: pointer;">
						<colgroup>
							<col style="width: 15%;">							
							<col style="width: 30%;">							
							<col style="width: 35%;">							
							<col style="width: 20%;">							
						</colgroup>
						<thead>
							<tr align="center">
								<th>????????????</th>
								<th>????????????</th>
								<th>????????????</th>
								<th>????????????</th>
							</tr>
						</thead> 
						<tbody>
							<c:if test="${empty orderList}">
								<tr align="center"><th colspan="6"><span>????????? ??????????????? ????????????.</span></th></tr>
							</c:if>								
							<c:forEach var="vo" items="${orderList}">
								<tr align="center">
									<td>${vo.fcOrderSeq}</td>
									<td>${vo.fcId}</td>
									<td>${vo.fcOrderDate}</td>
									<td><fmt:formatNumber value="${vo.sumPrice}" pattern="#,###"/> ???</td>
								</tr>
							</c:forEach>
						</tbody>
					</table><!-- ???????????? ????????? ????????? -->
			</div>
		</div><!-- ???????????? ?????? ?????????????????? -->		
		
		
		
		
		
		
		
		
		
		
		<!-- footer inlcud -->
		<div><%@ include file="footer.jsp" %></div>
	</div> <!-- ?????? ??? -->
</div> <!-- layoutSidenav ??? -->			



<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="/federico/resources/js/scripts.js"></script>

</body>
</html>