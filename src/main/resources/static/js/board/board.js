$(function() {
	$("#commentRegistBtn").click(addComment);
	
	$(document).on("click", "#commentCancelBtn", function() {
		var commentNo = $(this).parent().find("[name=commentNo]").val();
		boardCommentDetail(commentNo);
	});
	
	$(document).on("click", "#commentUpdateBtn", function() {
		updateComment(event);
	});
});

//comment 상세
function boardCommentDetail(commentNo) {

	$.ajax({
		type: "GET",
		url: "/boardComment/ajax/boardCommentDetail",
		dataType: "json",
		data: {
			commentNo : commentNo
		},
		error: commentDetailError,
		success: commentDetailSuccess
	});
}

//comment 상세 success
function commentDetailSuccess(data) {
	$("#comment_" + data.commentNo).empty();
	$("#comment_" + data.commentNo).html(data.comment);
}

//comment 상세 error
function commentDetailError() {
	alert("에러가 발생하였습니다.");
}

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

// comment 등록 success
function registSuccess(data) {
	
	$("#comment").val('');
	var totComments = Number($("#totComments").text()) + 1;
	
    var dataObj = {"userId" : data.register.userId, "date" : data.formattedRegistDate, "commentNo" : data.commentNo, "comment" : data.comment};
	
	$(".comment").prepend(commentHtml(dataObj));
	$("#totComments").text(totComments);
	
}

// comment 등록 error
function registError() {
	alert("에러가 발생하였습니다.");
}

//comment 업데이트
function updateComment(e) {
	
	e.preventDefault();

	var queryString = $("#commentUpdateForm").serialize();
	
	$.ajax({
		type: "POST",
		url: "/boardComment/ajax/boardCommentUpdate",
		dataType: "json",
		data: queryString,
		error: updateError,
		success: updateSuccess
	});
}

//comment 업데이트 success
function updateSuccess(data) {

	$("#comment_" + data.commentNo).empty();
	$("#comment_" + data.commentNo).html(data.comment);
}

// comment 업데이트 error
function updateError() {
	alert("에러가 발생하였습니다.");
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

//comment 업데이트폼 success
function forUpdateSuccess(data) {
	
	var boardNo = $("#boardNo").val();
	$("#comment_" + data.commentNo).empty();
	
	var result = '';
	result += '<form action="/boardComment/boardCommentUpdate" method="post" id="commentUpdateForm">';
	result += 	'<input type="hidden" name="boardNo" value="' + boardNo + '" />';
	result += 	'<textarea class="form-control" id="comment" name="comment" placeholder="comment" rows="3">' + data.comment + '</textarea>';
	result += 	'<div class="col-md-12" id="buttonDiv">';
	result += 		'<input type="hidden" name="commentNo" value="' + data.commentNo + '" />';
	result += 		'<button type="button" class="btn btn-success pull-right" id="commentUpdateBtn">작성</button>';
	result += 		'<button type="button" class="btn btn-danger pull-right" id="commentCancelBtn">취소</button>';
	result += 	'</div>';
	result += '</form>';
		
	$("#comment_" + data.commentNo).append(result);
}

//comment 업데이트폼 error
function forUpdateError() {
	alert("에러가 발생하였습니다.");
}

// comment 삭제
function deleteComment(commentNo, e) {
	
	if(confirm("댓글을 삭제하시겠습니까?")) {
		e.preventDefault();

		$.ajax({
			type: "POST",
			url: "/boardComment/ajax/boardCommentDelete",
			dataType: "json",
			data: {
				commentNo : commentNo
			},
			error: deleteError,
			success: deleteSuccess
		});
	}
	
}

// comment 삭제 success
function deleteSuccess(data) {
	console.log(data);
	if(data.resultBoolean) {
		
		var totComments = Number($("#totComments").text()) - 1;
		
		$("#thumbnailDiv_" + data.commentNo).remove();
		$("#contentDiv_" + data.commentNo).remove();
		
		$("#totComments").text(totComments);
	}
}

// comment 삭제 error
function deleteError() {
	alert("에러가 발생하였습니다.");
}

// comment html 생성
function commentHtml(dataObj) {
	
	var result = '';
	
	result += '<div class="col-sm-1" id="thumbnailDiv_' + dataObj.commentNo + '">';
	result += 	'<div class="thumbnail">';
	result += 		'<img class="img-responsive user-photo" src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png">';
	result += 	'</div>';
	result += '</div>';
	result += '<div class="col-sm-11" id="contentDiv_' + dataObj.commentNo + '">';
	result += 	'<div class="panel panel-default">';
	result += 		'<div class="panel-heading">';
	result += 			'<strong>' + dataObj.userId + '</strong>';
	result += 			'<i class="glyphicon glyphicon-time"></i><span class="text-muted">' + dataObj.date + '</span>';
	result += 			'<button type="button" class="btn btn-danger btn-xs clearfix pull-right" id="deleteCommentBtn" onClick="deleteComment(' + dataObj.commentNo + ', event)">';
	result += 				'<span class="glyphicon glyphicon-trash"></span>';
	result += 			'</button>';
	result += 			'<button type="button" class="btn btn-primary btn-xs clearfix pull-right" id="updateCommentBtn" onClick="forUpdateComment(' + dataObj.commentNo + ', event)">';
	result += 				'<span class="glyphicon glyphicon-pencil"></span>';
	result += 			'</button>';
	result += 		'</div>';
	result += 		'<div class="panel-body" id="comment_' + dataObj.commentNo + '">' + dataObj.comment + '</div>';
	result += 	'</div>';
	result += '</div>';
	
	return result;
}