$(function() {
	$("#commentRegistBtn").click(addComment);
});

// comment 등록
function addComment(e) {
	
	e.preventDefault();

	var queryString = $("#commentForm").serialize();
	
	$.ajax({
		type: "POST",
		url: "/boardComment/ajax/boardCommentRegist",
		dataType: "json",
		data: queryString,
		error: registError,
		success: registSuccess
	});
}

// comment 업데이트 폼
function forUpdateComment(commentNo, e) {

	e.preventDefault();

	$.ajax({
		type: "GET",
		url: "/boardComment/ajax/boardCommentUpdateForm",
		dataType: "json",
		data: {
			commentNo : commentNo
		},
		error: forUpdateError,
		success: forUpdateSuccess
	});
	
}

// comment 삭제
function deleteComment(commentNo, e) {

	e.preventDefault();
	
}

// comment 등록 success
function registSuccess(data) {

	var totComments = Number($("#totComments").text()) + 1;
	var html = '';
	
	html += '<div class="col-sm-1">';
	html += 	'<div class="thumbnail">';
	html += 		'<img class="img-responsive user-photo" src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png">';
	html += 	'</div>';
	html += '</div>';
	html += '<div class="col-sm-11">';
	html += 	'<div class="panel panel-default">';
	html += 		'<div class="panel-heading">';
	html += 			'<strong>' + data.register.userId + '</strong>';
	html += 			'<i class="glyphicon glyphicon-time"></i><span class="text-muted">' + data.formattedRegistDate + '</span>';
	html += 			'<button type="button" class="btn btn-danger btn-xs clearfix pull-right" id="deleteCommentBtn">';
	html += 				'<span class="glyphicon glyphicon-trash"></span>';
	html += 			'</button>';
	html += 			'<button type="button" class="btn btn-primary btn-xs clearfix pull-right" id="updateCommentBtn">';
	html += 				'<span class="glyphicon glyphicon-pencil"></span>';
	html += 			'</button>';
	html += 		'</div>';
	html += 	'<div class="panel-body">' + data.comment + '</div>';
	html += '	</div>';
	html += '</div>';
	
	$(".comment").prepend(html);
	$("#totComments").text(totComments);
	
}

// comment 등록 error
function registError() {
	
}

//comment 업데이트폼 success
function forUpdateSuccess(data) {
	
	console.log(data);
	var boardNo = $("#boardNo").val();
	$("#comment_" + data.commentNo).empty();
	
	/*
	var html = '';
	html += '<form action="/boardComment/boardCommentUpdate" method="post" id="commentUpdateForm">';
	html += 	'<input type="hidden" name="boardNo" value="' + boardNo + '" />';
	html += 	'<textarea class="form-control" id="comment" name="comment" placeholder="comment" rows="3"></textarea>';
	html += 	'<div class="col-md-12" id="buttonDiv">';
	html += 		'<button type="button" class="btn btn-success pull-right" id="commentUpdateBtn">작성</button>';
	html += 	'</div>
	html += '</form>';
		
	$("#comment_" + data.commentNo).append(html);
	*/
}

function forUpdateError() {
	
}