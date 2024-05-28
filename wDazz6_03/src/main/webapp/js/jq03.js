$(document).ready(function(){
	$('#id_err').hide();
	$('#age_err1').hide();
	$('#age_err2').hide();
	$('#pwd_err1').hide();
	$('#pwd_err2').hide();
	
	$('#btnSend').click(function(){
		// 입력 자료 오류 검사 후 자료를 서버로 전송
		// ID 검사
		let id = $('#userID').val(); // document.querySelector("#userID").value
	//	alert(id);
		if (id.length < 2 || isNaN(id) === false) {
			$('#id_err').show();
			return false;
		} else {
			$('#id_err').hide();
		}
		
		
		// age 입력 검사
		let age = $('#age').val();
		if (age.length < 1) {
			$('#age_err1').show();
			return false;
		} else {
			$('#age_err1').hide();
		}
		
		// age 숫자만 허용
		/*
		for (let i = 0; i < age.length; i++) {
			// 한 글자마다의 아스키코드 얻기 
			let data = age.charAt(i).charCodeAt(0); 
			// alert(data);
			if (data < 48 || data > 57) { // 아스키코드를 활용하여 숫자만 입력받기
				$('#age_err2').show();
				return false;
			} else {
				$('#age_err2').hide();
			}
		}
		*/
		
		// 방법 1 : 사용자 정의 함수 작성 후 호출
		/* 
		if(!isPositiveIntegerMyFunc(age) || age < 15 || age > 100){
			$('#age_err2').show();
			return false;
		} else {
			$('#age_err2').hide();
		}
		*/
		
		// 방법 2 : 정규표현식을 사용한 사용자 정의 함수 작성 후 호출
		if(!isValidAgeMyFunc(age)) {
			$('#age_err2').show();
			return false;
		} else {
			$('#age_err2').hide();
		}
		
		
		// 비밀번호 검사
		let pwd1 = $('#pwd1').val();
		if(pwd1.length < 1) {
			$('#pwd1').next().show(); // next() : next sibling을 의미
			return false;
		} else {
			$('#pwd1').next().hide(); // next(), prev()
		}
		
		let pwd2 = $('#pwd2').val();
		if(pwd1 !== pwd2) {
			$('#pwd_err2').show();
			return false;
		} else {
			$('#pwd_err2').hide();
		}
		
		// form 태그에서 입력한 자료를 서버 파일로 전송
		$('#signFrm').attr('action', 'jq03.jsp').attr('method', 'post').submit();
	});
});

function isPositiveIntegerMyFunc(para) { // 함수 만들기
	let num = Number(para);
	return Number.isInteger(num) && num > 0;
}

function isValidAgeMyFunc(para) { // 정규표현식 사용 함수 만들기
	let ageRegex = /^(1[5-9]|[2-9][0-9]|100)$/;
	return ageRegex.test(para); // age가 정규표현식과 일치하는지 테스트한 값 반환
}