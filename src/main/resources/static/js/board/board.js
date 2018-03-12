$(function() {
	$("#commentRegistBtn").click(addComment);
});

function addComment(e) {
	
	e.preventDefault();

	var queryString = $("#commentForm").serialize();
	
	$.ajax({
		type: "post",
		url: "/boardComment/ajax/boardCommentRegist",
		dataType: "json",
		data: queryString,
		error: resultError,
		success: resultSuccess
	});
}

function resultSuccess(data) {

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

function resultError() {
	
}