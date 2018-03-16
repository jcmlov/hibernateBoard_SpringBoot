$(function() {
	$("#commentRegistBtn").click(addComment);
	
	$(document).on("click", "#commentCancelBtn", function() {
		var commentVal = $(this).parent().parent().find("#comment").val();
		var commentDiv = $(this).parent().parent().parent().empty();
		
		commentDiv.html(commentVal);
	});
	
	$(document).on("click", "#commentUpdateBtn", function() {
		
	});
	
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
	
	$("#comment").val('');
	var totComments = Number($("#totComments").text()) + 1;
	
    var dataObj = {"userId" : data.register.userId, "date" : data.formattedRegistDate, "commentNo" : data.commentNo, "comment" : data.comment};
	
	$(".comment").prepend(commentHtml(dataObj));
	$("#totComments").text(totComments);
	
}

// comment 등록 error
function registError() {
	
}

//comment 업데이트폼 success
function forUpdateSuccess(data) {
	
	var boardNo = $("#boardNo").val();
	$("#comment_" + data.commentNo).empty();
	
	var html = '';
	html += '<form action="/boardComment/boardCommentUpdate" method="post" id="commentUpdateForm">';
	html += 	'<input type="hidden" name="boardNo" value="' + boardNo + '" />';
	html += 	'<textarea class="form-control" id="comment" name="comment" placeholder="comment" rows="3">' + data.comment + '</textarea>';
	html += 	'<div class="col-md-12" id="buttonDiv">';
	html += 		'<button type="button" class="btn btn-success pull-right" id="commentUpdateBtn">작성</button>';
	html += 		'<button type="button" class="btn btn-danger pull-right" id="commentCancelBtn">취소</button>';
	html += 	'</div>';
	html += '</form>';
		
	$("#comment_" + data.commentNo).append(html);
}

function forUpdateError() {
	
}

function commentHtml(dataObj) {
	
	var result = '';
	
	result += '<div class="col-sm-1">';
	result += 	'<div class="thumbnail">';
	result += 		'<img class="img-responsive user-photo" src="https://ssl.gstatic.com/accounts/ui/avatar_2x.png">';
	result += 	'</div>';
	result += '</div>';
	result += '<div class="col-sm-11">';
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