<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<footer class="bg-dark" style="height: 168px; position: relative; transform: translateY(-100%);">
		<div class="container-fluid">
			<div class="row justify-content-md-center py-1" style="border-top: 1px solid gray; border-bottom: 1px solid gray; height: 60px; font-size: small;">
				<div class="col-sm-1 pt-3 px-0" align="center" style="color: gray; width: 120px; cursor: pointer;" onclick="JavaScript:$('#agreefModal').modal('show')">
					개인정보처리방침
				</div>
				<div class="col-sm-1 pt-3 px-0" align="center" style="color: gray; width: 120px; cursor: pointer;" onclick="JavaScript:$('#serviceAgreefModal').modal('show')">
					제공서비스약관
				</div>
				<div class="col-sm-1 pt-3 px-0" align="center" style="color: gray; width: 130px; cursor: pointer;" onclick="JavaScript:$('#emailAgreefModal').modal('show')">
					이메일 무단수집거부
				</div>
				<div class="col-sm-1 pt-3 px-0" align="center" style="color: gray; border-right: 1px solid gray; cursor:pointer; width: 120px;" onclick="JavaScript:location.href='cscenterf'">
					고객센터
				</div>
				<div class="col-sm-1 pt-1 px-0" align="center" style="color: gray; border-right: 1px solid gray; width: 250px;">
					<i class="bi bi-telephone-fill"></i>&nbsp;주문전화 0000-0000<br>
					온라인주문 문의/취소 070-0000-0000
				</div>
				<div class="col-sm-1 pt-3 px-0" align="center"  style="color: gray; border-right: 1px solid gray; width: 180px;">
					<i class="bi bi-telephone-fill"></i>&nbsp;가맹문의 0000-0000
				</div>
				<c:if test="${not empty fcId}">
					<div class="col-sm-1 pt-3 px-0" align="center" onclick="javaScript:location.href='/federico/franchise/login'"
					style="color: gray; border-right: 1px solid gray; width: 100px; cursor: pointer;">
						<i class="bi bi-shop"></i>&nbsp;가맹점공간
					</div>
				</c:if>
				<c:if test="${empty fcId}">
					<div class="col-sm-1 pt-3 px-0" align="center" onclick="javaScript:location.href='/federico/franchise/loginf'"
					style="color: gray; border-right: 1px solid gray; width: 100px; cursor: pointer;">
						<i class="bi bi-shop"></i>&nbsp;가맹점공간
					</div>
				</c:if>
				<c:if test="${not empty loginID}">
					<div class="col-sm-1 pt-3 px-0" align="center" onclick="javaScript:location.href='/federico/headoffice/login'"
					style="color: gray; width: 100px; cursor: pointer;">
						<i class="bi bi-building"></i>&nbsp;본사공간
					</div>
				</c:if>
				<c:if test="${empty loginID}">
					<div class="col-sm-1 pt-3 px-0" align="center" onclick="javaScript:location.href='/federico/headoffice/loginf'"
					style="color: gray; width: 100px; cursor: pointer;">
						<i class="bi bi-building"></i>&nbsp;본사공간
					</div>
				</c:if>
			</div>
		</div>
		<div>
			<p class="m-0 text-center mt-3" style="color: gray; font-size: small;" align="left">
			법인명 : ㈜FEDERICO F&C / 대표자 : 김대표 / 사업자번호 : 000-00-00000 / 통신판매번호 : 제 0000-서울00-0000호 /<br>
			경기 성남시 분당구 돌마로 46 / 전화 : 031-0000-0000 / 팩스 : 031-0000-0000</p>
			<p class="m-0 text-center mt-1" style="color: gray; font-size: small;" align="left">Copyright &copy; Federico F&C all right reserved</p>
		</div>
	</footer>
	
	<!-- 개인정보처리방침 모달  -->
	<div class="modal fade" id="agreefModal" role="dialog">
		<div class="modal-dialog"
			style="max-width: 50%; width: auto; ">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">
					<i class="bi bi-person-check"></i>
					개인정보 처리방침</h5>
					<button type="button" class="btn-close infoClose"
						data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="card">
						<div class="card-body" style="overflow: auto; height: 500px;">
							<pre>
'(주)페데리코에프앤씨'는 (이하 '회사'는) 
고객님의 개인정보를 중요시하며, "정보통신망 이용촉진 및 정보보호"에 관한 법률을 준수하고 있습니다. 
						
회사는 개인정보처리방침을 통하여 고객님께서 제공하시는 개인정보가 어떠한 용도와 방식으로
이용되고 있으며, 개인정보보호를 위해 어떠한 조치가 취해지고 있는지 알려드립니다. 
						
회사는 개인정보처리방침을 개정하는 경우 웹사이트 공지사항(또는 개별공지)을 통하여 공지할 것입니다. 
						
■ 수집하는 개인정보 항목 
						
① 회사는 회원가입, 상담, 서비스 제공 등을 위해 아래와 같은 개인정보를 수집하고 있습니다. 
가. 성명 , 로그인ID , 비밀번호 , 생년월일 , 성별 , 자택 주소 , 휴대전화번호 , 이메일, 접속 로그 , 접속 IP 정보 
나. 서비스 이용 과정에서 아래와 같은 정보들이 자동 수집될 수 있습니다. 
-접속 IP 정보, 쿠키, 방문 일시, OS종류, 브라우저 종류, 위치정보, 카드번호 
② 개인정보 수집방법 가. 온라인 주문, 전화, 채용, 가맹문의, 이벤트 및 프로모션 응모 

■ 개인정보의 수집 및 이용목적 

① 회사는 수집한 개인정보를 다음의 목적을 위해 활용합니다. 
가. 서비스 제공에 관한 계약 이행 및 서비스 제공에 따른 요금정산 및 콘텐츠 제공 
나. 회원 관리 가) 회원제 서비스 이용에 따른 본인확인 , 불량회원의 부정 이용 방지와 비인가 사용 방지 , 가입의사 확인 , 불만처리 등 민원처리 , 고지사항 전달 
다. 채용
가) 상시 및 비상시 채용 
라. 가맹문의 
가) 가맹문의에 따른 개설 상담 
마. 통계 및 마케팅 광고에 활용 
가) 접속빈도 파악 등에 관한 통계 
나) 이벤트 등 광고성 정보 제공 

■ 개인정보의 보유 및 이용기간 

원칙적으로 회사는 개인정보의 수집 및 이용목적이 달성된 후 지체 없이 파기합니다. 단, 관계법령의 규정에 의하여 보존할 필요가 있는 경우
회사는 아래와 같이 관계법령에서 정한 일정한 기간 동안 회원정보를 보관합니다. 

1. 관련법령에 의한 정보보유 사유 
① 계약 또는 청약철회 등에 관한 기록 
- 보존 근거 : 전자상거래 등에서의 소비자보호에 관한 법률 
- 보존 기간 : 5년
② 대금결제 및 재화 등의 공급에 관한 기록 
- 보존 근거 : 전자상거래 등에서의 소비자보호에 관한 법률 
- 보존 기간 : 5년 ③ 소비자의 불만 또는 분쟁처리에 관한 기록 
- 보존 근거 : 전자상거래 등에서의 소비자보호에 관한 법률 
- 보존 기간 : 3년 ④ 본인 확인에 관한 기록 
- 보존 근거 : 정보통신망 이용촉진 및 정보보호 등에 관한 법률 
- 보존 기간 : 3개월 ⑤ 웹사이트 방문기록 
- 보존 근거 : 통신비밀보호법 
- 보존 기간 : 3개월 ⑥ 개인위치정보에 관한 기록 
- 보존 근거 : 위치정보의 보호 및 이용 등에 관한 법률 
- 보존 기간 : 1년 
						
■ 개인정보 제3자 제공
회사는 이용자의 개인정보를 명시한 범위 내에서만 처리하며, 정보주체의 동의, 법률의 특별한 규정에 해당하는 경우에만
개인정보를 제3자에게 제공합니다.
						
■ 수집한 개인정보의 위탁 
① 회사는 서비스 향상을 위해서 아래와 같이 개인정보를
위탁하고 있으며, 관계 법령에 따라 위탁업무 수행 목적 외 개인정보 처리 금지, 기술적 ∙ 관리적 보호조치, 재 위탁
제한, 수탁자에 대한 관리 ∙ 감독, 손해배상 등 책임에 관한 사항을 계약서 등 문서에 명시하고, 수탁자가 개인정보를
안전하게 처리하는지를 감독하고 있습니다. 회사의 개인정보 위탁처리 기관 및 위탁업무 내용은 아래와 같습니다.</pre>
							<table>
								<colgroup>
									<col style="width: 25%;">
									<col style="width: 25%;">
									<col style="width: 25%;">
									<col style="width: 25%;">
								</colgroup>
								<thead style="border-bottom : 3px solid black;">
									<tr>
										<th style="text-align: center;">수탁 업체명</th>
										<th style="text-align: center;">위탁 업무
											내용</th>
										<th style="text-align: center;">개인정보의
											보유 및 이용기간</th>
										<th style="text-align: center;">취급
											개인정보</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>㈜씨엔티테크</td>
										<td>홈페이지 및 모바일 채널 운영 지원 주문 중개 및 서버 인프라
											운영, 콜센터 주문 서비스 제공, 고객 상담 지원 및 매장 주문처리</td>
										<td>회원 탈퇴 혹은 위탁 계약 종료 시 까지</td>
										<td>성명, 로그인ID, 비밀번호, 생년월일, 성별, 주소,
											휴대전화번호, 이메일</td>
									</tr>
									<tr>
										<td>㈜KG 이니시스</td>
										<td>결제처리(신용카드)</td>
										<td>회원 탈퇴 혹은 위탁 계약 종료 시 까지</td>
										<td>신용카드정보</td>
									</tr>
									<tr>
										<td>㈜모빌리언스</td>
										<td>휴대전화 본인인증</td>
										<td>수집목적을 달성시 까지</td>
										<td>성명, 생년월일, 성별, 휴대전화번호</td>
									</tr>
									<tr>
										<td>버킷스튜디오</td>
										<td>비회원 인증, 문자 발송</td>
										<td>수집목적을 달성시 까지</td>
										<td>휴대전화번호</td>
									</tr>
									<tr>
										<td>처음소프트</td>
										<td>이메일 발송</td>
										<td>수집목적을 달성시 까지</td>
										<td>성명, 이메일</td>
									</tr>
									<tr>
										<td>㈜푸드테크</td>
										<td>POS 시스템 운영</td>
										<td>수집목적을 달성시 까지</td>
										<td>주소, 전화번호</td>
									</tr>
									<tr>
										<td>가맹점사업자</td>
										<td>가맹점사업자의 영업지역에 속한 주소지 회원에 대한 배달서비스
											제공</td>
										<td>수집목적을 달성시 까지</td>
										<td>주소, 전화번호</td>
									</tr>
								</tbody>
							</table>
							<pre>
② 위탁 업무의 내용이나 수탁자가 변경될 경우에는 지체없이 본 개인정보 처리방침을 통하여 공개하도록 하겠습니다. 

■ 개인정보의 파기절차 및 방법 

회사는 원칙적으로 개인정보 수집 및 이용목적이 달성된 후에는 해당 정보를 지체 없이 파기합니다. 파기절차 및 방법은 다음과 같습니다. 

① 파기절차 
회원님이 회원가입 등을 위해 입력하신 정보는 목적이 달성된 후 별도의 DB로 옮겨져(종이의 경우 별도의 서류함) 내부 방침 및 기타 관련 법령에 의한 정보보호 사유에
따라(보유 및 이용기간 참조) 일정 기간 저장된 후 파기되어집니다. 
별도 DB로 옮겨진 개인정보는 법률에 의한 경우가 아니고서는 보유되어지는 이외의 다른 목적으로 이용되지 않습니다. 

② 파기방법 
- 전자적 파일형태로 저장된 개인정보는 기록을 재생할 수 없는 기술적 방법을 사용하여 삭제합니다. 
개인정보의 분리보관 회원가입 시 고객이 선택한 개인정보 이용기간동안 회사 홈페이지 미접속 시 근거법령에 의거 다른 이용자의 개인정보와 분리보관 됩니다. 

■ 이용자의 권리와 그 행사방법 
이용자는 언제든지 등록되어 있는 자신의 개인정보를 조회하거나 수정할 수 있으며 가입해지를 요청할 수도 있습니다. 
이용자의 개인정보 조회 및 수정을 위해서는 ‘개인정보변경’(또는 ‘회원정보수정’ 등)을 클릭하여 본인 확인
절차를 거치신 후 직접 열람, 정정이 가능합니다. 
또한 가입해지(동의철회)를 위해서는 본 페이지 하단의 개인정보관리책임자에게 서면, 전화 또는 이메일로 연락하시면 지체 없이 조치하겠습니다. 
귀하가 개인정보의 오류에 대한 정정을 요청하신 경우에는 정정을 완료하기 전까지 당해 개인정보를 이용 또는 제공하지 않습니다. 또한 잘못된 개인정보를
제3자에게 이미 제공한 경우에는 정정 처리결과를 제3자에게 지체 없이 통지하여 정정이 이루어지도록 하겠습니다. 
‘회사’는 이용자 혹은 법정 대리인의 요청에 의해 해지 또는 삭제된 개인정보는 “‘회사’ 가 수집하는 개인정보의 보유 및
이용기간”에 명시된 바에 따라 처리하고 그 외의 용도로 열람 또는 이용할 수 없도록 처리하고 있습니다. 

■ 개인정보의 안전성 확보조치 
회사는 개인정보의 안전성 확보를 위해 개인정보보호법 시행령 제30조 제1항에 따른 조치 및 다음과 같은 조치를 취하고 있습니다. 

1. 기술적 대책 
① 회사는 이용자의 개인정보를 취급함에 있어 개인정보가 분실, 도난, 누출, 변조 또는 훼손되지 않도록 안정성 확보를 위하여 다음과 같은 기술적 대책을 강구하고 있습니다. 
② 이용자의 개인정보는 비밀번호에 의해 보호되며, 파일 및 전송 데이터를 암호화 하고 있습니다. 
③ 회사는 암호알고리즘을 이용하여 네트워크 상의 개인정보를 안전하게 전송할 수 있는 보안장치(SSL, VPN)를 채택하고 있습니다.
④ 해킹 등 외부 침입에 대비하여 각 서버마다 방화벽 등을 이용하여 보안에 만전을 기하고 있습니다. 

2. 관리적 대책 
① 회사는 이용자의 개인정보에 대한 접근권한을 최소한의 인원으로 제한하고 있습니다. 그 최소한의 인원에 해당하는 자는 다음과 같습니다. 
- 이용자를 직접 상대로 하여 마케팅 업무를 수행하는 자 
- 개인정보보호책임자 및 담당자 등 개인정보관리업무를 수행하는 자
- 기타 업무상 개인정보의 취급이 불가피한 자 
② 회사는 개인정보의 안전성 확보를 위해 내부관리계획을 수립하여 시행하며, 임직원 등에 대한 정기적인 교육을 시행하고 있습니다. 

3. 물리적 대책 
① 회사는 개인정보를 보관할 수 있는 별도의 보관장소 를 두고 있는 경우, 그 출입을 통제하고 있습니다. 
② 회사는 개인정보가 포함된 서류, 보조저장매체 등이 있는 경우 잠금 장치를 하여 보관하고 있습니다. 

■ 아동의 개인정보보호 회사는 만14세 미만 아동의 개인정보를
보호하기 위하여 회원가입은 만14세 이상만 가능하도록 하여 아동의 개인정보를 수집하지 않습니다. 

■ 개인정보 자동수집 장치의 설치, 운영 및 그 거부에 관한 사항 회사는 이용자 개개인에게 개인화되고 맞춤화된 서비스를 제공하기 위해
이용자의 정보를 저장하고 수시로 불러오는 '쿠키(cookie)'를 사용합니다. 

① 쿠키의 사용 목적 
회원과 비회원의 접속 빈도나 방문 시간 등의 분석, 이용자의 취향과 관심분야의 파악 및 자취 추적, 각종 이벤트 참여 정도 및 방문
회수 파악 등을 통한 타겟 마케팅 및 개인 맞춤 서 비스 제공 
② 쿠키 설정 거부 방법 
이용자는 쿠키 설치에 대해 거부할 수 있습니다. 단, 쿠키 설치를 거부하였을 경우 로그인이 필요한 일부 서비스의 이용이 어려울 수 있습니다.
(설정방법, IE 기준)웹 브라우저 상단의 도구 &gt; 인터넷 옵션 &gt; 개인정보 &gt; 사이트 차단 

■ 고지의 의무 현 개인정보처리방침 내용 추가, 삭제 및 수정이 있을 시에는 개정 최소 7일전부터 홈페이지 또는 이메일을 통해
고지할 것입니다. 

- 공고일자 : 2021년 12월 27일 
- 시행일자 : 2022년 01월 01일 

■ 개인정보에 관한 민원서비스 
회사는 고객의 개인정보를 보호하고 개인정보와 관련한 불만을 처리하기 위하여 아래와 같이 관련 부서 및 개인정보관리책임자를 지정하고 있습니다. 

개인정보보호책임자 성명 : 강광훈 차장 
전화번호 : 000-0000-0000
이메일 : kkh@federico.co.kr 

귀하께서는 회사의 서비스를 이용하시며 발생하는 모든 개인정보보호 관련
민원을 개인정보 관리책임자 혹은 담당부서로 신고하실 수 있습니다. 회사는 이용자들의 신고사항에 대해 신속 하게 충분한
답변을 드릴 것입니다. 기타 개인정보침해에 대한 신고나 상담이 필요하신 경우에는 아래 기관에 문의하시기 바랍니다.

1.개인분쟁조정위원회 (www.1336.or.kr/1336) 
2.정보보호마크인증위원회 (www.eprivacy.or.kr/02-580-0533~4) 
3.대검찰청 인터넷범죄수사센터 (http://icic.sppo.go.kr/02-3480-3600) 
4.경찰청 사이버테러대응센터 (www.ctrc.go.kr/02-392-0330) 
ο 본 방침은 : 2022 년 1월 1일 부터 시행됩니다.
						</pre>
						</div>

					</div>
					<div class="modal-footer">
						<div class="container-fluid">
							<div class="row">
								<button type="button" class="btn btn-secondary infoClose"
									data-bs-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 서비스이용약관 모달  -->
	<div class="modal fade" id="serviceAgreefModal" role="dialog">
		<div class="modal-dialog"
			style="max-width: 50%; width: auto; ">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">
					<i class="bi bi-person-check"></i>
					이용약관</h5>
					<button type="button" class="btn-close infoClose"
						data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="card">
						<div class="card-body" style="overflow: auto; height: 500px;">
							<pre>
제 1 장 총 칙

제 1 조 (목적)
이 약관은 페데리코피자(주)(이하 "회사"라 합니다)에서 제공하는 인터넷서비스(이하 "서비스"라 합니다)의 이용 조건 및 절차에 관한 기본적인 사항을 규정함을 목적으로 합니다.

제 2 조 (약관의 효력 및 변경)
① 이 약관은 서비스 화면이나 기타의 방법으로 이용고객에게 공지함으로써 효력을 발생합니다.
② 회사는 이 약관의 내용을 변경할 수 있으며, 변경된 약관은 제1항과 같은 방법으로 공지 또는 통지함으로써 효력을 발생합니다.

제 3 조 (용어의 정의)
이 약관에서 사용하는 용어의 정의는 다음과 같습니다.
① 회원 : 회사와 서비스 이용계약을 체결하거나 이용자 아이디(ID)를 부여받은 개인 또는 단체를 말합니다.
② 신청자 : 회원가입을 신청하는 개인 또는 단체를 말합니다.
③ 아이디(ID) : 회원의 식별과 서비스 이용을 위하여 회원이 정하고 회사가 승인하는 문자와 숫자의 조합을 말합니다.
④ 비밀번호 : 회원이 부여 받은 아이디(ID)와 일치된 회원임을 확인하고, 회원 자신의 비밀을 보호하기 위하여 회원이 정한 문자와 숫자의 조합을 말합니다.
⑤ 해지 : 회사 또는 회원이 서비스 이용계약을 취소하는 것을 말합니다.

제 2 장 서비스 이용계약

제 4 조 (이용계약의 성립)
① 이용약관 하단의 동의 버튼을 누르면 이 약관에 동의하는 것으로 간주됩니다.
② 이용계약은 서비스 이용희망자의 이용약관 동의 후 이용 신청에 대하여 회사가 승낙함으로써 성립합니다.

제 5 조 (이용신청)
① 신청자가 본 서비스를 이용하기 위해서는 회사 소정의 가입신청 양식에서 요구하는 이용자 정보를 기록하여 제출해야 합니다.
② 가입신청 양식에 기재하는 모든 이용자 정보는 모두 실제 데이터인 것으로 간주됩니다. 실명이나 실제 정보를 입력하지 않은 사용자는 법적인 보호를 받을 수 없으며, 서비스의 제한을 받을 수 있습니다.

제 6 조 (이용신청의 승낙)
① 회사는 신청자에 대하여 제2항, 제3항의 경우를 예외로 하여 서비스 이용신청을 승낙합니다.
② 회사는 다음에 해당하는 경우에 그 신청에 대한 승낙 제한사유가 해소될 때까지 승낙을 유보할 수 있습니다.
가. 서비스 관련 설비에 여유가 없는 경우
나. 기술상 지장이 있는 경우
다. 기타 회사가 필요하다고 인정되는 경우
③ 회사는 신청자가 다음에 해당하는 경우에는 승낙을 거부할 수 있습니다.
가. 다른 개인(회사)의 명의를 사용하여 신청한 경우
나. 이용자 정보를 허위로 기재하여 신청한 경우
다. 사회의 안녕질서 또는 미풍양속을 저해할 목적으로 신청한 경우
라. 기타 회사 소정의 이용신청요건을 충족하지 못하는 경우

제 7 조 (이용자정보의 변경)
회원은 이용 신청시에 기재했던 회원정보가 변경되었을 경우에는, 온라인으로 수정하여야 하며 변경하지 않음으로 인하여 발생되는 모든 문제의 책임은 회원에게 있습니다.

제 3 장 계약 당사자의 의무

제 8 조 (회사의 의무)
① 회사는 회원에게 각 호의 서비스를 제공합니다.
가. 신규서비스와 도메인 정보에 대한 뉴스레터 발송
나. 추가 도메인 등록시 개인정보 자동 입력
다. 도메인 등록, 관리를 위한 각종 부가서비스
② 회사는 서비스 제공과 관련하여 취득한 회원의 개인정보를 회원의 동의없이 타인에게 누설, 공개 또는 배포할 수 없으며, 서비스관련 업무 이외의 상업적 목적으로 사용할 수 없습니다. 단, 다음 각 호에 해당하는 경우는 예외입니다.
가. 전기통신기본법 등 법률의 규정에 의해 국가기관의 요구가 있는 경우
나. 범죄에 대한 수사상의 목적이 있거나 정보통신윤리 위원회의 요청이 있는 경우
다. 기타 관계법령에서 정한 절차에 따른 요청이 있는 경우
③ 회사는 이 약관에서 정한 바에 따라 지속적, 안정적으로 서비스를 제공할 의무가 있습니다.

제 9 조 (회원의 의무)
① 회원은 서비스 이용 시 다음 각 호의 행위를 하지 않아야 합니다.
가. 다른 회원의 ID를 부정하게 사용하는 행위
나. 서비스에서 얻은 정보를 회사의 사전승낙 없이 회원의 이용 이외의 목적으로 복제하거나 이를 변경, 출판 및 방송 등에 사용하거나 타인에게 제공하는 행위
다. 회사의 저작권, 타인의 저작권 등 기타 권리를 침해하는 행위
라. 공공질서 및 미풍양속에 위반되는 내용의 정보, 문장, 도형 등을 타인에게 유포하는 행위
마. 범죄와 결부된다고 객관적으로 판단되는 행위
바. 기타 관계법령에 위배되는 행위
② 회원은 관계법령, 이 약관에서 규정하는 사항, 서비스 이용 안내 및 주의 사항을 준수하여야 합니다.
③ 회원은 내용별로 회사가 서비스 공지사항에 게시하거나 별도로 공지한 이용 제한 사항을 준수하여야 합니다.

제 4 장 서비스 제공 및 이용

제 10 조 (회원 아이디(ID)와 비밀번호 관리에 대한 회원의 의무)
① 아이디(ID)와 비밀번호에 대한 모든 관리는 회원에게 책임이 있습니다. 회원에게 부여된 아이디(ID)와 비밀번호의 관리소홀, 부정사용에 의하여 발생하는 모든 결과에 대한 전적인 책임은 회원에게 있습니다.
② 자신의 아이디(ID)가 부정하게 사용된 경우 또는 기타 보안 위반에 대하여, 회원은 반드시 회사에 그 사실을 통보해야 합니다.

제 11 조 (서비스 제한 및 정지)
① 회사는 전시, 사변, 천재지변 또는 이에 준하는 국가비상사태가 발생하거나 발생할 우려가 있는 경우와 전기통신사업법에 의한 기간통신 사업자가 전기통신서비스를 중지하는 등 기타 불가항력적 사유가 있는 경우에는 서비스의 전부 또는 일부를 제한하거나 정지할 수 있습니다.
② 회사는 제1항의 규정에 의하여 서비스의 이용을 제한하거나 정지할 때에는 그 사유 및 제한기간 등을 지체없이 회원에게 알려야 합니다.

제5장 계약사항의 변경, 해지

제 12 조 (정보의 변경)
회원이 주소, 비밀번호 등 고객정보를 변경하고자 하는 경우에는 홈페이지의 회원정보 변경 서비스를 이용하여 변경할 수 있습니다.

제 13 조 (계약사항의 해지)
회원은 서비스 이용계약을 해지할 수 있으며, 해지할 경우에는 본인이 직접 서비스를 통하거나 전화 또는 온라인 등으로 회사에 해지신청을 하여야 합니다. 회사는 해지신청이 접수된 당일부터 해당 회원의 서비스 이용을 제한합니다. 회사는 회원이 다음 각 항의 1에 해당하여 이용계약을 해지하고자 할 경우에는 해지조치 7일전까지 그 뜻을 이용고객에게 통지하여 소명할 기회를 주어야 합니다.
① 이용고객이 이용제한 규정을 위반하거나 그 이용제한 기간 내에 제한 사유를 해소하지 않는 경우
② 정보통신윤리위원회가 이용해지를 요구한 경우
③ 이용고객이 정당한 사유 없이 의견진술에 응하지 아니한 경우
④ 타인 명의로 신청을 하였거나 신청서 내용의 허위 기재 또는 허위서류를 첨부하여 이용계약을 체결한 경우
회사는 상기 규정에 의하여 해지된 이용고객에 대해서는 별도로 정한 기간동안 가입을 제한할 수 있습니다.

제6장 손해배상

제 14 조 (면책조항)
① 회사는 회원이 서비스 제공으로부터 기대되는 이익을 얻지 못하였거나 서비스 자료에 대한 취사선택 또는 이용으로 발생하는 손해 등에 대해서는 책임이 면제됩니다.
② 회사는 회원의 귀책사유나 제3자의 고의로 인하여 서비스에 장애가 발생하거나 회원의 데이터가 훼손된 경우에 책임이 면제됩니다.
③ 회사는 회원이 게시 또는 전송한 자료의 내용에 대해서는 책임이 면제됩니다.
④ 상표권이 있는 도메인의 경우, 이로 인해 발생할 수도 있는 손해나 배상에 대한 책임은 구매한 회원 당사자에게 있으며, 회사는 이에 대한 일체의 책임을 지지 않습니다.

제 15 조 (관할법원)

서비스와 관련하여 회사와 회원간에 분쟁이 발생할 경우 회사의 본사 소재지를 관할하는 법원을 관할법원으로 합니다.

[부칙]

(시행일) 이 약관은 2018년 9월 4일부터 시행합니다.
						</pre>
						</div>

					</div>
					<div class="modal-footer">
						<div class="container-fluid">
							<div class="row">
								<button type="button" class="btn btn-secondary infoClose"
									data-bs-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 이메일무단수집거부 -->
	<div class="modal fade" id="emailAgreefModal" role="dialog">
		<div class="modal-dialog"
			style="max-width: 50%; width: auto; ">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">
					<i class="bi bi-person-check"></i>
					이메일무단수집거부</h5>
					<button type="button" class="btn-close infoClose"
						data-bs-dismiss="modal" aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="card">
						<div class="card-body" style="overflow: auto; height: 500px;">
							<pre>
페데리코피자 홈페이지에 게시된 전자우편(e-mail)주소를 전자우편 수집 프로그램이나 그 밖의 기술적 장치를 이용하여 무단으로 수집하는 것을 거부하며, 
이를 위반시 정보통신망이용 촉진 및 정보보호 등에 관한 법률('이하' 정보통신망법)에 의해 형사 처벌됨을 유념하시기 바랍니다.
						</pre>
						</div>

					</div>
					<div class="modal-footer">
						<div class="container-fluid">
							<div class="row">
								<button type="button" class="btn btn-secondary infoClose"
									data-bs-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	

</body>
</html>