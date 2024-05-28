$(document).ready(function() {
	$("#all").click(function() {
		//	alert('a');
		$("#show").empty;
		$("#count").empty;

		$.ajax({
			type: 'get',
			url: 'jq05.jsp',
			data: { 'gubun': 'all' },
			dataType: 'xml',
			success: function(datas) {
				let str = "<table border='1'>";
				str += "<tr><th>사번</th><th>이름</th><th>직급</th><th>연봉</th></tr>"
				let count = 0;
				$(datas).find('jikwon').each(function() {
					str += '<tr>';
					str += `<td>${$(this).find("sabun").text()}</td>`;
					str += `<td>${$(this).find("irum").text()}</td>`;
					str += `<td>${$(this).find("jik").text()}</td>`;
					str += `<td>${$(this).find("pay").text()}</td>`;
					str += '</tr>';
					count++;
				});
				str += "</table>";
				$('#show').append(str);
				$('#count').append(count);
			}
		}).fail(function() {
			$('#show').text("all 처리 오류");
		});

	});
	/*
	$("#search").click(function() {
		//	alert('b');
	}).fail(function() {
		$('#disp').text("search 처리 오류");
	});
	*/
});
