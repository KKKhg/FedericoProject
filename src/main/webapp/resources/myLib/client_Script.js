





// ============================= 메뉴조회, 장바구니 관련 (현구) =======================================
$(function(){
	// 모달닫힐떄 장바구니 수량 1로 리셋
	$('#addCartModal').on('hidden.bs.modal', function(){
		$('#addCartQty').val(1);
	})
	
	
}) //ready

// 장바구니모달 열기
function showAddCartModal(menuIndex){
	$('#addCartModal').modal('show');
	$('#btn-addCart').attr('onclick','addCartNoneMember('+menuIndex+')');
}

//비회원 카트추가
function addCartNoneMember(menuIndex){
	
	$.ajax({
		type: 'get',
		url: 'addCartNoneMember',
		data: {
			menuIndex: menuIndex,
			menuQty: $('#addCartQty').val()			
		},
		success: function(data){
			if(data.success == 'success'){
				alert('장바구니에 담았습니다.');
				$('#addCartModal').modal('hide');
			} else{
				alert('통신장애로 실패했습니다.<br>다시 시도해주세요.');
			}
		},
		error: function(){
			alert('통신장애로 실패했습니다.<br>다시 시도해주세요.');
		}
	})//ajax
}//addCart

// 장바구니 수량조절.
function arrowUp(inputId){
	$('#'+inputId).val(Number($('#'+inputId).val())+1);
}

function arrowDown(inputId){
	if(Number($('#'+inputId).val())>1){
	$('#'+inputId).val(Number($('#'+inputId).val())-1);
	}
}

// 장바구니 버튼 보였다 안보였다
function btnShow(index){
	console.log('up'+index)
	$('#hidden'+index).css('display','inline-block');
	$('#hiddenbtn'+index).css('display','inline-block');
}
		
function btnHide(index){
	console.log('down'+index)

	$('#hidden'+index).css('display','none');
	$('#hiddenbtn'+index).css('display','none');
}




// ============================= 메뉴조회, 장바구니 관련 (현구) =======================================




// ============================= 로그인 회원관련 (광훈) =======================================

$(function(){
	
	
	
	
	
	
})//

//클릭이펙트
function clickEffect(id){
	$('.checked').css({
		"color" : 'gray',
		"border-bottom" : "1px solid lightgray"
		
	});
	$('#'+id).css({
		"color" : 'black',
		"border-bottom" : "2px solid black"
	});
}//clickEffect


function showdiv(id) {
	if (id == 'clientLogin') {
		$('#outer_1').css('display', 'block');
		$('#outer_2').css('display', 'none');
		$('#innerTitle').css('display','block');
		$('#inner_1').css('display', 'block');
		$('#inner_2').css('display', 'none');

	} else if (id == 'nonOrder') {
		$('#outer_1').css('display', 'block');
		$('#outer_2').css('display', 'none');
		$('#innerTitle').css('display','none');
		$('#inner_1').css('display', 'none');
		$('#inner_2').css('display', 'block');
		
	} else {
		$('#outer_1').css('display', 'none');
		$('#outer_2').css('display', 'block');
	}

}







